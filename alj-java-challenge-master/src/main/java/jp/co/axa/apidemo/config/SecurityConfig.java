package jp.co.axa.apidemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
            .antMatchers(HttpMethod.GET, "/api/v1/employees/getAll").hasAnyRole("ADMIN", "USER")
            .antMatchers(HttpMethod.GET, "/api/v1/employees/{employeeId}").hasAnyRole("ADMIN", "USER")
            .antMatchers(HttpMethod.POST, "/api/v1/employees/save").hasRole("ADMIN")
            .antMatchers(HttpMethod.DELETE, "/api/v1/employees/{employeeId}").hasRole("ADMIN")
            .antMatchers(HttpMethod.PUT, "/api/v1/employees/{employeeId}").hasRole("ADMIN")
            .antMatchers("/api/v1/employees/**").authenticated()
            .anyRequest().permitAll()
            .and().httpBasic();
    }
    
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN")
            .and()
            .withUser("user").password(passwordEncoder().encode("user")).roles("USER");
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
