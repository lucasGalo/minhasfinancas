package com.galo.minhasfinancas.core.execution;

import com.galo.minhasfinancas.core.services.telemetria.ITelemetriaServices;
import com.galo.minhasfinancas.core.services.telemetria.TelemetriaServices;
import com.galo.minhasfinancas.dataprovider.repositories.TelemetriaRepository;
import com.galo.minhasfinancas.domain.dto.TelemetriaDTO;
import com.galo.minhasfinancas.domain.entity.Telemetria;
import com.galo.minhasfinancas.domain.mappers.TelemetriaMapper;
import com.galo.minhasfinancas.domain.querys.QUERY;
import com.galo.minhasfinancas.domain.querys.TelemetriaQuery;
import com.galo.minhasfinancas.framework.api.execution.ExecutionAbs;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TelemetriaImpl extends ExecutionAbs<Telemetria, TelemetriaRepository, TelemetriaDTO, TelemetriaMapper> {

  public final ITelemetriaServices services;
  public TelemetriaImpl(TelemetriaRepository repo, TelemetriaMapper mapper) {
    super(repo, mapper);
    this.services = new TelemetriaServices(this);
  }
  public Telemetria getTelemetria(String session){return repo.findTelemetria(session);}
  public List<TelemetriaDTO> findAllAno(String ano){return services.findAllAno(ano);}
  @Override public String getName() {
    return Telemetria.class.getSimpleName();
  }
  @Override public Class<? extends QUERY> getQuery() {return TelemetriaQuery.class;}
  @Override public ITelemetriaServices getService(){return this.services;}
}
