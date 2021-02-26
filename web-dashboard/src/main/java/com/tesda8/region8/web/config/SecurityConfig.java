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

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("planning")
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
                .withUser("quality")
                .password(passwordEncoder().encode("tesda8"))
                .roles("QUALITY")
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
                .withUser("leyte_po")
                .password(passwordEncoder().encode("tesda8"))
                .roles("LEYTE_PO")
                .and()
                .withUser("samar_po")
                .password(passwordEncoder().encode("tesda8"))
                .roles("SAMAR_PO")
                .and()
                .withUser("biliran_po")
                .password(passwordEncoder().encode("tesda8"))
                .roles("BILIRAN_PO")
                .and()
                .withUser("so_leyte_po")
                .password(passwordEncoder().encode("tesda8"))
                .roles("SOUTHERN_LEYTE_PO")
                .and()
                .withUser("e_samar_po")
                .password(passwordEncoder().encode("tesda8"))
                .roles("EASTERN_SAMAR_PO")
                .and()
                .withUser("n_samar_po")
                .password(passwordEncoder().encode("tesda8"))
                .roles("NORTHERN_SAMAR_PO")
                .and()
                .withUser("rtc")
                .password(passwordEncoder().encode("tesda8"))
                .roles("RTC_TTI")
                .and()
                .withUser("cnvs")
                .password(passwordEncoder().encode("tesda8"))
                .roles("CNVS_TTI")
                .and()
                .withUser("ptc_leyte")
                .password(passwordEncoder().encode("tesda8"))
                .roles("PTC_LEYTE_TTI")
                .and()
                .withUser("cnsat")
                .password(passwordEncoder().encode("tesda8"))
                .roles("CNSAT_TTI")
                .and()
                .withUser("ptc_biliran")
                .password(passwordEncoder().encode("tesda8"))
                .roles("PTC_BILIRAN_TTI")
                .and()
                .withUser("ptc_so_leyte")
                .password(passwordEncoder().encode("tesda8"))
                .roles("PTC_SO_LEYTE_TTI")
                .and()
                .withUser("ptc_samar")
                .password(passwordEncoder().encode("tesda8"))
                .roles("PTC_SAMAR_TTI")
                .and()
                .withUser("ptc_e_samar")
                .password(passwordEncoder().encode("tesda8"))
                .roles("PTC_E_SAMAR_TTI")
                .and()
                .withUser("snsat")
                .password(passwordEncoder().encode("tesda8"))
                .roles("SNSAT_TTI")
                .and()
                .withUser("bnas")
                .password(passwordEncoder().encode("tesda8"))
                .roles("BNAS_TTI")
                .and()
                .withUser("anas")
                .password(passwordEncoder().encode("tesda8"))
                .roles("ANAS_TTI")
                .and()
                .withUser("bcat")
                .password(passwordEncoder().encode("tesda8"))
                .roles("BCAT_TTI")
                .and()
                .withUser("ptc_n_samar")
                .password(passwordEncoder().encode("tesda8"))
                .roles("PTC_N_SAMAR_TTI")
                .and()
                .withUser("lnais")
                .password(passwordEncoder().encode("tesda8"))
                .roles("LNAIS_TTI");
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/customer_satisfaction/**", "/dashboard/**", "/oauth/token", "/api/graph/**", "/tableData/**", "/home", "/favicon.ico").permitAll()
                .antMatchers("/planning", "/planning/opcr/**").hasAnyRole("ADMIN", "PLANNING", "LEYTE_PO", "SAMAR_PO", "EASTERN_SAMAR_PO", "BILIRAN_PO", "SOUTHERN_LEYTE_PO", "NORTHERN_SAMAR_PO", "BCAT_TTI", "RTC_TTI", "CNVS_TTI", "PTC_LEYTE_TTI", "CNSAT_TTI", "PTC_BILIRAN_TTI", "PTC_SO_LEYTE_TTI", "PTC_SAMAR_TTI", "PTC_E_SAMAR_TTI", "SNSAT_TTI", "BNAS_TTI", "ANAS_TTI", "PTC_N_SAMAR_TTI", "LNAIS_TTI")
                .antMatchers("/planning/successIndicator/**", "/planning/pap/**").hasAnyRole("ADMIN", "PLANNING")
                .antMatchers("/certification/**").hasAnyRole("CERTIFICATION", "ADMIN")
                .antMatchers("/monthlyReports/**", "/dailyReports/**").hasAnyRole("ADMIN", "PLANNING", "CERTIFICATION")
                .antMatchers("/supply/**").hasAnyRole("SUPPLY")
                .antMatchers("/payroll/**").hasAnyRole("PAYROLL")
                .antMatchers("/program_registration/**").hasAnyRole("PROGRAM_REG", "ADMIN")
                .antMatchers("/scholarship/**").hasAnyRole("SCHOLARSHIP", "ADMIN")
                .antMatchers("/quality/**").hasAnyRole("QUALITY", "ADMIN")
                .antMatchers("/audit/**").hasAnyRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .successHandler(loginSuccessHandler)
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