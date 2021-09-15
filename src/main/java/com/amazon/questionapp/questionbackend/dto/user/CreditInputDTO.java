package com.amazon.questionapp.questionbackend.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditInputDTO {

    private String userId;
    private Integer creditAmount;

}
