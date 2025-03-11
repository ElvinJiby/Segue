package org.elvinjiby.marketplace.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/register", "/process_register","/login",
                            "/css/**", "/js/**", "/images/**").permitAll()   // allow anyone to access

                    .requestMatchers("/customer/**").hasRole("CUSTOMER")   // restrict customer pages

                    .requestMatchers("/admin/**").hasRole("ADMIN")  // restrict admin pages

                    .anyRequest().authenticated()   // all other pages need authentication
            )
            .formLogin(form -> form
                    .loginPage("/login")
                    .successHandler((request, response, authentication) -> {
                        String role = authentication.getAuthorities().iterator().next().getAuthority();
                        String path = (role.equals("ROLE_ADMIN")) ? "/admin/home" : "/customer/home";
                        response.sendRedirect(path);
                    })
//                    .defaultSuccessUrl("/login?loginSuccess=true", true)
                    .permitAll()
            )
            .logout(LogoutConfigurer::permitAll);

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
