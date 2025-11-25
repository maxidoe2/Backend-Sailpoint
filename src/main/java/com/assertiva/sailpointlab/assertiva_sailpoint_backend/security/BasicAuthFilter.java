package com.assertiva.sailpointlab.assertiva_sailpoint_backend.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Base64;

@Component
public class BasicAuthFilter implements Filter {

    @Value("${app.auth.username}")
    private String validUser;

    @Value("${app.auth.password}")
    private String validPass;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String authHeader = req.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Basic ")) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // Decode credentials
        String base64Credentials = authHeader.substring("Basic ".length());
        String credentials = new String(Base64.getDecoder().decode(base64Credentials));
        String[] values = credentials.split(":", 2);

        String user = values[0];
        String pass = values[1];

        if (!user.equals(validUser) || !pass.equals(validPass)) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        chain.doFilter(request, response);
    }
}
