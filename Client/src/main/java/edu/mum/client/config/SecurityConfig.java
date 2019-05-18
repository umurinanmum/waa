package edu.mum.client.config;


<<<<<<< HEAD
import org.springframework.context.annotation.Bean;
=======
>>>>>>> master
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
<<<<<<< HEAD
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
=======
>>>>>>> master

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
<<<<<<< HEAD
        .and().csrf().disable();


      //  security
                //   .authorizeRequests()
=======
                .and().csrf().disable();


        //  security
        //   .authorizeRequests()
>>>>>>> master
//                .antMatchers("/authorization/login").permitAll()
//                .antMatchers("/fileUpload/show").permitAll()
//                .antMatchers("/fileUpload/uploadFile").permitAll()
//                .antMatchers("/authorization/do-login").permitAll()
//                .antMatchers("/css/**").permitAll()
//                .anyRequest().authenticated()

<<<<<<< HEAD
                //   .and()
        //        .exceptionHandling().accessDeniedHandler(accessDeniedHandler())
          //      .and()
            //    .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint());
=======
        //   .and()
        //        .exceptionHandling().accessDeniedHandler(accessDeniedHandler())
        //      .and()
        //    .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint());
>>>>>>> master
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
