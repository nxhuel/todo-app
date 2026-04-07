package com.nxhu.todo.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.nxhu.todo.dto.request.TaskDtoReq;
import com.nxhu.todo.dto.response.TaskDtoRes;
import com.nxhu.todo.mapper.TaskMapper;
import com.nxhu.todo.persistence.entity.PriorityEnum;
import com.nxhu.todo.persistence.entity.TaskEntity;
import com.nxhu.todo.persistence.repository.ITaskRepository;
import com.nxhu.todo.service.ITaskService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements ITaskService {

    private final ITaskRepository taskRepository;

    @Override
    public TaskDtoRes createTask(TaskDtoReq taskDtoReq) {
        // Convertir taskDtoReq a TaskEntity y guardarlo en la base de datos
        TaskEntity taskEntity = new TaskEntity();

        taskEntity.setTitle(taskDtoReq.getTitle());
        taskEntity.setDescription(taskDtoReq.getDescription());
        taskEntity.setCompleted(taskDtoReq.isCompleted());
        taskEntity.setPriority(taskDtoReq.getPriority());
        taskEntity.setCategory(taskDtoReq.getCategory());
        taskEntity.setDueDate(taskDtoReq.getDueDate());

        // Sistema se encarga de generar la fecha de creación
        taskEntity.setCreatedAt(LocalDate.now());

        taskRepository.save(taskEntity);

        // Convertir TaskEntity a TaskDtoRes y devolverlo al usuario
        TaskDtoRes taskDtoRes = new TaskDtoRes();

        taskDtoRes.setId(taskEntity.getId());
        taskDtoRes.setTitle(taskEntity.getTitle());
        taskDtoRes.setDescription(taskEntity.getDescription());
        taskDtoRes.setCompleted(taskEntity.isCompleted());
        taskDtoRes.setPriority(taskEntity.getPriority());
        taskDtoRes.setCategory(taskEntity.getCategory());
        taskDtoRes.setDueDate(taskEntity.getDueDate());

        return taskDtoRes;
    }

    @Override
    public List<TaskDtoRes> findAllTasks() {
        // Obtener todas las tareas de la base de datos y convertirlas a TaskDtoRes
        List<TaskEntity> taskEntities = taskRepository.findAll();

        return taskEntities.stream().map(taskEntity -> {
            TaskDtoRes taskDtoRes = new TaskDtoRes();

            taskDtoRes.setId(taskEntity.getId());
            taskDtoRes.setTitle(taskEntity.getTitle());
            taskDtoRes.setDescription(taskEntity.getDescription());
            taskDtoRes.setCompleted(taskEntity.isCompleted());
            taskDtoRes.setPriority(taskEntity.getPriority());
            taskDtoRes.setCategory(taskEntity.getCategory());
            taskDtoRes.setDueDate(taskEntity.getDueDate());

            return taskDtoRes;
        }).toList();
    }

    @Override
    public TaskDtoRes findTaskById(Long id) {
        TaskEntity taskEntity = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));

        TaskDtoRes taskDtoRes = new TaskDtoRes();
        taskDtoRes.setId(taskEntity.getId());
        taskDtoRes.setTitle(taskEntity.getTitle());
        taskDtoRes.setDescription(taskEntity.getDescription());
        taskDtoRes.setCompleted(taskEntity.isCompleted());
        taskDtoRes.setPriority(taskEntity.getPriority());
        taskDtoRes.setCategory(taskEntity.getCategory());
        taskDtoRes.setDueDate(taskEntity.getDueDate());

        return taskDtoRes;
    }

    @Override
    public TaskDtoRes patchTask(Long id, Map<String, Object> updates) {
        TaskEntity taskEntity = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));

        updates.forEach((key, value) -> {
            switch (key) {
                case "title" -> taskEntity.setTitle((String) value);
                case "description" -> taskEntity.setDescription((String) value);
                case "completed" -> taskEntity.setCompleted((Boolean) value);
                case "priority" -> taskEntity.setPriority(PriorityEnum.valueOf((String) value));
                case "category" -> taskEntity.setCategory((String) value);
                case "dueDate" -> taskEntity.setDueDate(LocalDate.parse((String) value));
                default -> throw new IllegalArgumentException("Campo no válido: " + key);
            }
        });

        taskEntity.setUpdatedAt(LocalDate.now());
        taskRepository.save(taskEntity);

        TaskDtoRes taskDtoRes = new TaskDtoRes();
        taskDtoRes.setId(taskEntity.getId());
        taskDtoRes.setTitle(taskEntity.getTitle());
        taskDtoRes.setDescription(taskEntity.getDescription());
        taskDtoRes.setCompleted(taskEntity.isCompleted());
        taskDtoRes.setPriority(taskEntity.getPriority());
        taskDtoRes.setCategory(taskEntity.getCategory());
        taskDtoRes.setDueDate(taskEntity.getDueDate());

        return taskDtoRes;
    }

    @Override
    public void deleteTask(Long id) {
        TaskEntity taskEntity = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));

        taskRepository.delete(taskEntity);
    }

    @Override
    public List<TaskDtoRes> findByTitle(String title) {
        return taskRepository.findByTitleContainingIgnoreCase(title)
        .stream()
        .map(TaskMapper::toDto)
        .toList();
    }

    @Override
    public List<TaskDtoRes> findByCompleted(boolean completed) {
        return taskRepository.findByCompleted(completed)
        .stream()
        .map(TaskMapper::toDto)
        .toList();
    };

}
