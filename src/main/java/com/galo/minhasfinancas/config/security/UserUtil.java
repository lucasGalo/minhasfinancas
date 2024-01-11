package com.galo.minhasfinancas.config.security;

import com.galo.minhasfinancas.config.security.domain.UserSS;
import org.springframework.security.core.context.SecurityContextHolder;

import static com.galo.minhasfinancas.domain.enums.Perfil.ADMIN;

public class UserUtil {

    public static UserSS getUserSS() {
        if(SecurityContextHolder.getContext().getAuthentication() == null) return null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserSS) {
            return (UserSS) principal;
        }
        return null;
    }

    public static int getIdUserLogado(){
        UserSS userSS = UserUtil.getUserSS();
        if(null != userSS){
            return userSS.getId();
        }else {
            return 0;
        }
    }

    public static boolean isAdmin(){
        UserSS userSS = UserUtil.getUserSS();
        return null != userSS && userSS.getPerfis().contains(ADMIN);
    }
}
