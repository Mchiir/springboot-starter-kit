package com.mchiir.starterkit.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mchiir.starterkit.exception.ErrorResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import java.io.IOException;

@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        log.warn(
                "Authentication failed at [{}]: {}",
                request.getRequestURI(),
                authException == null ? "n/a" : authException.getMessage()
        );

        ErrorResponse body = ErrorResponse.builder()
                .status(HttpServletResponse.SC_UNAUTHORIZED)
                .code("UNAUTHORIZED")
                .message(authException == null || authException.getMessage() == null
                        ? "Unauthorized" : authException.getMessage())
                .path(request.getRequestURI())
                .details(null)
                .build();

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        mapper.writeValue(response.getWriter(), body);
    }
}