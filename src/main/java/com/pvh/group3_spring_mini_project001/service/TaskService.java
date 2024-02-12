package com.pvh.group3_spring_mini_project001.service;

import com.pvh.group3_spring_mini_project001.model.Task;
import com.pvh.group3_spring_mini_project001.model.request.TaskRequest;


import java.util.List;

public interface TaskService {

    List<Task> getAllTask();
    Task searchTaskById(Integer id);
    Task insertTask(TaskRequest taskRequest,Integer currentId);
    Task getTaskByIdUser(Integer userId, Integer id);

    List<Task> getTaskByUser(Integer id);

    void deleteTaskByUser(Integer id,Integer userId);

    Task updateTaskByUser(TaskRequest taskRequest, Integer id, Integer idOfCurrentUser);

    List<Task> getTaskByUserStatus(String status, boolean asc, boolean des, Integer pageNo, Integer pageSize);
}
