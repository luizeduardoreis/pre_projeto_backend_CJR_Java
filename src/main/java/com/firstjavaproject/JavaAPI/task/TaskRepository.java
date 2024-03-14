package com.firstjavaproject.JavaAPI.task;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, UUID> {

    @Modifying
    @Transactional
    @Query("DELETE FROM TaskEntity t WHERE t.isDone = true")
    void deleteDoneTasks();


    @Query("SELECT t FROM TaskEntity t WHERE t.isDone = true")
    List<TaskEntity> getDoneTasks();

    @Query("SELECT t FROM TaskEntity t WHERE t.isDone = false")
    List<TaskEntity> getNotDoneTasks();

    @Transactional
    @Modifying
    @Query("DELETE FROM TaskEntity WHERE id = :id")
    void deleteTaskById(@Param("id") UUID id);

}
