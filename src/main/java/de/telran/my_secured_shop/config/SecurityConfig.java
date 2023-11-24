package de.telran.my_secured_shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain
    securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(x -> x
                        .requestMatchers(HttpMethod.GET, "/product/all").permitAll()
                        .requestMatchers(HttpMethod.GET, "/user/all").permitAll()
                        .requestMatchers(HttpMethod.GET, "/product/{id}").hasAnyRole("ADMIN", "USER", "MANAGER")
                        .requestMatchers(HttpMethod.POST, "/user/{userId}/{productId}").hasAnyRole("ADMIN", "USER", "MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/user/{userId}/{productId}").hasAnyRole("ADMIN", "USER", "MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/user/clear/{userId}").hasAnyRole("ADMIN", "USER", "MANAGER")
                        .requestMatchers(HttpMethod.GET, "/user/{id}").hasAnyRole("ADMIN", "MANAGER")
                        .requestMatchers(HttpMethod.GET, "/user/count").hasAnyRole("ADMIN", "MANAGER")
                        .requestMatchers(HttpMethod.GET, "/product/count").hasAnyRole("ADMIN", "MANAGER")
                        .requestMatchers(HttpMethod.GET, "/product/total_price").hasAnyRole("ADMIN", "MANAGER")
                        .requestMatchers(HttpMethod.GET, "/product/avg_price").hasAnyRole("ADMIN", "MANAGER")
                        .requestMatchers(HttpMethod.GET, "/user/total/{id}").hasAnyRole("ADMIN", "MANAGER")
                        .requestMatchers(HttpMethod.GET, "/user/average/{id}").hasAnyRole("ADMIN", "MANAGER")
                        .requestMatchers(HttpMethod.POST, "/product/add").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/product/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/product/name/{name}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/user/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/user/name/{name}").hasRole("ADMIN"))
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }
}
