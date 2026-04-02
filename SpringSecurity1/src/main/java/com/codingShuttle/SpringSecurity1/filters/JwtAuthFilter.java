package com.codingShuttle.SpringSecurity1.filters;


import com.codingShuttle.SpringSecurity1.entities.UserEntity;
import com.codingShuttle.SpringSecurity1.services.UserService;
import com.codingShuttle.SpringSecurity1.services.impl.JwtServiceImpl;
import com.codingShuttle.SpringSecurity1.services.impl.SessionServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;


@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    Logger log = LoggerFactory.getLogger(JwtAuthFilter.class);

    private final JwtServiceImpl jwtServiceImpl;
    private final UserService userService;
    private final SessionServiceImpl sessionServiceImpl;
    private final HandlerExceptionResolver handlerExceptionResolver;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            log.info("JwtAuthFilter doFilterInternal");
            final String authorizationHeader = request.getHeader("Authorization");
            if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }
            String token = authorizationHeader.split("Bearer ")[1];
            log.info("JwtAuthFilter token:  {}", token);

            Long userId = jwtServiceImpl.getUserIdFromJwtToken(token);
            log.info("JwtAuthFilter userId:  {}", userId);

            UserEntity user = userService.getUserByUserId(userId);
            Boolean isTokenValid = sessionServiceImpl.checkTokenValid(user, token);

            if (!isTokenValid) {
                log.warn("Token not found in DB - session may have been invalidated");
                handlerExceptionResolver.resolveException(
                        request,
                        response,
                        null,
                        new BadCredentialsException("Invalid Token or session expired, Please login again.")
                );
                return;
            }

            if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, null);
                authenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                filterChain.doFilter(request, response);
                log.info("Response {}", response);
                log.info("Authentication Success");
            }

        } catch (ExpiredJwtException e) {
            log.warn("Token not found in DataBase - session may have been invalidated");
            handlerExceptionResolver.resolveException(request, response, null,
                    new BadCredentialsException("Invalid Token or session expired, Please login again."));

        } catch (Exception e) {
            handlerExceptionResolver.resolveException(request, response, null, e);
        }
    }
}
