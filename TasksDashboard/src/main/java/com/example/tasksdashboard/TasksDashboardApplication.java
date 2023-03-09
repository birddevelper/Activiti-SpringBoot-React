package com.example.tasksdashboard;

import org.activiti.engine.FormService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.TaskService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.Normalizer;

@SpringBootApplication
public class TasksDashboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(TasksDashboardApplication.class, args);
    }



    @Bean
    public ProcessEngine processEngine() {
        ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration
                .createStandaloneProcessEngineConfiguration()
                .setJdbcUrl("jdbc:mysql://127.0.0.1:3306/activitytest?characterEncoding=UTF-8")
                .setJdbcDriver("com.mysql.jdbc.Driver")
                .setJdbcUsername("root")
                .setJdbcPassword("")
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

        return processEngineConfiguration.buildProcessEngine();
    }


    @Bean
    public TaskService taskService(ProcessEngine processEngine) {
        return processEngine.getTaskService();
    }

    @Bean
    public FormService formService(ProcessEngine processEngine) {
        return processEngine.getFormService();
    }

}
