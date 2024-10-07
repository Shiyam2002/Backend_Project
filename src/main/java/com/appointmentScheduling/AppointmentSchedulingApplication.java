package com.appointmentScheduling;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class AppointmentSchedulingApplication {

	static Logger log = Logger.getLogger(AppointmentSchedulingApplication.class);

	public static void main(String[] args) {
		PropertyConfigurator.configure("C:\\Users\\ramya\\Desktop\\shiyam\\Project\\appointmentScheduling\\src\\properties\\log4j.properties");
		ApplicationContext content = SpringApplication.run(AppointmentSchedulingApplication.class, args);
		log.info("Application started");
	}
}
