package com.learnview.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.learnview.domain.Test;

@Repository
public interface TestRepository extends MongoRepository<Test, String> {

    List<Test> findAllOrderByWhen();

}
