package com.nerdware.classroomapis.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final ApplcationDetailService applcationDetailService;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder, ApplcationDetailService applcationDetailService) {
        this.passwordEncoder = passwordEncoder;
        this.applcationDetailService = applcationDetailService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(applcationDetailService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/student/**").hasAuthority("STUDENT")
                .antMatchers("/api/parent/**").hasAuthority("PARENT")
                .antMatchers("/api/teacher/**").hasAuthority("TEACHER")
                .antMatchers("/", "index", "/css/*", "/js/*", "/swagger-ui.html", "/swagger-ui/index.html").permitAll()
                .antMatchers("/api/subject/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

}