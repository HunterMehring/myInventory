package org.launchcode.myInventory.config;

import org.launchcode.myInventory.models.data.UserDao;
import org.launchcode.myInventory.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDao userDao;


    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{

        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //need an error message if username or password is not correct
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/admin/**", "/category/**", "/changes/**", "/changes/**",
                        "/item/**", "/players/**", "/search/**", "/tasks/**", "/login/**", "/home/**").authenticated()
                .anyRequest().permitAll()
                .and().formLogin()
                  .loginPage("/login")
                  .successForwardUrl("/home")
                  .usernameParameter("name")
                  .passwordParameter("password")
                  .permitAll();
    }

    //put a real login page in formLogin()

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
