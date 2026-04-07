package com.nxhu.todo.mapper;

import com.nxhu.todo.dto.response.TaskDtoRes;
import com.nxhu.todo.persistence.entity.TaskEntity;

public class TaskMapper {
    public static TaskDtoRes toDto(TaskEntity task) {
        return new TaskDtoRes(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.isCompleted(),
                task.getPriority(),
                task.getCategory(),
                task.getDueDate());
    }

}
