package com.pvh.group3_spring_mini_project001.repository;

import com.pvh.group3_spring_mini_project001.model.Task;
import com.pvh.group3_spring_mini_project001.model.request.TaskRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TaskRepository {

    @Select("""
            SELECT * FROM task_tb
            """)
    @Results(id = "taskMap", value = {
            @Result(property = "taskId", column = "task_id"),
            @Result(property = "taskName", column = "task_name"),
            @Result(property = "description", column = "task_description"),
            @Result(property = "date", column = "task_date"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "status", column = "task_status"),
            @Result(property = "categoryId", column = "category_id"),
    })
    List<Task> getAllTask();

    @Select("""
            SELECT * FROM task_tb where task_id=#{id}
            """)
    @ResultMap("taskMap")
    Task searchTaskById(Integer id);

    @Select("""
            insert into task_tb(task_name, task_description, task_date, task_status, user_id, category_id)
            values (#{task.taskName},#{task.description},#{task.date},#{task.status},#{id},#{task.categoryId}) returning *
            """)
    @ResultMap("taskMap")
    Task insertTask(@Param("task") TaskRequest taskRequest, Integer id);

    @Select("""
            select * from task_tb where user_id=#{userId} and task_id=#{id}
            """)
    @ResultMap("taskMap")
    Task getTaskByIdUser(Integer userId, Integer id);

    @Select("""
            SELECT * FROM task_tb where user_id=#{id}
            """)
    @ResultMap("taskMap")
    List<Task> getTaskByUser(Integer id);

    @Delete("""
            delete from task_tb where task_id=#{id} and user_id=#{userId}
            """)
    Integer deleteTaskByUser(Integer id,Integer userId);

    @Select("""
            update task_tb set task_name=#{task.taskName},task_description=#{task.description},
            task_date=#{task.date},task_status=#{task.status},category_id=#{task.categoryId} 
            where task_id=#{id} and user_id=#{idOfCurrentUser} returning *
            """)
    @ResultMap("taskMap")
    Task updateTaskByUser(@Param("task") TaskRequest taskRequest, Integer id, Integer idOfCurrentUser);

    @Select("""
            SELECT * FROM task_tb where task_status LIKE #{status} limit #{pageSize} offset #{pageNo}
            """)
    @ResultMap("taskMap")
    List<Task> getTaskByUserStatus(String status, Integer pageNo, Integer pageSize);
    @Select("""
            SELECT * FROM task_tb where task_status=#{status} order by task_date desc limit #{pageSize} offset #{pageNo}
            """)
    @ResultMap("taskMap")
    List<Task> getTaskByUserStatusDes(String status, Integer pageNo, Integer pageSize);
}
