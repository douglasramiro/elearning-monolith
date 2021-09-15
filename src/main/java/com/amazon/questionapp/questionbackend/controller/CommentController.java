package com.amazon.questionapp.questionbackend.controller;

import com.amazon.questionapp.questionbackend.dto.comment.CommentInputDTO;
import com.amazon.questionapp.questionbackend.dto.comment.CommentOutputDTO;
import com.amazon.questionapp.questionbackend.exceptions.InvalidObjectException;
import com.amazon.questionapp.questionbackend.model.Comment;
import com.amazon.questionapp.questionbackend.service.CommentService;
import com.amazon.questionapp.questionbackend.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final QuestionService questionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentOutputDTO save(@RequestBody CommentInputDTO commentInputDTO) throws InvalidObjectException {
        Comment comment = new Comment(commentInputDTO.getComment(), questionService.recoverQuestionById(commentInputDTO.getQuestionId()));
        commentService.creteNewComment(comment);
        return new CommentOutputDTO(comment.getId(),comment.getComment(),comment.getQuestion().getId());
    }

    @GetMapping
    public List<CommentOutputDTO> listAll(){
        List<CommentOutputDTO> returnList = new ArrayList<>();
        List<Comment> comments = commentService.listAllComments();
        comments.forEach(comment -> {
            returnList.add(new CommentOutputDTO(comment.getId(),comment.getComment(),comment.getQuestion().getId()));
        });
        return returnList;
    }
}
