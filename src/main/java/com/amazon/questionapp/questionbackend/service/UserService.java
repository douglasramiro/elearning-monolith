package com.amazon.questionapp.questionbackend.service;

import com.amazon.questionapp.questionbackend.exceptions.BusinessException;
import com.amazon.questionapp.questionbackend.exceptions.ResourceNotFoundException;
import com.amazon.questionapp.questionbackend.model.User;
import com.amazon.questionapp.questionbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void createNewUser(User user){
        Optional<User> userByEmail = userRepository.findUserByEmail(user.getEmail());
        if (userByEmail.isPresent()){
            throw new BusinessException("The email is already in use.");
        }
        userRepository.save(user);
    }

    public User recoverUserById(String id){
        return userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User not found!"));
    }

    public void addCreditsToUser(String id, Integer amount){
        User user = recoverUserById(id);
        user.addCredits(amount);
        userRepository.save(user);
    }

    public Integer recoverCreditsByUserId(String id){
        return userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User not found")).getCredits();
    }

    public List<User> listAllUsers(){
        return userRepository.findAll();
    }

    public void reduceCredit(User user) {
        user.reduceCredit();
        userRepository.save(user);
    }
}
