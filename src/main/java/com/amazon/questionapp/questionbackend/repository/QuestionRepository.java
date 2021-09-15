package com.amazon.questionapp.questionbackend.repository;

import com.amazon.questionapp.questionbackend.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface QuestionRepository extends JpaRepository<Question, String> {

    @Query("SELECT q from Question q join fetch q.comments")
    List<Question> findAllWithComments();
}
