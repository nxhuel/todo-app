package com.nxhu.todo.dto.request;

import java.time.LocalDate;

import com.nxhu.todo.persistence.entity.PriorityEnum;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class TaskDtoReq {

    private String title;

    private String description;

    private boolean completed;

    @Enumerated(EnumType.STRING)
    private PriorityEnum priority;

    private String category;

    private LocalDate dueDate;
}
