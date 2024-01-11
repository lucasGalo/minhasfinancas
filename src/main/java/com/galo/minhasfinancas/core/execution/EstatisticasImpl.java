package com.galo.minhasfinancas.core.execution;

import com.galo.minhasfinancas.domain.dto.CompraDTO;
import com.galo.minhasfinancas.domain.dto.EstatisticasDTO;
import com.galo.minhasfinancas.framework.api.execution.ExecutionAbs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

import static com.galo.minhasfinancas.config.security.UserUtil.getIdUserLogado;
import static com.galo.minhasfinancas.reflectionCuston.ReflexaoCustom.custom;

@Service
public class EstatisticasImpl extends ExecutionAbs {
  @Autowired
  protected EstatisticasImpl() {
    super(null, null);
  }

  @Override
  public String getName() {
    return null;
  }

  @Override
  public String print(Object object, Locale locale) {
    return null;
  }

  @Override
  public List findAll(){
    EstatisticasDTO estatisticas = new EstatisticasDTO();
    estatisticas.setCompras( custom(compraRepository.findAllUsuario(getIdUserLogado()), CompraDTO.class, EstatisticasDTO.class));
    return  List.of(estatisticas);
  }


}
