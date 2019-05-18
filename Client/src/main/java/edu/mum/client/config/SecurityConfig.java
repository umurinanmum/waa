package edu.mum.client.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Bean
//    public AccessDeniedHandler accessDeniedHandler() {
//        return new WaaAccessDeniedHandler();
//    }
//
//    @Bean
//    public AuthenticationEntryPoint authenticationEntryPoint() {
//        return new WaaForbiddenEntryPoint();
//    }

    @Override
    protected void configure(HttpSecurity security) throws Exception {

        security.authorizeRequests().antMatchers("/**").permitAll()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().csrf().disable();


        //  security
        //   .authorizeRequests()
//                .antMatchers("/authorization/login").permitAll()
//                .antMatchers("/fileUpload/show").permitAll()
//                .antMatchers("/fileUpload/uploadFile").permitAll()
//                .antMatchers("/authorization/do-login").permitAll()
//                .antMatchers("/css/**").permitAll()
//                .anyRequest().authenticated()

        //   .and()
        //        .exceptionHandling().accessDeniedHandler(accessDeniedHandler())
        //      .and()
        //    .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint());
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
