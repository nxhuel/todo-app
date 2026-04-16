package com.nxhu.todo.service;

import java.util.List;
import java.util.Map;

import com.nxhu.todo.dto.request.TaskDtoReq;
import com.nxhu.todo.dto.response.TaskDtoRes;
import com.nxhu.todo.exception.TaskNotFoundEx;

public interface ITaskService {

    // CRUD 
    TaskDtoRes createTask(TaskDtoReq taskDtoReq);

    List<TaskDtoRes> findAllTasks();

    TaskDtoRes findTaskById(Long id) throws TaskNotFoundEx;

    TaskDtoRes patchTask(Long id, Map<String, Object> updates) throws TaskNotFoundEx;

    void deleteTask(Long id) throws TaskNotFoundEx;

    // FILTROS
    List<TaskDtoRes> findByTitle(String title);

    List<TaskDtoRes> findByCompleted(boolean completed);
}
