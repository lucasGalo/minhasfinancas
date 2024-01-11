package com.galo.minhasfinancas.framework.modelview.enumerados;

import com.galo.minhasfinancas.domain.enums.Ano;
import com.galo.minhasfinancas.domain.enums.DiaSemana;
import com.galo.minhasfinancas.domain.enums.Icone;
import com.galo.minhasfinancas.domain.enums.StatusItem;
import com.galo.minhasfinancas.domain.enums.StatusCompra;
import com.galo.minhasfinancas.domain.enums.Mes;
import com.galo.minhasfinancas.domain.enums.Perfil;
import com.galo.minhasfinancas.domain.enums.StatusRenda;
import com.galo.minhasfinancas.domain.enums.Unidade;

public enum ListObjectsEnum {

    DTOS_ENUM;

    public enum Nomes {
        PERFIL_ENUM("perfillist", "getPerfi", Perfil.values()),
        ITEMSTATUS_ENUM("statusItemlist", "getStatusItem", StatusItem.values()),
        COMPRASTATUS_ENUM("statusCompralist", "getStatusCompra", StatusCompra.values()),
        RENDASTATUS_ENUM("statusRendalist", "getStatusRenda", StatusRenda.values()),
        Ano_ENUM("anolist", "getAno", Ano.values()),
        Mes_ENUM("meslist", "getMes", Mes.values()),
        Unidade_ENUM("unidades", "getUnidade", Unidade.values()),
        DiasSemada_ENUM("dias", "getDia", DiaSemana.values()),
        ICONES_ENUM("icones", "getIcone", Icone.values()),
        ;

        private final String list;
        private final String method;
        private final Object[] listValues;

        Nomes(final String list, final String method, final Object[] listValues) {
            this.list = list;
            this.method = method;
            this.listValues = listValues;
        }

        public String getList() {
            return list;
        }

        public String getMethod() {
            return method;
        }

        public Object[] getListValues() {
            return listValues;
        }

        /**
         * Retornará o enum correspondente ao nome do método passado
         * params String method (deve corresponder a o mesmo nome que existe no enumerado)
         */
        public static Nomes toEnum(final String method) {
            int tam;
            for (Nomes enumerado : Nomes.values()) {
                tam = enumerado.method.length();
                if (method.length() >= tam)
                    if (enumerado.method.equals(method.substring(0, tam))) {
                        return enumerado;
                    }
            }
            return null;
        }
    }
}
