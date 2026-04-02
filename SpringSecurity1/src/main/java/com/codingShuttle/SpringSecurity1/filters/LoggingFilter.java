package com.codingShuttle.SpringSecurity1.filters;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class LoggingFilter extends OncePerRequestFilter {

    Logger log = LoggerFactory.getLogger(LoggingFilter.class);

    private final HandlerExceptionResolver handlerExceptionResolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            log.info("LoggingFilter doFilterInternal start");
            log.info("LoggingFilter doFilterInternal request:  {}", request);
            log.info("Incoming Request  --> Method: {}", request.getMethod());
            log.info("Incoming Request  --> URI: {}", request.getRequestURI());
            log.info("Incoming Request  --> Authorization: {}", request.getHeaders("Authorization"));
            filterChain.doFilter(request, response);
            log.info("LoggingFilter doFilterInternal response: {}", response);
            log.info("Outgoing Response  --> Status: {}", response.getStatus());
            log.info("LoggingFilter doFilterInternal end");
        } catch (Exception e) {
            handlerExceptionResolver.resolveException(request, response, null, e);
        }
    }
}
