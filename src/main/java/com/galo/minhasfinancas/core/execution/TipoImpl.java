package com.galo.minhasfinancas.core.execution;

import com.galo.minhasfinancas.dataprovider.repositories.TipoRepository;
import com.galo.minhasfinancas.domain.dto.TipoDTO;
import com.galo.minhasfinancas.domain.entity.Tipo;
import com.galo.minhasfinancas.domain.mappers.TipoMapper;
import com.galo.minhasfinancas.framework.api.execution.ExecutionAbs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.galo.minhasfinancas.config.security.UserUtil.getIdUserLogado;
import static com.galo.minhasfinancas.reflectionCuston.ReflexaoCustom.custom;

@Service
public class TipoImpl extends ExecutionAbs<Tipo, TipoRepository, TipoDTO, TipoMapper> {

  @Autowired protected TipoImpl(TipoRepository repo, TipoMapper mapper) {
    super(repo, mapper);
  }
  @Override public String getName() {return Tipo.class.getSimpleName();}

  @Override public List findAll() {return custom(tipoRepository.findAllUsuario(getIdUserLogado()), TipoDTO.class,  Tipo.class);}
}
