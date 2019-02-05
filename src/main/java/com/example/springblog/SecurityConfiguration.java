package com.example.springblog;

import com.example.springblog.services.UserDetailsLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  private UserDetailsLoader usersLoader;

  public SecurityConfiguration(UserDetailsLoader usersLoader) {
    this.usersLoader = usersLoader;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
    return new MySimpleUrlAuthenticationSuccessHandler();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
        .userDetailsService(usersLoader) // How to find users by their username
        .passwordEncoder(passwordEncoder()) // How to encode and verify passwords
    ;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        /* Login configuration */
        .formLogin()
        .loginPage("/login")
        .successHandler(myAuthenticationSuccessHandler())
        .defaultSuccessUrl("/") // user's home page, it can be any URL
        .permitAll() // Anyone can go to the login page
        /* Logout configuration */
        .and()
        .logout()
        .logoutSuccessUrl("/login?logout") // append a query string value
        /* Pages that can be viewed without having to log in */
        .and()
        .authorizeRequests()
        .antMatchers("/", "/sign-up", "/products") // anyone can see the home and the ads pages
        .permitAll()
        /* Pages that require authentication */
        .and()
        .authorizeRequests()
        .antMatchers(
            "/products/create", // only authenticated users can create ads
            "/products/{id}/edit",
            "/products/{id}/delete"// only authenticated users can edit ads
        )
        .authenticated()
    ;
  }
}
