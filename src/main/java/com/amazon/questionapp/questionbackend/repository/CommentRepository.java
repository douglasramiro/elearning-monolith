package com.amazon.questionapp.questionbackend.repository;

import com.amazon.questionapp.questionbackend.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentRepository extends JpaRepository<Comment, String> {

}
