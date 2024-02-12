package com.pvh.group3_spring_mini_project001.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
   private Integer userId;
    private String email;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String token;
}
