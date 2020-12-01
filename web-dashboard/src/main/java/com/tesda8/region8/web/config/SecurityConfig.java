package com.tesda8.region8.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder =
                PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth
                .inMemoryAuthentication()
                .withUser("planning_officer")
                .password(encoder.encode("tesda8"))
                .roles("PLANNING")
                .and()
                .withUser("rd")
                .password(encoder.encode("tesda8"))
                .roles("USER", "ADMIN", "CERTIFICATION")
                .and()
                .withUser("ro_officer")
                .password(encoder.encode("tesda8"))
                .roles("USER", "ADMIN", "CERTIFICATION")
                .and()
                .withUser("payroll")
                .password(encoder.encode("tesda8"))
                .roles("PAYROLL")
                .and()
                .withUser("prog_reg")
                .password(encoder.encode("tesda8"))
                .roles("PROGRAM_REG")
                .and()
                .withUser("scholarship")
                .password(encoder.encode("tesda8"))
                .roles("SCHOLARSHIP")
                .and()
                .withUser("certification")
                .password(encoder.encode("tesda8"))
                .roles("CERTIFICATION")
                .and()
                .withUser("supply")
                .password(encoder.encode("tesda8"))
                .roles("SUPPLY");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/dashboard", "/monthly", "/api/**", "/tableData/**", "/registeredPrograms/**", "/home", "/favicon.ico").permitAll()
                .antMatchers("/planning/**").hasAnyRole("ADMIN", "PLANNING")
                .antMatchers("/certification/**").hasAnyRole("CERTIFICATION", "ADMIN")
                .antMatchers("/monthlyReports/**", "/dailyReports/**").hasAnyRole("ADMIN", "PLANNING", "CERTIFICATION")
                .antMatchers("/supply/**").hasAnyRole("SUPPLY")
                .antMatchers("/payroll/**").hasAnyRole("PAYROLL")
                .antMatchers("/program_registration/**").hasAnyRole("PROGRAM_REG", "ADMIN")
                .antMatchers("/scholarship/**").hasAnyRole("SCHOLARSHIP", "ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/webjars/**");
        web.ignoring().antMatchers("/css/**","/fonts/**","/libs/**", "/images/**", "/static/**", "/js/**");

    }
}