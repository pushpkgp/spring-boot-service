package com.dell.service;

import com.dell.service.dao.AppDAO;
import com.dell.service.core.AppCore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

/**
 * Service entry point.
 */
@ComponentScan("com.dell.service")
@SpringBootApplication
public class AppMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(AppMain.class, args);
        AppCore appCore = (AppCore) applicationContext.getBean("appCore");
        List<AppDAO> list = appCore.bufferedReader().serviceBuilder();
        list.forEach(appDAO -> System.out.print("\n" + appDAO.getService()
                + "\nFacility Name: " + appDAO.getFacilityName()
                + "; Coordinates: " + appDAO.getLocation() + "\n"));
    }
}