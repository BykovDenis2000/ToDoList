package com.example.demo.config;

import com.example.demo.model.User;
import com.example.demo.repos.UserRepo;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .mvcMatchers("/","/registration","/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").defaultSuccessUrl("/notes").permitAll();


    }


    @Bean
    public PrincipalExtractor principalExtractor(UserRepo userRepo){
        return map -> {
            String id = (String) map.get("sub");
            User user = userRepo.findById(id).orElseGet(() ->{
                User newUser = new User();
                newUser.setUserId(id);
                newUser.setName((String) map.get("name"));
                newUser.setEmail((String) map.get("email"));
                newUser.setGender((String) map.get("gender"));
                newUser.setLocale((String) map.get("locale"));

                return newUser;
            });
            return userRepo.save(user);
        };
    }
}
