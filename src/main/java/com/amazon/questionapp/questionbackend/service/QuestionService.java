package com.amazon.questionapp.questionbackend.service;


import com.amazon.questionapp.questionbackend.exceptions.BusinessException;
import com.amazon.questionapp.questionbackend.exceptions.ResourceNotFoundException;
import com.amazon.questionapp.questionbackend.model.Question;
import com.amazon.questionapp.questionbackend.model.User;
import com.amazon.questionapp.questionbackend.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final UserService userService;

    public void creteNewQuestion(Question question){
        User user = userService.recoverUserById(question.getUser().getId());
        if (user.getCredits() <= 0){
            throw new BusinessException("Not enough credits to create a question!");
        }
        userService.reduceCredit(user);
        question.setUser(user);
        questionRepository.save(question);
    }

    public List<Question> listAllQuestions(){
        return questionRepository.findAll();
    }

    public List<Question> listAllQuestionsWithComments(){
        return questionRepository.findAllWithComments();
    }

    public Question recoverQuestionById(String id) {
        return questionRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Question "+id+" not found"));
    }
}
