package com.chrishall.expensetrackerapi.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import com.chrishall.expensetrackerapi.util.JwtTokenUtil;

import io.jsonwebtoken.ExpiredJwtException;

public class JwtRequestFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String requestTokenHeader = request.getHeader("Authorization");
		
		String jwtToken = null;
		String username = null;
		
		if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer")) {
			
			 jwtToken = requestTokenHeader.substring(7);
			 
			 try {
				 
			 } catch (IllegalArgumentException e) {
				 throw new RuntimeException("Unable to get JWT token");
			 } catch (ExpiredJwtException e) {
				 throw new RuntimeException("Jwt token has expired");
			 }
		}

	}

}
