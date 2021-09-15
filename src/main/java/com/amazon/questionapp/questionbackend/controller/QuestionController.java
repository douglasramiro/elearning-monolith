package com.amazon.questionapp.questionbackend.controller;

import com.amazon.questionapp.questionbackend.dto.comment.CommentOutputDTO;
import com.amazon.questionapp.questionbackend.dto.question.QuestionInputDTO;
import com.amazon.questionapp.questionbackend.dto.question.QuestionOutputDTO;
import com.amazon.questionapp.questionbackend.dto.question.QuestionWithCommentsOutputDTO;
import com.amazon.questionapp.questionbackend.exceptions.InvalidObjectException;
import com.amazon.questionapp.questionbackend.model.Question;
import com.amazon.questionapp.questionbackend.service.QuestionService;
import com.amazon.questionapp.questionbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public QuestionOutputDTO save(@RequestBody QuestionInputDTO questionInputDTO) throws InvalidObjectException {
        Question question = new Question(questionInputDTO.getTitle(), questionInputDTO.getQuestion(),userService.recoverUserById(questionInputDTO.getUserId()));
        questionService.creteNewQuestion(question);
        return new QuestionOutputDTO(question.getId(), question.getTitle(), question.getQuestion(), question.getUser().getId());
    }

    @GetMapping
    public List<QuestionOutputDTO> listAll(){
        List<QuestionOutputDTO> returnList = new ArrayList<>();
        List<Question> questions = questionService.listAllQuestions();
        questions.forEach(question -> {
            returnList.add(new QuestionOutputDTO(question.getId(), question.getTitle(), question.getQuestion(), question.getUser().getId()));
        });
        return returnList;
    }

    @GetMapping("/with-comments")
    public List<QuestionWithCommentsOutputDTO> listAllWithComments(){
        List<QuestionWithCommentsOutputDTO> returnList = new ArrayList<>();
        List<Question> questions = questionService.listAllQuestionsWithComments();
        questions.forEach(question -> {
            QuestionWithCommentsOutputDTO questionWithCommentsOutputDTO = new QuestionWithCommentsOutputDTO(question.getId(), question.getTitle(), question.getQuestion(), question.getUser().getId());
            question.getComments().forEach(comment -> {
                questionWithCommentsOutputDTO.addComment(new CommentOutputDTO(comment.getId(),comment.getComment(),comment.getQuestion().getId()));
            });
            returnList.add(questionWithCommentsOutputDTO);
        });
        return returnList;
    }
}
