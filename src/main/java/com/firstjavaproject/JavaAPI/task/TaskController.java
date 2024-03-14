package com.firstjavaproject.JavaAPI.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path="tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<TaskEntity> getTasksByIsDone(@RequestParam(name = "isdone", required = false) Boolean isDone) {
        if (isDone == null) {
            return this.taskService.getTasks();
        } else {
            return this.taskService.getTasksByIsDone(isDone);
        }
    }

    @PostMapping
    public TaskEntity addNewTask(@RequestBody TaskEntity newTask) {
        return this.taskService.addNewTask(newTask);
    }

    @PatchMapping("{id}")
    public TaskEntity updateTask(@PathVariable UUID id, @RequestBody TaskEntity updatedTask) {
        return this.taskService.updateTask(id, updatedTask);
    }

    @DeleteMapping("{id}")
    public void deleteTaskById(@PathVariable UUID id) {
        this.taskService.deleteTaskById(id);
    }
    @DeleteMapping
    public void deleteAllTasks() {
        this.taskService.deleteAllTasks();
    }

    @DeleteMapping("clear-done")
    public void deleteDoneTasks() {
        this.taskService.deleteDoneTasks();
    }
}
