package com.amazon.questionapp.questionbackend.model;

import com.amazon.questionapp.questionbackend.exceptions.InvalidObjectException;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "TBL_QUESTION")
@NoArgsConstructor
public class Question {

    @Id
    private String id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String question;

    @ManyToOne(optional = false)
    private User user;

    @OneToMany(mappedBy = "question")
    private List<Comment> comments;

    public Question(String title, String question, User user) throws InvalidObjectException {
        id = UUID.randomUUID().toString();
        this.title = title;
        this.question = question;
        this.user = user;
        validateQuestion();
    }

    public void validateQuestion() throws InvalidObjectException {
        List<String> errors = new ArrayList<>();
        if (title == null || "".equals(title) || title.length() < 10 || title.length() > 255){
            errors.add("A title must be provided with at least 10 characters and less than 255");
        }
        if (question == null || "".equals(question) || question.length() < 15 || question.length() > 255) {
            errors.add("A question must be provided with at least 15 characters and less than 255");
        }
        if (user == null){
            errors.add("A question must be associated to an user.");
        }
        if (errors.size() > 0){
            throw new InvalidObjectException(errors);
        }
    }
}
