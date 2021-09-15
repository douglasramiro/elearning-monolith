package com.amazon.questionapp.questionbackend.model;

import com.amazon.questionapp.questionbackend.exceptions.InvalidObjectException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "TBL_COMMENT")
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    private String id;

    @Column(nullable = false)
    private String comment;

    @ManyToOne(optional = false)
    private Question question;

    public Comment(String comment, Question question) throws InvalidObjectException{
        id = UUID.randomUUID().toString();
        this.comment = comment;
        this.question = question;
        validateComment();
    }

    public void validateComment() throws InvalidObjectException {
        List<String> errors = new ArrayList<>();
        if (comment == null || "".equals(comment) || comment.length() < 15 || comment.length() > 255) {
            errors.add("A comment must be provided with at least 15 characters and less than 255");
        }
        if (question == null){
            errors.add("A comment must be associated to a question.");
        }
        if (errors.size() > 0){
            throw new InvalidObjectException(errors);
        }
    }
}
