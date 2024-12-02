package com.appointmentScheduling.Configuration;

import com.appointmentScheduling.Interceptors.HeaderValidation;
import com.appointmentScheduling.Interceptors.Logging;
import com.appointmentScheduling.Interceptors.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {

    @Autowired
    HeaderValidation headerValidationInterceptor;
    @Autowired
    Logging loggingInterceptor;
    @Autowired
    Role roleInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(headerValidationInterceptor).addPathPatterns("/**").order(2);
        registry.addInterceptor(loggingInterceptor).order(1);
        registry.addInterceptor(roleInterceptor).addPathPatterns("/api/patient/signup","/api/patient/patientList", "/api/patient/deletePatient/**", "/api/patient/updatePatient/**",
                "/api/address/create", "/api/address/delete").order(3);
    }

}
