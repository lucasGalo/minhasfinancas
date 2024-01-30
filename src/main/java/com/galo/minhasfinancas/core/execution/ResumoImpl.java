package com.galo.minhasfinancas.core.execution;

import com.galo.minhasfinancas.domain.dto.CompraDTO;
import com.galo.minhasfinancas.domain.dto.EstatisticasDTO;
import com.galo.minhasfinancas.domain.dto.ManipulacaoDTO;
import com.galo.minhasfinancas.domain.dto.RendaDTO;
import com.galo.minhasfinancas.domain.dto.ResumoDTO;
import com.galo.minhasfinancas.framework.api.execution.ExecutionAbs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.galo.minhasfinancas.config.security.UserUtil.getIdUserLogado;
import static com.galo.minhasfinancas.reflectionCuston.ReflexaoCustom.custom;
import static com.galo.minhasfinancas.sources.DateUtil.toFormate;

@Service
public class ResumoImpl extends ExecutionAbs {
  @Autowired
  protected ResumoImpl() {
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
    ResumoDTO resumos = new ResumoDTO();
    resumos.setEstatisticas( new EstatisticasDTO(custom(compraRepository.findAllUsuario(getIdUserLogado()), CompraDTO.class, ResumoDTO.class)));
    resumos.setManipulacao( new ManipulacaoDTO(custom(rendaRepository.findAllUsuario(getIdUserLogado()), RendaDTO.class, ResumoDTO.class)));
    resumos.setAno(Integer.parseInt(toFormate(new Date(), "YYYY")));
    return  List.of(resumos);
  }

  public ResumoDTO renda(int ano){
    return new ResumoDTO(null, new ManipulacaoDTO(custom(rendaRepository.findAllUsuarioAndAno(getIdUserLogado(), ano), RendaDTO.class, ResumoDTO.class)), ano);
  }

  public ResumoDTO despesa(int ano){
    return new ResumoDTO(new EstatisticasDTO(custom(compraRepository.findAllUsuarioAndAno(getIdUserLogado(), ano), CompraDTO.class, ResumoDTO.class)), null, ano);
  }
}
