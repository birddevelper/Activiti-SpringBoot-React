package com.example.tasksdashboard.controllers;

import com.example.tasksdashboard.models.TaskFormFields;
import com.example.tasksdashboard.services.UserTaskService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class TaskController {


    @Autowired
    private TaskService taskService;

    @Autowired
    private UserTaskService userTaskService;

    @GetMapping("/getTasks")
    public List<Map<String, String>> getTasksForUser(String userId) {
        List<Task> tasks = taskService.createTaskQuery()
                .taskAssignee(userId)
                .taskInvolvedUser(userId)
                .list();

        List<Map<String,String>> taskList = tasks.stream()
                .map(task -> {
                    Map<String, String> taskInfo = new HashMap<>();
                    taskInfo.put("taskId", task.getId());
                    taskInfo.put("taskName", task.getName());
                    taskInfo.put("formKey",task.getFormKey());
                    return taskInfo;
                })
                .collect(Collectors.toList());

                //.collect(Collectors.toMap(Task::getId, task -> task.getName() != null ? task.getName() : "no name"));
        return taskList;
    }



    @GetMapping("/{taskId}")
    public TaskFormFields getTaskFormFields(@PathVariable String taskId) {
        TaskFormFields taskFormFields = userTaskService.getTaskFormFields(taskId);
        return taskFormFields;
    }


}
