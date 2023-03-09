package com.example.tasksdashboard.models;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.activiti.form.model.FormField;

import java.util.List;

@Getter
@Setter
@Data
public class TaskFormFields {

    private String taskId;
    private String taskName;
    private List<FormField> formFields;


}