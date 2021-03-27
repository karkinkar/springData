package com.example.demo.configuration;

import com.example.demo.configuration.security.AppSecurityRole;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class AppSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${spring.datasource.username}")
    private String datausername;

    @Value("${spring.datasource.password}")
    private String datapassword;

    @Value("${spring.datasource.url}")
    private String dataUrl;

    private final PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        var username = "ravi";
        var user = User.builder()
                .username(username)
                .password(passwordEncoder.encode(username))
                .roles(AppSecurityRole.READER.name())
                .build();

        var adminName = "admin";
        var admin = User.builder()
                .username(adminName)
                .password(passwordEncoder.encode(adminName))
                .roles(AppSecurityRole.ADMIN.name())
                .build();

        var userManager = new JdbcUserDetailsManager();
        userManager.setDataSource(
                DataSourceBuilder.create()
                        .username(datausername)
                        .password(datapassword)
                        .driverClassName("org.postgresql.Driver")
                        .url(dataUrl)
                        .build());

        userManager.createUser(user);
        userManager.createUser(admin);
        return userManager;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/user/listUsers").hasRole(AppSecurityRole.READER.name())
                .antMatchers("/user/createUser").hasRole(AppSecurityRole.ADMIN.name())
                .antMatchers("/user/defaultS").hasRole(AppSecurityRole.ADMIN.name());
    }
}
