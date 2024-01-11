package com.galo.minhasfinancas.config.security.token;

import com.galo.logstach.interfaces.LogProcessInterface;
import com.galo.minhasfinancas.config.beans.HttpRequest;
import com.galo.minhasfinancas.config.security.domain.UserSS;
import com.galo.minhasfinancas.core.execution.TelemetriaImpl;
import com.galo.minhasfinancas.core.execution.UsuarioImpl;
import com.galo.minhasfinancas.domain.entity.Usuario;
import com.galo.minhasfinancas.domain.enums.BaseEnum;
import com.galo.minhasfinancas.domain.enums.Perfil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Component
public class LoginService {

    @Autowired protected HttpRequest request;
    @Autowired private BCryptPasswordEncoder encoder;
    @Autowired private UsuarioImpl usuarioImpl;
    @Autowired private TelemetriaImpl telemetriaImpl;

    public UserSS getToken(String username, String password, LogProcessInterface log) {
        UserSS userSS = null;
        try{
            log.info(request.format("[getToken] start userSS, user: %s e password: %s", username, password));
            Usuario usuario = usuarioImpl.findByEmail(username);

            final String sessionAntes = request.getSession();

            if(null != usuario){
                if(encoder.matches(password, usuario.getSenha())){
                    Set<Perfil> perfils = usuario.getPerfis().stream().map(it -> (Perfil) BaseEnum.getEnumInstance(Perfil.values(), it)).collect(Collectors.toSet());
                    userSS = new UserSS(usuario.getId(), usuario.getNome(), usuario.getEmail(), "", perfils, "userSS", usuario.getImg());
                    log.info(request.format("[getToken] [SUCESSO] UserSS: %s", userSS));

                     telemetriaImpl.getService().updateTelemetriaGuid(sessionAntes, usuario.getId());
                }else{
                    log.error("[getToken] [Exception] erro login : matches fail");
                }
            }
        }catch (Exception ex){
            log.error(request.format("[getToken] [Exception] erro login %s: ", ex.getMessage()));
            return userSS;
        }
        return userSS;
    }
}
