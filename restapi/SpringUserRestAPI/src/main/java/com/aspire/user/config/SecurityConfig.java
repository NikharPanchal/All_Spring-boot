package com.aspire.user.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.aspire.user.service.UserService;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
//@EnableGlobalMethodSecurity(prePostEnabled = false)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserService userService;
	
	@Autowired
	private JWTAuthenticationFilter jwtFilter;
	
//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//
//		registry.addMapping("/**").allowedMethods("*");
//	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeHttpRequests().anyRequest().authenticated().and().httpBasic();
//		http.csrf().disable();
//		http.headers().frameOptions().disable(); 

		
//		http.authorizeHttpRequests().antMatchers("/users","/token","/save","/user/*").permitAll().anyRequest().authenticated().and().httpBasic();
//		http.csrf().disable();

		
//		http.csrf().disable().cors().disable().authorizeRequests().antMatchers("/token").permitAll().anyRequest().authenticated().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		        http
		            .cors().configurationSource(corsConfigurationSource())
		            .and()
		            .csrf().disable()	
		            .authorizeRequests()
		            .antMatchers("/api/token").permitAll()
		            .anyRequest().authenticated()
		            .and()
		            .httpBasic()
		            .and()
		            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		        
		        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		
		        
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
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
//	@Bean
//	public CorsFilter corsFilter() {
//	    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//	    final CorsConfiguration config = new CorsConfiguration();
//	    config.setAllowCredentials(true);
//	    // Don't do this in production, use a proper list  of allowed origins
//	    config.setAllowedOrigins(Collections.singletonList("*"));
//	    config.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept"));
//	    config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH"));
//	    source.registerCorsConfiguration("/**", config);
//	    return new CorsFilter();
//	}

}
