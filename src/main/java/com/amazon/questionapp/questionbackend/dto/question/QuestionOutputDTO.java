package com.amazon.questionapp.questionbackend.dto.question;

import com.amazon.questionapp.questionbackend.dto.comment.CommentOutputDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionOutputDTO {

    private String id;
    private String title;
    private String question;
    private String userId;

}
