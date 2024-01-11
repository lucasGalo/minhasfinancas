package com.galo.minhasfinancas.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static com.galo.minhasfinancas.config.statics.Matches.ALL;
import static com.galo.minhasfinancas.config.statics.Matches.CLIENTE_MATCHES_GET;
import static com.galo.minhasfinancas.config.statics.Matches.CLIENTE_MATCHES_POST;
import static com.galo.minhasfinancas.config.statics.Matches.PUBLIC_MATCHES_FILE_STATICS;
import static com.galo.minhasfinancas.config.statics.Matches.PUBLIC_PAGES_FREE;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/resources/**").permitAll()
                        .requestMatchers(PUBLIC_MATCHES_FILE_STATICS).permitAll()
                        .requestMatchers(CLIENTE_MATCHES_GET).permitAll()
                        .requestMatchers(CLIENTE_MATCHES_POST).permitAll()
                        .requestMatchers(PUBLIC_PAGES_FREE).permitAll()
//                        .requestMatchers("/items/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                );

        http
                .formLogin(form -> form
                        .loginPage("/login")
                        .failureUrl("/login?error=true")
                        .permitAll()
                );

        http
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .permitAll()
                );

        http
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                );

        http
                .csrf().disable();
        http
                .headers().frameOptions().disable();


        return http.build();
    }

    public void configure(WebSecurity web) throws Exception {
        /*LIBERAR ARQUIVOS STATICOS COMO CSS E JS*/
        web.ignoring().requestMatchers(PUBLIC_MATCHES_FILE_STATICS);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration(ALL, new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}
