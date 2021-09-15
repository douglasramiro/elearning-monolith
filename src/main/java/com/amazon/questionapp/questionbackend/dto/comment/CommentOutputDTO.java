package com.amazon.questionapp.questionbackend.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentOutputDTO {

    private String id;
    private String comment;
    private String questionId;
}
