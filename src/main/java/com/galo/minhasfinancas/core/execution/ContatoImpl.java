package com.galo.minhasfinancas.core.execution;

import com.galo.minhasfinancas.dataprovider.repositories.ContatoRepository;
import com.galo.minhasfinancas.domain.dto.ContatoDTO;
import com.galo.minhasfinancas.domain.entity.Contato;
import com.galo.minhasfinancas.domain.mappers.ContatoMapper;
import com.galo.minhasfinancas.framework.api.execution.ExecutionAbs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContatoImpl extends ExecutionAbs<Contato, ContatoRepository, ContatoDTO, ContatoMapper> {

  @Autowired protected ContatoImpl(ContatoRepository repo, ContatoMapper mapper) {
    super(repo, mapper);
  }
  @Override public String getName() {
    return Contato.class.getSimpleName();
  }
}
