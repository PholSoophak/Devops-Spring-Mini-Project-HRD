package com.pvh.group3_spring_mini_project001.service.serviceImp;

import com.pvh.group3_spring_mini_project001.excep.EmptyInput;
import com.pvh.group3_spring_mini_project001.exception.EmptyValidate;
import com.pvh.group3_spring_mini_project001.exception.NotFoundValidate;
import com.pvh.group3_spring_mini_project001.exception.NotYourOwn;
import com.pvh.group3_spring_mini_project001.model.Task;
import com.pvh.group3_spring_mini_project001.model.constant.StatusTask;
import com.pvh.group3_spring_mini_project001.model.request.TaskRequest;
import com.pvh.group3_spring_mini_project001.repository.TaskRepository;
import com.pvh.group3_spring_mini_project001.service.CategoryService;
import com.pvh.group3_spring_mini_project001.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskImp implements TaskService {

    private final TaskRepository taskRepository;
    private final CategoryService categoryService;

    public TaskImp(TaskRepository taskRepository, CategoryService categoryService) {
        this.taskRepository = taskRepository;
        this.categoryService = categoryService;
    }

    @Override
    public List<Task> getAllTask() {
        return taskRepository.getAllTask();
    }

    @Override
    public Task searchTaskById(Integer id) {
        Task task = taskRepository.searchTaskById(id);
        if (task == null) {
            throw new NotFoundValidate("This Task is not found");
        }
        return task;
    }

    @Override
    public Task insertTask(TaskRequest taskRequest, Integer currentId) {
        boolean b = false;
        for (StatusTask status : StatusTask.values()
        ) {
            if (taskRequest.getStatus().equalsIgnoreCase(status.name())) {
                b = true;
                break;
            }
        }
        if (taskRequest.getTaskName().isEmpty() || taskRequest.getTaskName().isBlank()) {
            throw new EmptyValidate("Task name cannot Empty or Blank");

        } else if (!b) {
            throw new EmptyInput("This status is not correct : 'string' " +
                    "please input one of (is_canceled, is_completed, is_in_progress, is_in_review)");
        }
        categoryService.searchCategoryIdByUser(taskRequest.getCategoryId(), currentId);
        categoryService.searchCategoryById(taskRequest.getCategoryId());
        return taskRepository.insertTask(taskRequest, currentId);
    }

    @Override
    public Task getTaskByIdUser(Integer userId, Integer id) {
        searchTaskById(id);
        Task task=taskRepository.getTaskByIdUser(userId, id);
        if (task==null){
            throw new NotFoundValidate("You don't have this task");
        }
        return task;
    }

    @Override
    public List<Task> getTaskByUser(Integer id)
    {
List<Task> tasks=taskRepository.getTaskByUser(id);
if (tasks.isEmpty()){
    throw new NotFoundValidate("You do not have any task!");
}
        return  tasks;
    }

    @Override
    public void deleteTaskByUser(Integer id,Integer userId) {
        //categoryService.searchCategoryById(id);
        searchTaskById(id);
        if (  taskRepository.deleteTaskByUser(id,userId)!=1){
            throw new NotYourOwn("Can not delete this task is not owner!");
        }

    }

    @Override
    public Task updateTaskByUser(TaskRequest taskRequest, Integer id, Integer idOfCurrentUser) {
        boolean b = false;
        for (StatusTask status : StatusTask.values()
        ) {
            if (taskRequest.getStatus().equalsIgnoreCase(status.name())) {
                b = true;
                break;
            }
        }
        if (taskRequest.getTaskName().isEmpty() || taskRequest.getTaskName().isBlank()) {
            throw new EmptyValidate("Task name cannot Empty or Blank");

        } else if (!b) {
            throw new EmptyInput("This status is not correct : 'string' " +
                    "please input one of (is_canceled, is_completed, is_in_progress, is_in_review)");
        }
        searchTaskById(id);
        Task task = taskRepository.updateTaskByUser(taskRequest, id, idOfCurrentUser);
        if (task == null) {
            throw new NotYourOwn("You are owner this task");
        }
        categoryService.searchCategoryIdByUser(taskRequest.getCategoryId(), idOfCurrentUser);
        categoryService.searchCategoryById(taskRequest.getCategoryId());
        return task;
    }

    @Override
    public List<Task> getTaskByUserStatus(String status, boolean asc, boolean des, Integer pageNo, Integer pageSize) {
        pageNo=(pageNo-1)*pageSize;
        if (des){
            return taskRepository.getTaskByUserStatusDes(status,pageNo,pageSize);
        }
        return taskRepository.getTaskByUserStatus(status,pageNo,pageSize);
    }
}
