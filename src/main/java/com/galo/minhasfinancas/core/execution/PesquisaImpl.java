package com.galo.minhasfinancas.core.execution;

import com.galo.minhasfinancas.dataprovider.repositories.PesquisaRepository;
import com.galo.minhasfinancas.domain.dto.PesquisaDTO;
import com.galo.minhasfinancas.domain.entity.Pesquisa;
import com.galo.minhasfinancas.domain.mappers.PesquisaMapper;
import com.galo.minhasfinancas.framework.api.execution.ExecutionAbs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PesquisaImpl extends ExecutionAbs<Pesquisa, PesquisaRepository, PesquisaDTO, PesquisaMapper> {

  @Autowired protected PesquisaImpl(PesquisaRepository repo, PesquisaMapper mapper) {
    super(repo, mapper);
  }
  @Override public String getName() {
    return Pesquisa.class.getSimpleName();
  }
}
