package com.galo.minhasfinancas.core.services.usuario;

import com.galo.minhasfinancas.core.execution.UsuarioImpl;
import com.galo.minhasfinancas.domain.entity.Usuario;
import com.galo.minhasfinancas.domain.querys.UsuarioQuery;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UsuarioServices extends IUsuarioServices {

  private final UsuarioImpl impl;

  private final BCryptPasswordEncoder pe = new BCryptPasswordEncoder();

  public UsuarioServices(UsuarioImpl services) {
    this.impl = services;
  }

  @Override
  public Boolean trocaSenha(UsuarioQuery query) {
    if(null == query.getId()) return false;
    Usuario usuario = impl.isObj(Integer.valueOf(query.getId()));

    if (pe.matches(query.getAntiga(), usuario.getSenha())) {
      usuario.setSenha(pe.encode(query.getNova()));
      impl.repo.save(usuario);
      return true;
    }
    return false;
  }

  @Override
  public Boolean anotacoes(UsuarioQuery query) {
    if(null == query.getId()) return false;

    Usuario usuario = impl.isObj(Integer.valueOf(query.getId()));
    if (null == usuario) return false;

    usuario.setDescricao(query.getDescricao());
    impl.repo.save(usuario);
    return true;

  }

  @Override
  public Boolean autorizar(UsuarioQuery query) {
    return null;
  }
}