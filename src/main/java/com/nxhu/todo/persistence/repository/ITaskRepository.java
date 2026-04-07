package com.nxhu.todo.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nxhu.todo.persistence.entity.TaskEntity;

@Repository
public interface ITaskRepository extends JpaRepository<TaskEntity, Long> {

    List<TaskEntity> findByTitleContainingIgnoreCase(String title);

    List<TaskEntity> findByCompleted(boolean completed);
}
