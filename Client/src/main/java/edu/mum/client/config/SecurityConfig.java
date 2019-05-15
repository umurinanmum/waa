package edu.mum.client.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity security) throws Exception
    {

        security
                .authorizeRequests()
                .antMatchers("/authorization/login").permitAll()
                .antMatchers("/authorization/do-login").permitAll()
                .antMatchers("/css/**").permitAll()
                .anyRequest().authenticated();
                //.and()
               // .formLogin()
                //.loginPage("/login");
                //.failureUrl("/welcome");
                //.and()
                //.logout()
                //.logoutSuccessUrl("/index.html");

      //  security.httpBasic().disable();
    }
}
