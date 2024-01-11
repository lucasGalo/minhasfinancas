package com.galo.minhasfinancas.core.execution;

import com.galo.minhasfinancas.domain.dto.CompraDTO;
import com.galo.minhasfinancas.domain.dto.CalendarDTO;
import com.galo.minhasfinancas.domain.dto.RendaDTO;
import com.galo.minhasfinancas.framework.api.execution.ExecutionAbs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

import static com.galo.minhasfinancas.config.security.UserUtil.getIdUserLogado;
import static com.galo.minhasfinancas.reflectionCuston.ReflexaoCustom.custom;

@Service
public class CalendarImpl extends ExecutionAbs {
  @Autowired
  protected CalendarImpl() {
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
    CalendarDTO calendar = new CalendarDTO();
    calendar.setCompras( custom(compraRepository.findAllUsuario(getIdUserLogado()), CompraDTO.class, CalendarDTO.class));
    calendar.setRendas(custom(rendaRepository.findAllUsuario(getIdUserLogado()), RendaDTO.class, CalendarDTO.class));
    return  List.of(calendar);
  }


}
