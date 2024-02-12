package com.pvh.group3_spring_mini_project001.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Response<T> {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T payload;
    private LocalDateTime dateTime;
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private String message;
    private boolean status;
}
