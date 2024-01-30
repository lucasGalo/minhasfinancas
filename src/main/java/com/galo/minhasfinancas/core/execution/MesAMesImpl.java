package com.galo.minhasfinancas.core.execution;

import com.galo.minhasfinancas.domain.dto.CompraDTO;
import com.galo.minhasfinancas.domain.dto.EstatisticasDTO;
import com.galo.minhasfinancas.domain.dto.ManipulacaoDTO;
import com.galo.minhasfinancas.domain.dto.RendaDTO;
import com.galo.minhasfinancas.domain.dto.MesAMesDTO;
import com.galo.minhasfinancas.domain.enums.Mes;
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
public class MesAMesImpl extends ExecutionAbs {
  @Autowired
  protected MesAMesImpl() {
    super(null, null);
  }

  @Override public String getName() {return null;}

  @Override public String print(Object object, Locale locale) {
    return null;
  }

  @Override
  public List findAll(){
    MesAMesDTO resumos = new MesAMesDTO();
    resumos.setEstatisticas( new EstatisticasDTO(custom(compraRepository.findAllUsuario(getIdUserLogado()), CompraDTO.class, MesAMesDTO.class)));
    resumos.setManipulacao( new ManipulacaoDTO(custom(rendaRepository.findAllUsuario(getIdUserLogado()), RendaDTO.class, MesAMesDTO.class)));
    resumos.setAno(Integer.parseInt(toFormate(new Date(), "YYYY")));
    resumos.setMes(Mes.toEnum(Integer.parseInt(toFormate(new Date(), "MM"))).getValue());
    return  List.of(resumos);
  }

  public MesAMesDTO renda(int ano){
    return new MesAMesDTO(null, new ManipulacaoDTO(custom(rendaRepository.findAllUsuarioAndAno(getIdUserLogado(), ano), RendaDTO.class, MesAMesDTO.class)), ano);
  }

  public MesAMesDTO despesa(int ano){
    return new MesAMesDTO(new EstatisticasDTO(custom(compraRepository.findAllUsuarioAndAno(getIdUserLogado(), ano), CompraDTO.class, MesAMesDTO.class)), null, ano);
  }

  }
