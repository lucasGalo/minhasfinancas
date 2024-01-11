package com.galo.minhasfinancas.config.filters;

import com.galo.minhasfinancas.config.security.domain.UserSS;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AuthInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!Objects.isNull(authentication) && authentication.getPrincipal() instanceof UserSS) {
            UserSS principal = (UserSS) authentication.getPrincipal();
            requestTemplate.header("Authorization", "Bearer "+principal.getToken());
        }
    }
}
