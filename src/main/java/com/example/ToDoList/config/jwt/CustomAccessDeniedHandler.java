package com.example.ToDoList.config.jwt;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
private static final Logger logger = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);

@Override
public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
        throws IOException, ServletException {
    logger.error("Access Denied: {}", accessDeniedException.getMessage());
    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    response.getWriter().write("You are not authorized to access this resource.");
}
}
