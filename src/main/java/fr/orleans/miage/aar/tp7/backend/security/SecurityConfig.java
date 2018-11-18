package fr.orleans.miage.aar.tp7.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

   @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http
               .authorizeRequests()
               .antMatchers("/", "/login","/register").access("permitAll")
               .anyRequest()
                .fullyAuthenticated()
               .and()
               .formLogin()
               .loginPage("/login")
               .usernameParameter("username")
               .passwordParameter("password")
               .defaultSuccessUrl("/menu")
               .and()
               .logout()
               .logoutSuccessUrl("/")
               .and()
               .csrf()
               .disable();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled from client where username=?")
                .authoritiesByUsernameQuery("select username, roles from client, client_roles where (client.id=client_roles.client_id) and client.username=?")
                .passwordEncoder(encoder());
    }

}
