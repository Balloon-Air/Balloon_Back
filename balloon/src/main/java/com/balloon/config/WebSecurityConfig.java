package com.balloon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.balloon.jwt.JwtAccessDeniedHandler;
import com.balloon.jwt.JwtAuthenticationEntryPoint;
import com.balloon.jwt.TokenProvider;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@Component
public class WebSecurityConfig implements WebMvcConfigurer {

	private final TokenProvider tokenProvider;
	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
	private final long MAX_AGE_SECS = 3600;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
//                .httpBasic().disable()

				.csrf().disable().sessionManagement(session -> session.maximumSessions(2).maxSessionsPreventsLogin(true)
						.expiredUrl("/login?exprie=true"))
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		;

		http.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
				.accessDeniedHandler(jwtAccessDeniedHandler);


		http.authorizeRequests()
    
				.antMatchers(HttpMethod.POST, "/auth/login").permitAll().antMatchers(HttpMethod.GET, "/api/unit/**")
				.permitAll().antMatchers(HttpMethod.POST, "/auth/**").hasRole("ADMIN")
				.antMatchers(HttpMethod.POST, "/api/unitlist").hasRole("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/api/unit/**").hasRole("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/employee/**").hasRole("ADMIN").anyRequest().authenticated();

		http.logout().permitAll();

		http.exceptionHandling().accessDeniedPage("/accesDenied");
		http.apply(new JwtSecurityConfig(tokenProvider));

		return http.build();
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("http://localhost:3000")
				.allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS").allowedHeaders("*")
				.allowCredentials(true).maxAge(MAX_AGE_SECS);
	}

}