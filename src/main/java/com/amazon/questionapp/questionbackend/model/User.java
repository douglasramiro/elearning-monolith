package com.amazon.questionapp.questionbackend.model;

import com.amazon.questionapp.questionbackend.exceptions.InvalidObjectException;
import com.amazon.questionapp.questionbackend.util.PasswordUtils;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "TBL_USER")
@NoArgsConstructor
public class User {

    @Id
    private String id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Integer credits;

    public User(String email, String name, String password) throws InvalidObjectException {
        this.id = UUID.randomUUID().toString();
        this.credits = 0;
        this.email = email;
        this.name = name;
        this.password = password;
        validateUser();
        encryptPassword();
    }

    public void addCredits(Integer amount) {
        credits+=amount;
    }

    public  void encryptPassword() {
        setPassword(PasswordUtils.hashPassword(password));
    }

    public void validateUser() throws InvalidObjectException {
        List<String> errors = new ArrayList<>();
        EmailValidator emailValidator = EmailValidator.getInstance();
        if (email == null || "".equals(email) || !emailValidator.isValid(email)){
            errors.add("A valid email is required");
        }
        if (name == null || "".equals(name) || name.length() < 3) {
            errors.add("A name must be provided with at least 3 characters");
        }
        if (password == null || "".equals(password) || password.length() < 6){
            errors.add("A password must be provided with at least 6 characters");
        }
        if (errors.size() > 0){
            throw new InvalidObjectException(errors);
        }
    }

    public void reduceCredit() {
        this.credits--;
    }
}
