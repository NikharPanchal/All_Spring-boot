package com.aspire.user.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.aspire.user.service.UserService;
import com.aspire.user.utils.JwtToken;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter  {

	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtAuthentication jwtUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		
//		step 1: GetToken
//		step 2: Bearer
//		Step 3: Validate Token
		String requestTokenHeader=request.getHeader("Authorization");
		String username=null;
		String jwtToken=null;
		
		if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken=requestTokenHeader.substring(7);
			try {
				username=this.jwtUtil.extractUsername(jwtToken);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			UserDetails userDetails=this.userService.loadUserByUsername(username);
			
			if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
				
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
			else
			{
				System.out.println("Token is not valid");
			}
		}
		filterChain.doFilter(request, response);
	}

}
