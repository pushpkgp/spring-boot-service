package com.dell.service.configurations;

import com.dell.service.dao.AppDAO;
import com.dell.service.core.AppCore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfiguration {
    @Value("${input.file.name}")
    private String inputFileName;

    @Bean
    public AppDAO.Builder serviceDAOBuilder() {
        return new AppDAO.Builder();
    }

    @Bean
    public AppCore appCore(AppDAO.Builder appDAOBuilder){
        return new AppCore(inputFileName, appDAOBuilder);
    }
}