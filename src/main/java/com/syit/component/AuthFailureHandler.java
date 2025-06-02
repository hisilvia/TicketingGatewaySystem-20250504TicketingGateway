package com.syit.component;

import java.io.IOException;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		String errorMessage = "Invalid username or password";
        if (exception instanceof UsernameNotFoundException) {
            errorMessage = "User not found";
        } else if (exception instanceof BadCredentialsException) {
            errorMessage = "Invalid password";
        }
        System.out.println("errorMessage: "+errorMessage);
        response.sendRedirect("/login?error=" + errorMessage);
		
	}

}
