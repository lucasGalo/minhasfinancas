package com.galo.minhasfinancas.core.execution;

import com.galo.minhasfinancas.dataprovider.repositories.RendaRepository;
import com.galo.minhasfinancas.domain.dto.RendaDTO;
import com.galo.minhasfinancas.domain.entity.Renda;
import com.galo.minhasfinancas.domain.mappers.RendaMapper;
import com.galo.minhasfinancas.framework.api.execution.ExecutionAbs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.galo.minhasfinancas.config.security.UserUtil.getIdUserLogado;
import static com.galo.minhasfinancas.reflectionCuston.ReflexaoCustom.custom;

@Service
public class RendaImpl extends ExecutionAbs<Renda, RendaRepository, RendaDTO, RendaMapper> {

  @Autowired protected RendaImpl(RendaRepository repo, RendaMapper mapper) {
    super(repo, mapper);
  }
  @Override public String getName() {return Renda.class.getSimpleName();}

  @Override public List findAll() {return custom(rendaRepository.findAllUsuario(getIdUserLogado()), RendaDTO.class,  Renda.class);}
}
