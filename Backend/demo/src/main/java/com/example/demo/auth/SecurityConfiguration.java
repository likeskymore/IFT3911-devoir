package com.example.demo.auth;

import com.example.demo.config.ApplicationProperties;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

  private final ApplicationProperties applicationProperties;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(customizer -> {
      customizer
          .requestMatchers(HttpMethod.POST, "/api/users").permitAll()
          .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()

          .requestMatchers(
              "/swagger-ui/**",
              "/api-docs/**",
              "/swagger-ui.html"
          ).permitAll()

          .anyRequest().authenticated();
    });

    http.csrf(csrf -> {
      csrf.disable(); // TODO: We will implement CSRF protection later
    });

    http.cors(customizer -> {
      customizer.configurationSource(corsConfigurationSource());
    });

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  private CorsConfigurationSource corsConfigurationSource() {
    return new CorsConfigurationSource() {
      @Override
      public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(applicationProperties.getAllowedOrigins());
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);
        return config;
      }
    };
  }
}