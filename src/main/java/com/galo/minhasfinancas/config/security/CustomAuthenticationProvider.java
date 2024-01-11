package com.galo.minhasfinancas.config.security;

import com.galo.logstach.group.Logs;
import com.galo.logstach.interfaces.LogProcessInterface;
import com.galo.minhasfinancas.config.beans.HttpRequest;
import com.galo.minhasfinancas.config.security.domain.UserSS;
import com.galo.minhasfinancas.config.security.token.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import static com.galo.minhasfinancas.sources.StringUtil.getUUID;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired protected HttpRequest request;

    public static String msg;
    private final Logs logs;
    private final LoginService login;

    public CustomAuthenticationProvider(LoginService login, Logs logs) {
        this.login = login;
        this.logs = logs;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try (LogProcessInterface log = logs.get("[KEY] "+getUUID())) {
            msg = "";
            log.info(request.format("[FORM] [authenticate] - [USER] @authentication:  %s", authentication));
            String email = authentication.getName();
            String password = authentication.getCredentials().toString();
            UserSS userSS = login.getToken(email, password, log);
            log.info(request.format("[FORM] [authenticate] - [TOKEN] @userSS  %s", userSS));

            if (userSS == null) {
                msg = "Usuário ou senha inválidos";
                return null;
            }

            log.info(request.format("[FORM] [authenticate] - [userSS] @userSS  %s", userSS));
            return new UsernamePasswordAuthenticationToken(userSS, password, userSS.getAuthorities());
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
