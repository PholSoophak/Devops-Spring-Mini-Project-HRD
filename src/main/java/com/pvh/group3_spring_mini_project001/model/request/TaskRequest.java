package com.pvh.group3_spring_mini_project001.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskRequest {
    private String taskName;
    private String description;
    private LocalDateTime date;
    private String status;
    private Integer categoryId;
}
