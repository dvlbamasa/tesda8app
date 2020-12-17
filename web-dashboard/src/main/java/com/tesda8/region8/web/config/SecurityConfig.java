package com.tesda8.region8.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("planning_officer")
                .password(passwordEncoder().encode("tesda8"))
                .roles("PLANNING")
                .and()
                .withUser("rd")
                .password(passwordEncoder().encode("tesda8"))
                .roles("ADMIN")
                .and()
                .withUser("rod_chief")
                .password(passwordEncoder().encode("tesda8"))
                .roles("ADMIN")
                .and()
                .withUser("payroll")
                .password(passwordEncoder().encode("tesda8"))
                .roles("PAYROLL")
                .and()
                .withUser("prog_reg")
                .password(passwordEncoder().encode("tesda8"))
                .roles("PROGRAM_REG")
                .and()
                .withUser("scholarship")
                .password(passwordEncoder().encode("tesda8"))
                .roles("SCHOLARSHIP")
                .and()
                .withUser("certification")
                .password(passwordEncoder().encode("tesda8"))
                .roles("CERTIFICATION")
                .and()
                .withUser("supply")
                .password(passwordEncoder().encode("tesda8"))
                .roles("SUPPLY");
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/dashboard/**", "/oauth/token", "/api/graph/**", "/tableData/**", "/home", "/favicon.ico").permitAll()
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