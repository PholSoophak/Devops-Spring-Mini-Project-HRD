package com.pvh.group3_spring_mini_project001.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    private Integer taskId;
    private String taskName;
    private String description;
    private LocalDateTime date;
    private Integer userId;
    private String status;
    private Integer categoryId;
}
