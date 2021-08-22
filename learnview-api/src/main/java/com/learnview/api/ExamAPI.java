package com.learnview.api;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learnview.LearnViewApplication;
import com.learnview.domain.Question;
import com.learnview.dto.Exam;
import com.learnview.repo.QuestionRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = { LearnViewApplication.HTTP_ORIGINS1, LearnViewApplication.HTTP_ORIGINS2 })
public class ExamAPI {

    @Autowired
    public ExamAPI(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    private QuestionRepository questionRepository;

    @GetMapping("/exams")
    public List<Exam> findExams() {
        List<Question> allQuestions = questionRepository.findAll();
        return allQuestions.stream().map(q -> new Exam(q.getExam(), q.getCategory())).distinct()
                .collect(Collectors.toList());
    }

}
