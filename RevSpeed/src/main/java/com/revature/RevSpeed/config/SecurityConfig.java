package com.revature.RevSpeed.config;

import com.revature.RevSpeed.models.Role;
import com.revature.RevSpeed.security.JwtAuthenticationEntryPoint;
import com.revature.RevSpeed.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationEntryPoint point;
    @Autowired
    private JwtAuthenticationFilter filter;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // configure
        http.authorizeHttpRequests(
                        auth->
                                auth.requestMatchers("/admin/**").hasAnyAuthority(Role.ADMIN.name())
                                        .requestMatchers("/user/login").permitAll()
                                        .requestMatchers("/user/create-user").permitAll()
                                        .requestMatchers("/email/sendemail").permitAll()
                                        .requestMatchers("/broadbandplans/**").permitAll()
                                        .requestMatchers("/businessplans/**").permitAll()
                                        .requestMatchers("service/**").permitAll()
                                        .requestMatchers("userservicelink/**").permitAll()
                                        .requestMatchers("/email/**").permitAll().
                                        requestMatchers("/user/**").permitAll().
                                        requestMatchers("/swagger-ui.html", "/swagger-ui/**").permitAll()
                                        .requestMatchers("/actuator/**").permitAll()
                                        .anyRequest().authenticated())
                .exceptionHandling(ex->ex.authenticationEntryPoint(point))   // if any exception occers this will run
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // statless menas we are not storing any thing on server

        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
       DaoAuthenticationProvider daoAuthenticationProvider= new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
       return daoAuthenticationProvider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {  //
        return builder.getAuthenticationManager();
    }
}
