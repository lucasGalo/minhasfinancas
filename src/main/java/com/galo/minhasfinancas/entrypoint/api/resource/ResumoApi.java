package com.galo.minhasfinancas.entrypoint.api.resource;

import com.galo.minhasfinancas.core.execution.ResumoImpl;
import com.galo.minhasfinancas.domain.dto.ResumoDTO;
import com.galo.minhasfinancas.framework.api.resources.AbsResource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.galo.minhasfinancas.entrypoint.NamesResouces.RESUMO_URL;

@RestController
@RequestMapping(value = "/api" + RESUMO_URL)
public class ResumoApi extends AbsResource<ResumoDTO, ResumoImpl, ResumoDTO> {



  public ResumoApi(ResumoImpl impl) {
    super(impl);
  }

  @RequestMapping(value = "/renda", method = RequestMethod.GET)
  public String Renda(final @RequestParam("ano") Integer ano, Model map) {
    ResumoDTO resumo = service.renda(ano);
    return resumo.getDonutDataRendaToJson();
  }

  @RequestMapping(value = "/despesa", method = RequestMethod.GET)
  public String Despesa(final @RequestParam("ano") Integer ano, Model map) {
    ResumoDTO resumo = service.despesa(ano);
    return resumo.getDonutDataDespesaToJson();
  }

}
