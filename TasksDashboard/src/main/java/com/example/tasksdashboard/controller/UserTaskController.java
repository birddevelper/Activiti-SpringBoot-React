package com.example.tasksdashboard.controller;


import org.activiti.engine.FormService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.impl.util.json.JSONArray;
import org.activiti.engine.impl.util.json.JSONObject;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/userTask")
public class UserTaskController {

//    @Autowired
//    private FormService formService;

    @GetMapping("/{processId}/{taskId}/form")
    public String getTaskFormJson(@PathVariable String taskId, @PathVariable String processId ) {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        Task task = processEngine.getTaskService().createTaskQuery()
                .processInstanceId(processId)
                .taskId(taskId)
                .singleResult();

        FormService formService = processEngine.getFormService();

        TaskFormData formData = formService.getTaskFormData(taskId);
        List<FormProperty> formProperties = formData.getFormProperties();
        JSONArray formJson = new JSONArray();
        for (FormProperty property : formProperties) {
            JSONObject fieldJson = new JSONObject();
            fieldJson.put("name", property.getId());
            fieldJson.put("label", property.getName());
            fieldJson.put("type", property.getType().getName());
            fieldJson.put("required", property.isRequired());
            formJson.put(fieldJson);
        }
        return formJson.toString();
    }

    // other controller methods ...
}
