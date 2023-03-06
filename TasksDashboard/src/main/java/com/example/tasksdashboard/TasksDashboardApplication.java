package com.example.tasksdashboard;

import org.activiti.engine.FormService;
import org.activiti.engine.impl.FormServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TasksDashboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(TasksDashboardApplication.class, args);
    }

//    @Bean
//    FormService getFormService(){
//        return new FormServiceImpl();
//    }
}
