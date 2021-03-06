package com.learnview.api;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learnview.LearnViewApplication;
import com.learnview.domain.Option;
import com.learnview.domain.Question;
import com.learnview.domain.Test;
import com.learnview.dto.Answer;
import com.learnview.dto.NewTestRequest;
import com.learnview.dto.RunningTest;
import com.learnview.dto.TestResultRequest;
import com.learnview.dto.TestResults;
import com.learnview.repo.QuestionRepository;
import com.learnview.repo.TestRepository;
import com.learnview.util.SortQuestions;

import lombok.extern.java.Log;

@Log
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = { LearnViewApplication.HTTP_ORIGINS1, LearnViewApplication.HTTP_ORIGINS2 })
public class TestAPI {

    @Autowired
    public TestAPI(QuestionRepository questionRepository, TestRepository testRepository, ObjectMapper objectMapper) {
        this.questionRepository = questionRepository;
        this.testRepository = testRepository;
        this.objectMapper = objectMapper;
    }

    private QuestionRepository questionRepository;
    private TestRepository     testRepository;
    private ObjectMapper       objectMapper;

    @GetMapping("/tests")
    public List<Test> findTests() {
        List<Test> alltests = testRepository.findAll();
        return alltests.stream()
                .map(q -> new Test(q.getId(), q.getUsername(), q.getWhen(), q.getExam(), q.getCorrect(), q.getTotal()))
                .distinct().collect(Collectors.toList());
    }

