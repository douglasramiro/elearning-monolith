package com.amazon.questionapp.questionbackend.service;

import com.amazon.questionapp.questionbackend.model.Comment;
import com.amazon.questionapp.questionbackend.model.Question;
import com.amazon.questionapp.questionbackend.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final QuestionService questionService;

    public void creteNewComment(Comment comment){
        Question question = questionService.recoverQuestionById(comment.getQuestion().getId());
        comment.setQuestion(question);
        commentRepository.save(comment);
    }

    public List<Comment> listAllComments(){
        return commentRepository.findAll();
    }
}
