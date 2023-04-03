package com.aspire.blog.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.aspire.blog.service.BlogService;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	BlogService blogService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	     http
         .cors().configurationSource(corsConfigurationSource())
         .and()
         .csrf().disable()	
         .authorizeRequests()
         .anyRequest().authenticated()
         .and()
         .httpBasic();
	}
	
	 @Bean
	    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
	        CorsConfiguration configuration = new CorsConfiguration();
	        configuration.setAllowedOriginPatterns(Arrays.asList("http://localhost:4200", "http://localhost:8080")); // set allowed origin patterns
	        configuration.addAllowedMethod("*"); // allow all HTTP methods
	        configuration.addAllowedHeader("*"); // allow all headers
	        configuration.setAllowCredentials(true); // allow sending credentials (e.g., cookies)

	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", configuration);
	        return source;
	    }

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder().encode("admin")).roles("USER");
//		.and().withUser("user").password("admin").roles("USER","");
		auth.userDetailsService(blogService)
		.passwordEncoder(passwordEncoder());
	}

}
