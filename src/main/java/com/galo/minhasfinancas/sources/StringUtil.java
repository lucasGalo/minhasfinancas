package com.galo.minhasfinancas.sources;

import java.util.Objects;
import java.util.UUID;

public class StringUtil {

    public static String cleanMask(String campo){
        if(Objects.isNull(campo)) return null;
        return campo.replaceAll("[^0-9]", "");
    }
    public static String recorte(String conteudo, int max){
        if(Objects.isNull(conteudo)) return null;
        return conteudo.substring(0, Math.min(conteudo.length(), max));
    }
    public static String getUUID() {
        return UUID.randomUUID().toString();
    }
    public static String toJson(String comando){
        return  "{" + comando.replaceAll("&",",").replaceAll("=", ":") + "}";
    }
    public static String capitalize(String str){return str == null ? null :  str.substring(0, 1).toUpperCase() + str.substring(1);}
}
