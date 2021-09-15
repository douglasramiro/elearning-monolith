package com.amazon.questionapp.questionbackend.controller;

import com.amazon.questionapp.questionbackend.dto.user.CreditInputDTO;
import com.amazon.questionapp.questionbackend.dto.user.CreditOutputDTO;
import com.amazon.questionapp.questionbackend.dto.user.UserInputDTO;
import com.amazon.questionapp.questionbackend.dto.user.UserOutputDTO;
import com.amazon.questionapp.questionbackend.exceptions.InvalidObjectException;
import com.amazon.questionapp.questionbackend.model.User;
import com.amazon.questionapp.questionbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserOutputDTO save(@RequestBody UserInputDTO userInputDTO) throws InvalidObjectException {
        User user = new User(userInputDTO.getEmail(), userInputDTO.getName(), userInputDTO.getPassword());
        userService.createNewUser(user);
        return new UserOutputDTO(user.getId(),user.getEmail(), user.getName(), user.getCredits());
    }

    @GetMapping
    public List<UserOutputDTO> listAll(){
        List<UserOutputDTO> returnList = new ArrayList<>();
        List<User> users = userService.listAllUsers();
        users.forEach(user -> {
            returnList.add(new UserOutputDTO(user.getId(),user.getEmail(), user.getName(), user.getCredits()));
        });
        return returnList;
    }

    @PutMapping("/credits")
    public void addCreditsToUser(@RequestBody CreditInputDTO creditInputDTO){
        userService.addCreditsToUser(creditInputDTO.getUserId(), creditInputDTO.getCreditAmount());
    }

    @GetMapping(path = "/{id}/credits/")
    public CreditOutputDTO recoverCreditsByUserId(@PathParam("id") String userId){
        return new CreditOutputDTO(userId, userService.recoverCreditsByUserId(userId));
    }

}
