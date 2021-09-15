package com.amazon.questionapp.questionbackend.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOutputDTO {

    private String id;
    private String email;
    private String name;
    private Integer credits;

}
