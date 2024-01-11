package com.galo.minhasfinancas.core.execution;

import com.galo.minhasfinancas.core.services.usuario.IUsuarioServices;
import com.galo.minhasfinancas.core.services.usuario.UsuarioServices;
import com.galo.minhasfinancas.dataprovider.repositories.UsuarioRepository;
import com.galo.minhasfinancas.domain.dto.usuario.UsuarioDTO;
import com.galo.minhasfinancas.domain.entity.Usuario;
import com.galo.minhasfinancas.domain.enums.Perfil;
import com.galo.minhasfinancas.domain.mappers.UsuarioMapper;
import com.galo.minhasfinancas.domain.querys.QUERY;
import com.galo.minhasfinancas.domain.querys.UsuarioQuery;
import com.galo.minhasfinancas.exceptions.exceptions.ObjectNotFoundException;
import com.galo.minhasfinancas.framework.api.execution.ExecutionAbs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class UsuarioImpl extends ExecutionAbs<Usuario, UsuarioRepository, UsuarioDTO, UsuarioMapper> {
  private final IUsuarioServices services;

  @Autowired
  public UsuarioImpl(UsuarioRepository repo, UsuarioMapper mapper) {
    super(repo, mapper);
    this.services = new UsuarioServices(this);
  }

  @Override
  public Usuario save(UsuarioDTO dto) {
// bug email
    dto.setEmail(dto.getEmail().equals("")? null: dto.getEmail());

    Usuario usuario = objetoOrNew(dto);
    if (dto.getId() == null && dto.getSenha() != null) /* considerando uma atualização, assim não viria a senha */
      usuario.setSenha(pe.encode(dto.getSenha()));
    else if (dto.getId() != null) {
      Usuario referenceById = repo.getReferenceById(dto.getId());
      if (null == dto.getPerfis())
        usuario.setPerfis(referenceById.getPerfis());
      else
        usuario.setPerfis(referenceById.getPerfis());

      if(null == usuario.getPerfis())
        usuario.setPerfis(Collections.singleton(Perfil.CLIENTE.getCod()));
      usuario.setSenha(referenceById.getSenha());
    }
    //imgSet(dto, usuario, imgRepository);
    return usuario;
  }

  private String getImageNome(String email) {
    return email + ".jpg";
  }

  @Override
  public String getName() {
    return Usuario.class.getSimpleName();
  }

  @Override
  public Class<? extends QUERY> getQuery() {
    return UsuarioQuery.class;
  }

  @Override
  public Object getService() {
    return this.services;
  }

  public boolean isNome(String nome) {

    Usuario obj = repo.findByNome(nome.trim());
    return obj != null;
  }

  public boolean isEmail(String email) {

    Usuario obj = repo.findByEmail(email);
    return obj != null;
  }

  public Usuario findByEmail(String email) {

    Usuario obj = repo.findByEmail(email);
    if (obj == null) {
      throw new ObjectNotFoundException("Email não ! Email: " + email + ", Tipo: '" + Usuario.class.getSimpleName() + "'");
    }
    return obj;
  }

}
