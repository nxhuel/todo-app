package com.nxhu.todo.dto.response;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.nxhu.todo.persistence.entity.PriorityEnum;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
@JsonPropertyOrder({
    "id",
    "title",
    "description",
    "completed",
    "priority",
    "category",
    "dueDate"
})
public class TaskDtoRes {

    private Long id;

    private String title;

    private String description;

    private boolean completed;

    @Enumerated(EnumType.STRING)
    private PriorityEnum priority;

    private String category;

    private LocalDate dueDate;
}
