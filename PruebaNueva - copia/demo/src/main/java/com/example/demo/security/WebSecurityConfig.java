package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import lombok.AllArgsConstructor;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@AllArgsConstructor
public class WebSecurityConfig {


    private final JWTAuthorizationFilter jaf;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, AuthenticationManager atm) throws Exception{

        JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter();
        jwtAuthenticationFilter.setAuthenticationManager(atm);
        jwtAuthenticationFilter.setFilterProcessesUrl("/login");

        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(requests -> requests
                        .anyRequest()
                        .authenticated())
                //EL QUE TENGA HUEVOS QUE BORRE LA LINEA 43
                .httpBasic(withDefaults()) //YO SOY LA LINEA 43
                .sessionManagement(management -> management
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilter(jwtAuthenticationFilter)
                .addFilterBefore(jaf, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    

    @Bean
    UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("admin")
        .password("$2a$10$xpLO3kbYcWpI1jAsa651Je6fcFq2TcD.bzXDAENjvVs3foNKz89zq")
        .roles()
        .build());

        manager.createUser(User.withUsername("smartinezs2")
        .password("$2a$10$pqBZYGHxVEBZCi51vu2vaexULc3ORHUVP4vTdJ.jjVOzCUafMj0R2")
        .roles()
        .build());

        return manager;
    }


    @Bean
    public AuthenticationManager authenticationManager(PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userDetailsService);
        return new ProviderManager(provider);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
        //BCryptPasswordEncoder(12) (Duración)
    }


    public static void main(String args[]){
        System.out.println("pass "+ new BCryptPasswordEncoder(12).encode("PUTO123"));
    }

}
