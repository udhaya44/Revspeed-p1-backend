package com.revature.RevSpeed.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

// this all are in memory
// if you dont configure the user id and password in resourse then we can create configuration class
@Configuration
public class AppConfig {

//   @Bean
//   public UserDetailsService userDetailsService(){
//
//     UserDetails user= User.builder().username("swaraj").password(passwordEncoder().encode("swaraj@123")).roles("admin").build();
//      UserDetails user1= User.builder().username("krunal").password(passwordEncoder().encode("krunal@123")).roles("admin").build();
//
//      return new InMemoryUserDetailsManager(user,user1);   // this will create in memory user and store details to that
//   }

   @Bean
   public PasswordEncoder passwordEncoder(){
      return new BCryptPasswordEncoder();
   }


}
