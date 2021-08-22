package com.learnview.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learnview.LearnViewApplication;
import com.learnview.domain.Question;
import com.learnview.dto.NewQuestion;
import com.learnview.repo.QuestionRepository;
import com.learnview.util.IdManager;

import lombok.extern.java.Log;

@Log
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = { LearnViewApplication.HTTP_ORIGINS1, LearnViewApplication.HTTP_ORIGINS2 })
public class QuestionAPI {

    private ObjectMapper objectMapper;

    @Autowired
    public QuestionAPI(QuestionRepository questionRepository, ObjectMapper objectMapper) {
        this.questionRepository = questionRepository;
        this.objectMapper = objectMapper;
    }

    private QuestionRepository questionRepository;

    @GetMapping("/question/")
    public long count() {
        return questionRepository.count();
    }

    @GetMapping("/question/exam/{exam}")
    public long countByExam(@PathVariable String exam) {
        return questionRepository.countByExam(exam);
    }

    @GetMapping("/question/category/{category}")
    public long countByCategory(@PathVariable String category) {
        return questionRepository.countByCategory(category);
    }

    @GetMapping("/question/category/{category}/subcategory/{subcategory}/")
    public long countByCategoryAndSubcategory(@PathVariable String category, @PathVariable String subcategory) {
        return questionRepository.countByCategoryAndSubcategory(category, subcategory);
    }

    @GetMapping("/question/{id}")
    public Question findById(@PathVariable String id) {
        Optional<Question> question = questionRepository.findById(id);
        if (question.isEmpty())
            return new Question();
        return question.get();
    }

    @GetMapping("/questions/exam/{exam}")
    public List<Question> findByExam(@PathVariable String exam) {
        return questionRepository.findByExam(exam);
    }

    @PostMapping("/question/new")
    public Question newQuestion(@RequestBody String question) {

        NewQuestion newQuestion = new NewQuestion("Ok");
        log.info("Received: " + question);

        // convert to question
        try {
            newQuestion = objectMapper.readValue(question, NewQuestion.class);
        } catch (JsonProcessingException e) {
            log.info("parse error: " + e.getMessage());
            newQuestion = new NewQuestion("Parse error: [" + e.getMessage() + "]");
        }

        if (newQuestion.getExam() != null) {
            // get next id
            Question lastQuestion = questionRepository.findTopByOrderByIdDesc();
            String nextId = IdManager.getNext(lastQuestion.getExam(), lastQuestion.getId());
            log.info("Next ID: " + nextId);
            newQuestion.setId(nextId);
            questionRepository.save(newQuestion.getQuestion());
        }

        // return updated JSON question
        return newQuestion.getQuestion();
    }

}
