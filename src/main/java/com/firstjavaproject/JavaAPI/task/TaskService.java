package com.firstjavaproject.JavaAPI.task;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskEntity> getTasks() {
        return taskRepository.findAll();
    }

    public List<TaskEntity> getTasksByIsDone(Boolean isDone) {
        if (isDone) { return this.taskRepository.getDoneTasks(); }
        else { return this.taskRepository.getNotDoneTasks(); }
    }

    public TaskEntity addNewTask(TaskEntity newTask) {
        return taskRepository.save(newTask);
    }

    public TaskEntity updateTask(UUID id, TaskEntity updatedTask) {
        Optional<TaskEntity> taskToUpdateOpt = this.taskRepository.findById(id);

        if (taskToUpdateOpt.isPresent()) {
            TaskEntity taskToUpdate = taskToUpdateOpt.get();
            taskToUpdate.setName(updatedTask.getName());
            taskToUpdate.setIsDone(updatedTask.getIsDone());
            taskToUpdate.setTagId(updatedTask.getTagId());

            return this.taskRepository.save(taskToUpdate);
        } else {
            throw new EntityNotFoundException("Task with id " + id + " not found in the database");
        }
    }

    public void deleteAllTasks() {
        this.taskRepository.deleteAll();
    }

    public void deleteTaskById(UUID id) {
        this.taskRepository.deleteTaskById(id);
    }

    public void deleteDoneTasks() {
        this.taskRepository.deleteDoneTasks();
    }
}
