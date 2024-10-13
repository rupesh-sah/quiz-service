package com.rupesh.question_service.dao;


import com.rupesh.question_service.entities.QuestionEntities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<QuestionEntities, Integer> {

    List <QuestionEntities> findByCategory(String category);

    @Query(value =  "SELECT q.id FROM question_entities q Where q.category = :category ORDER BY RANDOM() LIMIT :numQ ",nativeQuery = true)
    List<Integer> findRandomQuestionsByCategory(String category, int numQ);



}
