package com.nxhu.todo.service;

import java.util.List;
import java.util.Map;

import com.nxhu.todo.dto.request.TaskDtoReq;
import com.nxhu.todo.dto.response.TaskDtoRes;

public interface ITaskService {

    TaskDtoRes createTask(TaskDtoReq taskDtoReq);

    List<TaskDtoRes> findAllTasks();

    TaskDtoRes findTaskById(Long id);

    TaskDtoRes patchTask(Long id, Map<String, Object> updates);

    void deleteTask(Long id);
}
