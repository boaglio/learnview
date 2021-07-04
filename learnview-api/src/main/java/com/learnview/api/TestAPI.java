package com.learnview.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learnview.LearnViewApplication;
import com.learnview.domain.Question;
import com.learnview.domain.Test;
import com.learnview.dto.Answer;
import com.learnview.dto.NewTestRequest;
import com.learnview.dto.RunningTest;
import com.learnview.repo.QuestionRepository;
import com.learnview.repo.TestRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = LearnViewApplication.HTTP_ORIGINS)
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
        newTest.setQuestion(allQuestions.get(0));
        newTest.setTest(test);

        testRepository.save(test);

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
        if (answer.getMark() == null) {
            newTest.setMsg("[mark] parameter missing");
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
        Optional<Test> test = testRepository.findById(answer.getQuestionID());
        if (test.isPresent()) {
            // save test
            test.get().getAnswers().add(answer);
        }

        // get next question
        Optional<Question> q = questionRepository.findById(answer.getQuestionID());
        Question nextQuestion = null;
        if (q.isPresent()) {
            List<Question> questions = questionRepository.findByExam(q.get().getExam());
            nextQuestion = questions.get(++position);
        }

        newTest.setTest(test.get());
        newTest.setQuestion(nextQuestion);

        return newTest;
    }

}
