package com.galo.minhasfinancas.framework.api.action;

import com.galo.minhasfinancas.domain.enums.BaseEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListBaseActions {

    /* Este List deve conter todas as calsses que estendao de BaseEnum.
    *  conter tbm mais de uma classe para o método getEnumInstance(), funcionar
    */
    @SuppressWarnings("all")
    public static final List<BaseEnum[]> list = new ArrayList(Arrays.asList(
        UsuarioAction.values(),
            TelemetriaAction.values()
    ));

}
