package com.sukhachev.testTaskApril.security;

import com.sukhachev.testTaskApril.handler.LoginSuccessHandler;
import com.sukhachev.testTaskApril.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//Класс-конфигурация Spring Security
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userDetailsService;
    @Autowired
    private LoginSuccessHandler loginSuccessHandler;


    @Override
    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    //Отвечает за настройки безопасности приложения
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()

                .successHandler(loginSuccessHandler)

                .permitAll();

        //Дает доступ с страницам
        http.authorizeRequests()
                .antMatchers("/users/**").authenticated()
                .and().httpBasic().and().csrf().disable();


    }

    //Шифрует пароль, сейчас шифрование выключено
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}