package com.learnview.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.learnview.domain.Question;

@Repository
public interface QuestionRepository extends MongoRepository<Question, String> {

    // counts
    long countByExam(String exam);

    long countByCategory(String category);

    long countByCategoryAndSubcategory(String category, String subcategory);

    // get questions for exam
    List<Question> findByExam(String exam);

    Question findTopByOrderByIdDesc();

}
