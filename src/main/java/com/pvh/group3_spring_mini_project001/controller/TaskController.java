package com.pvh.group3_spring_mini_project001.controller;

import com.pvh.group3_spring_mini_project001.constance.Auth;
import com.pvh.group3_spring_mini_project001.model.Task;
import com.pvh.group3_spring_mini_project001.model.User;
import com.pvh.group3_spring_mini_project001.model.request.TaskRequest;
import com.pvh.group3_spring_mini_project001.response.Response;
import com.pvh.group3_spring_mini_project001.service.TaskService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("v1/api")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public ResponseEntity<Response<List<Task>>> getAllTask() {
        Response<List<Task>> response = Response.<List<Task>>builder()
                .payload(taskService.getAllTask())
                .dateTime(LocalDateTime.now())
                .status(true).build();
        return ResponseEntity.ok().body(response);
    }


    @GetMapping("/tasks/{id}")
    public ResponseEntity<Response<Task>> searchTaskById(
            @PathVariable Integer id
    ) {

            Response<Task> response = Response.<Task>builder()
                    .payload(taskService.searchTaskById(id))
                    .dateTime(LocalDateTime.now())
                    .status(true).build();
            return ResponseEntity.ok().body(response);

    }


    @PostMapping("/tasks/users")
    public ResponseEntity<Response<Task>> insertTask(
            @RequestBody TaskRequest taskRequest
    ) {
        Response<Task> response = Response.<Task>builder()
                .payload(taskService.insertTask(taskRequest, Auth.USER_ID))
                .dateTime(LocalDateTime.now())
                .status(true).build();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/tasks/{id}/users")
    public ResponseEntity<Response<Task>> getAllTaskByUser(
            @PathVariable Integer id
    ) {
        if (taskService.getTaskByIdUser(Auth.USER_ID,id)!=null){
            Response<Task> response = Response.<Task>builder()
                    .payload(taskService.getTaskByIdUser(Auth.USER_ID, id))
                    .dateTime(LocalDateTime.now())
                    .status(true)
                    .build();
            return ResponseEntity.ok().body(response);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/users")
    public ResponseEntity<Response<List<Task>>> getTaskByUser() {
        Response<List<Task>> response = Response.<List<Task>>builder()
                .payload(taskService.getTaskByUser(Auth.USER_ID))
                .dateTime(LocalDateTime.now())
                .status(true).build();
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}/users")
    public ResponseEntity<Response<Task>> deleteTaskByUser(
            @PathVariable Integer id
    ) {
       taskService.deleteTaskByUser(id,Auth.USER_ID);
            Response<Task> response = Response.<Task>builder()
                    .payload(null)
                    .message("Delete task successfully")
                    .dateTime(LocalDateTime.now())
                    .status(true).build();
            return ResponseEntity.ok().body(response);

    }

    @PutMapping("/{id}/users")
    public ResponseEntity<Response<Task>> updateTaskByUser(
            @PathVariable Integer id,
            @RequestBody TaskRequest taskRequest
    ) {
            Response<Task> response = Response.<Task>builder()
                    .payload(taskService.updateTaskByUser(taskRequest,id,Auth.USER_ID))
                    .dateTime(LocalDateTime.now())
                    .status(true).build();
            return ResponseEntity.ok().body(response);

    }

    @GetMapping("tasks/users")
    public ResponseEntity<Response<List<Task>>> getTaskByUserStatus(
            @RequestParam(name = "status",defaultValue = "is_in_progress") String status,
            @RequestParam(defaultValue = "false") boolean asc,
            @RequestParam(defaultValue = "false") boolean des,
            @RequestParam(defaultValue = "1") Integer pageNo,
            @RequestParam(defaultValue = "3") Integer pageSize
    ) {
            Response<List<Task>> response = Response.<List<Task>>builder()
                    .payload(taskService.getTaskByUserStatus(status,asc,des,pageNo,pageSize))
                    .dateTime(LocalDateTime.now())
                    .status(true).build();
            return ResponseEntity.ok().body(response);

    }
}


