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
public class QuestionWithCommentsOutputDTO {

    private String id;
    private String title;
    private String question;
    private String userId;

    public QuestionWithCommentsOutputDTO(String id, String title, String question, String userId) {
        this.id = id;
        this.title = title;
        this.question = question;
        this.userId = userId;
    }

    private List<CommentOutputDTO> comments;

    public void addComment(CommentOutputDTO commentOutputDTO){
        getComments().add(commentOutputDTO);
    }
    public List<CommentOutputDTO> getComments() {
        if (comments == null){
            comments = new ArrayList<>();
        }
        return comments;
    }
}