    @PostMapping("/test")
    @ResponseBody
    public RunningTest newTest(@RequestBody String request) {

        RunningTest newTest = new RunningTest();
        NewTestRequest newTestRequest = new NewTestRequest();
        try {
            newTestRequest = objectMapper.readValue(request, NewTestRequest.class);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
        // validate input
        if (newTestRequest.getExam() == null) {
            newTest.setMsg("[exam] parameter missing");
            return newTest;
        }
        if (newTestRequest.getUsername() == null) {
            newTest.setMsg("[username] parameter missing");
            return newTest;
        }

        // get all questions
        List<Question> allQuestions = questionRepository.findByExam(newTestRequest.getExam());
        if (allQuestions.isEmpty()) {
            newTest.setMsg("exam [" + newTestRequest.getExam() + "] has no questions");
            return newTest;
        }

        Test test = new Test(newTestRequest.getUsername());
        test.setExam(newTestRequest.getExam());

        log.info("creating test " + test.getId() + " for " + test.getUsername());
        test.setOrder(SortQuestions.getRandomQuestions(allQuestions.size()));
        test.setTotal(allQuestions.size());
        test.setCorrect(0);
        log.info("exam " + test.getExam() + " - question random order: " + test.getOrder());

        Question firstQuestion = getNextQuestion(test.getExam(), test.getOrder(), 1);

        newTest.setQuestion(firstQuestion);
        test.setTotal(allQuestions.size());
        newTest.setTest(test);

        testRepository.save(test);

        test.setOrder(null);
        test.setCorrect(null);
        test.setAnswers(null);

        // return test + first question
        return newTest;
    }

    @PostMapping("/test/next-question")
    @ResponseBody
    public RunningTest updateTest(@RequestBody String request) {

        RunningTest newTest = new RunningTest();
        // save answer
        Answer answer = new Answer();
        try {
            answer = objectMapper.readValue(request, Answer.class);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
        // validate input
        if (answer.getTestID() == null) {
            newTest.setMsg("[testID] parameter missing");
            return newTest;
        }
        if (answer.getQuestionID() == null) {
            newTest.setMsg("[questionID] parameter missing");
            return newTest;
        }
        if (answer.getPosition() == null) {
            newTest.setMsg("[position] parameter missing");
            return newTest;
        }
        if (answer.getOptions() == null) {
            newTest.setMsg("[options] parameter missing");
            return newTest;
        }

        // get position
        int position = answer.getPosition();

        // get test
        Optional<Test> test = testRepository.findById(answer.getTestID());

        if (test.isPresent() && answer.getPosition() > test.get().getTotal()) {
            newTest.setMsg("[position] invalid value");
            return newTest;
        }

        if (test.isPresent() && !test.get().isCompleted()) {

            // save answer
            Map<String, Answer> userAnswerMap = test.get().getAnswers().stream()
                    .collect(Collectors.toMap(Answer::getQuestionID, Function.identity()));
            if (userAnswerMap.containsKey(answer.getQuestionID())) {
                test.get().getAnswers().remove(answer);
            }
            answer.setTestID(null);
            answer.setPosition(position);
            test.get().getAnswers().add(answer);
            int lastQuestion = test.get().getTotal();

            test.get().setPosition(position);

            if (lastQuestion == position) {
                log.info("last question: " + lastQuestion + " position: " + position);
                // get test results
                List<Question> allQuestions = questionRepository.findByExam(test.get().getExam());
                Map<String, Question> questionMap = allQuestions.stream()
                        .collect(Collectors.toMap(Question::getId, Function.identity()));

                test.get().setCorrect(0);
                test.get().getAnswers().forEach(a -> {
                    Question rightQuestion = questionMap.get(a.getQuestionID());

                    System.out
                            .print(" [a] " + a.getOptions() + " = " + rightQuestion.getOptions() + " [rightQuestion]");

                    if (a.getOptions().equals(rightQuestion.getOptions())) {
                        a.setCorrect(true);
                        test.get().setCorrect(test.get().getCorrect() + 1);

                        System.out.println(" - equal ! ");
                    } else {
                        System.out.println(" - NOT equal ! ");
                    }
                });

                test.get().setCompleted(true);

                // save
                testRepository.save(test.get());

            } else {

                log.info("current position: " + position);
                // get next question
                Question nextQuestion = getNextQuestion(test.get().getExam(), test.get().getOrder(), position + 1);
                newTest.setQuestion(nextQuestion);
                // save
                testRepository.save(test.get());

                test.get().setCorrect(null);
                test.get().setAnswers(null);
            }
            test.get().setOrder(null);

        }

        if (test.isPresent())
            newTest.setTest(test.get());

        return newTest;
    }

    private Question getNextQuestion(String exam, List<Integer> order, int position) {
        List<Question> allQuestions = questionRepository.findByExam(exam);
        int pos = order.get(position - 1);
        Question nextQuestion = allQuestions.get(pos - 1);
        nextQuestion.setExplanation(null);
        nextQuestion.setTags(null);
        nextQuestion.getOptions().forEach(opt -> {
            opt.setAnswer(null);
            opt.setCorrect(null);
            opt.setSelected(null);
        });
        nextQuestion.setOptSize(nextQuestion.getOptions().size());
        return nextQuestion;
    }

    @PostMapping("/test/results")
    @ResponseBody
    public TestResults getResults(@RequestBody String request) {

        TestResults newTestResults = new TestResults();
        TestResultRequest testResultRequest = new TestResultRequest();
        try {
            testResultRequest = objectMapper.readValue(request, TestResultRequest.class);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
        // validate input
        if (testResultRequest.getTestID() == null) {
            newTestResults.setMsg("[testID] parameter missing");
            return newTestResults;
        }
        if (testResultRequest.getPosition() == null) {
            newTestResults.setPosition(1);
        } else {
            try {
                newTestResults.setPosition(Integer.valueOf(testResultRequest.getPosition()));
            } catch (NumberFormatException nfe) {
                newTestResults.setMsg("[position] parameter invalid");
                return newTestResults;
            }
        }

        Optional<Test> storagedTest = testRepository.findById(testResultRequest.getTestID());

        if (storagedTest.isEmpty()) {
            newTestResults.setMsg("[testID] parameter invalid");
            return newTestResults;
        }
        newTestResults.setTestID(storagedTest.get().getId());
        newTestResults.setExam(storagedTest.get().getExam());
        newTestResults.setUsername(storagedTest.get().getUsername());
        newTestResults.setWhen(storagedTest.get().getWhen());
        newTestResults.setTotal(storagedTest.get().getTotal());
        newTestResults.setCorrect(storagedTest.get().getCorrect());

        Map<String, Answer> answerMap = storagedTest.get().getAnswers().stream()
                .collect(Collectors.toMap(Answer::getQuestionID, Function.identity()));

        List<Question> allQuestions = questionRepository.findByExam(storagedTest.get().getExam());

        int pos = storagedTest.get().getOrder().get(newTestResults.getPosition() - 1);
        Question storedQuestion = allQuestions.get(pos);

        Map<String, Option> optionMap = storedQuestion.getOptions().stream()
                .collect(Collectors.toMap(Option::getOption, Function.identity()));

        for (Option o : answerMap.get(storedQuestion.getId()).getOptions()) {
            Option userOption = optionMap.get(o.getOption());
            if (o.equals(userOption) && userOption.getSelected()) {
                o.setSelected(true);
            }
        }

        newTestResults.setQuestion(storedQuestion);

        return newTestResults;
    }

}
