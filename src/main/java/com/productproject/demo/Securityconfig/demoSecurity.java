package com.productproject.demo.Securityconfig;

// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import javax.sql.DataSource;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.context.annotation.Configuration;

@Configuration
public class demoSecurity {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select peoplename,password,enabled from peoples where peoplename=?");
        
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select peoplename, authority from authorities where peoplename=?");
                  
        return jdbcUserDetailsManager;   
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{

        http
            .authorizeHttpRequests(configurer ->
                configurer
                    
                    .requestMatchers(HttpMethod.POST, "/api/store/**").hasRole("USER")
                    .requestMatchers(HttpMethod.POST, "/api/store/**").hasRole("MANAGER")
                    .requestMatchers(HttpMethod.PUT, "/api/store/**").hasRole("MANAGER")
                    .requestMatchers(HttpMethod.GET, "/api/store/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/api/store/**").hasRole("ADMIN")
                    
                    .requestMatchers(HttpMethod.GET, "/api/access/**").hasAnyRole("ADMIN", "USER", "MANAGER")
                   
                    .anyRequest().authenticated()
            );

        http.httpBasic(Customizer.withDefaults());
        
        http.csrf(csrf -> csrf.disable());
        return http.build(); 
    }

        
    }
    



