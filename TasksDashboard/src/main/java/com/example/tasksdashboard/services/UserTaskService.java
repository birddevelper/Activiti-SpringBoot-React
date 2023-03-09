package com.example.tasksdashboard.services;

import com.example.tasksdashboard.models.TaskFormFields;
import org.activiti.engine.FormService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.FormType;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.impl.form.*;
import org.activiti.engine.task.Task;
import org.activiti.form.model.FormField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserTaskService {


    @Autowired
    private org.activiti.engine.TaskService taskService;

    @Autowired
    FormService formService;


    public TaskFormFields getTaskFormFields(String taskId) {
        TaskFormData taskFormData = formService.getTaskFormData(taskId);

        List<FormField> formFields = new ArrayList<>();
        List<FormProperty> formProperties = taskFormData.getFormProperties();
        for (FormProperty formProperty : formProperties) {
            FormField formField = new FormField();
            formField.setId(formProperty.getId());
            formField.setName(formProperty.getName());
            formField.setType(getFormFieldType(formProperty.getType()));
            formField.setRequired(formProperty.isRequired());
            formField.setReadOnly(formProperty.isReadable());
            formField.setValue(formProperty.getValue());
            formFields.add(formField);
        }



        Task task = taskService.createTaskQuery().taskId(taskId.toString()).singleResult();
        TaskFormFields taskFormFields = new TaskFormFields();
        taskFormFields.setTaskId(taskId);
        taskFormFields.setTaskName(task.getName());
        taskFormFields.setFormFields(formFields);

        return taskFormFields;
    }


    // Method to map the Activiti FormProperty types to FormField types
    private static String getFormFieldType(FormType activitiType) {
        if (activitiType == null) {
            return null;
        } else if (activitiType.equals(StringFormType.class.getName())) {
            return "string";
        } else if (activitiType.equals(LongFormType.class.getName())) {
            return "long";
        } else if (activitiType.equals(DoubleFormType.class.getName())) {
            return "double";
        } else if (activitiType.equals(BooleanFormType.class.getName())) {
            return "boolean";
        } else if (activitiType.equals(DateFormType.class.getName())) {
            return "date";
        } else if (activitiType.equals(EnumFormType.class.getName())) {
            return "enum";
        } else {
            return "unkown";
        }
    }


}
