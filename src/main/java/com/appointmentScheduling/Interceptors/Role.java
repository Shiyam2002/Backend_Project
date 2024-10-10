package com.appointmentScheduling.Interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;

@Component
public class Role {

   // @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false); // Do not create a session if it doesn't exist
        String userRole = (session != null) ? (String) session.getAttribute("role") : null;

        if (userRole == null || !"ADMIN".equals(userRole)) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN); // 403 Forbidden
            response.setContentType("application/json");
            PrintWriter writer = response.getWriter();
            writer.write("{\"message\": \"Access Denied. Only admins can access this resource.\"}");
            writer.flush();
            return false; // Prevent access to the service
        }
        return true;
    }

}