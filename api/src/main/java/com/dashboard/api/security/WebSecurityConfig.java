package com.dashboard.api.security;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.dashboard.api.security.jwt.EntryPointJwt;
import com.dashboard.api.security.jwt.TokenFilter;
import com.dashboard.api.security.services.CusUsersDetailsService;

/**
 * WebSecurityConfiguration
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig{

  @Value("${spring.h2.console.path}")
  private String h2ConsolePath;

  @Autowired
  CusUsersDetailsService userDetailsService;

  @Autowired
  private EntryPointJwt uEntryPointJwt;

  @Bean
  public TokenFilter auTokenFilter() {
    return new TokenFilter();
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
       
    authProvider.setUserDetailsService(userDetailsService);
    authProvider.setPasswordEncoder(passwordEncoder());
   
    return authProvider;
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
    return authConfig.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
  
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.cors().and().csrf().disable().exceptionHandling().authenticationEntryPoint(uEntryPointJwt)
    .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    .and().authorizeRequests().antMatchers("/**").permitAll()
    .antMatchers(h2ConsolePath+"/**").permitAll()
    .anyRequest().authenticated();

    http.headers().frameOptions().sameOrigin();
    http.authenticationProvider(authenticationProvider());
    http.addFilterBefore(auTokenFilter(), UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }
}