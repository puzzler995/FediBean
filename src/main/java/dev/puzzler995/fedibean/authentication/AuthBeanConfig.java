package dev.puzzler995.fedibean.authentication;

import dev.puzzler995.fedibean.authentication.modules.DatabaseUserDetailsService;
import dev.puzzler995.fedibean.data.modules.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AuthBeanConfig {
  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public UserDetailsService userDetailsService(UserService userService) {
    return new DatabaseUserDetailsService(userService);
  }

  @Bean
  SecurityFilterChain web(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated())
        .httpBasic(Customizer.withDefaults())
        .csrf(AbstractHttpConfigurer::disable) //TODO: Figure out how to actually setup security
        .formLogin(Customizer.withDefaults());
    return http.build();
  }
}
