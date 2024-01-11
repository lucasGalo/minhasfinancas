package com.galo.minhasfinancas.core.execution;

import com.galo.minhasfinancas.dataprovider.repositories.PagamentoRepository;
import com.galo.minhasfinancas.domain.dto.CategoriaDTO;
import com.galo.minhasfinancas.domain.dto.PagamentoDTO;
import com.galo.minhasfinancas.domain.entity.Categoria;
import com.galo.minhasfinancas.domain.entity.Pagamento;
import com.galo.minhasfinancas.domain.mappers.PagamentoMapper;
import com.galo.minhasfinancas.framework.api.execution.ExecutionAbs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.galo.minhasfinancas.config.security.UserUtil.getIdUserLogado;
import static com.galo.minhasfinancas.reflectionCuston.ReflexaoCustom.custom;

@Service
public class PagamentoImpl extends ExecutionAbs<Pagamento, PagamentoRepository, PagamentoDTO, PagamentoMapper> {
  @Autowired
  protected PagamentoImpl(PagamentoRepository repo, PagamentoMapper mapper) {
    super(repo, mapper);
  }
  @Override
  public String getName() {
    return Pagamento.class.getSimpleName();
  }

  public List findAll() {return custom(pagamentoRepository.findAllUsuario(getIdUserLogado()), PagamentoDTO.class,  Pagamento.class);}

}
