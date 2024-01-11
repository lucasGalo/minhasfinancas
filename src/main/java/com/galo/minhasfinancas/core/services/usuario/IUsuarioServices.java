package com.galo.minhasfinancas.core.services.usuario;

import com.galo.minhasfinancas.domain.querys.UsuarioQuery;

public abstract class IUsuarioServices {
  public abstract Boolean trocaSenha(UsuarioQuery query);
  public abstract Boolean anotacoes(UsuarioQuery query);
  public abstract Boolean autorizar(UsuarioQuery query);

}
