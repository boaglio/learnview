package com.learnview.load;

import java.io.File;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learnview.domain.Question;
import com.learnview.repo.QuestionRepository;

import lombok.extern.java.Log;

@Log
@Component
public class DataLoadRunner implements ApplicationRunner {

    @Value("${load.path}")
    private String loadPath;

    @Autowired
    public DataLoadRunner(QuestionRepository questionRepository, ObjectMapper objectMapper) {
        this.questionRepository = questionRepository;
        this.objectMapper = objectMapper;
    }

    private QuestionRepository questionRepository;
    private ObjectMapper       objectMapper;

    @Override
    public void run(ApplicationArguments args) {

        try {
            LocalDateTime start = LocalDateTime.now();
            log.info("==== DataLoadRunner started [" + loadPath + "] ====");

            List<String> exams = Stream.of(new File(loadPath).listFiles()).filter(file -> file.isDirectory())
                    .map(File::getName).collect(Collectors.toList());

            exams.forEach(e -> System.out.println("exam found: " + e));

            for (String examToLoad : exams) {
                String questionDir = loadPath + File.separator + examToLoad;
                List<String> questions = Stream.of(new File(questionDir).listFiles())
                        .filter(file -> !file.isDirectory()).map(File::getName).collect(Collectors.toList());
                System.out.println("questions: " + questions.size());
                // load question to exam db
                int count = 1;
                for (String q : questions) {
                    log.info("Saving " + count + "/" + questions.size());
                    String fileStr = FileUtil.convertFile2String(questionDir + File.separator + q);
                    saveQuestion(fileStr);
                }
            }

            LocalDateTime end = LocalDateTime.now();
            log.info(String.format("==== DataLoadRunner ended: [%d minutes %d seconds] ====",
                    TimeUnit.MILLISECONDS.toMinutes(Duration.between(start, end).toMillis()),
                    TimeUnit.MILLISECONDS.toSeconds(Duration.between(start, end).toMillis()) - TimeUnit.MINUTES
                            .toSeconds(TimeUnit.MILLISECONDS.toMinutes(Duration.between(start, end).toMillis()))));
        } catch (Exception e) {
            log.info("==== DataLoadRunner error: " + e.getMessage());
        }
    }

    private void saveQuestion(String strQuestion) {
        Question q = new Question();
        try {
            q = objectMapper.readValue(strQuestion, Question.class);
            questionRepository.save(q);
        } catch (JsonProcessingException e) {
            log.info("parse error: " + e.getMessage());
        }
    }

}
