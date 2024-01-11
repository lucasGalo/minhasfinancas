package com.galo.minhasfinancas.entrypoint.api.resource;

import com.galo.minhasfinancas.core.execution.PagamentoImpl;
import com.galo.minhasfinancas.domain.dto.PagamentoDTO;
import com.galo.minhasfinancas.domain.entity.Pagamento;
import com.galo.minhasfinancas.framework.api.resources.AbsResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.galo.minhasfinancas.entrypoint.NamesResouces.PAGAMENTO_URL;

@RestController
@RequestMapping(value = "/api" + PAGAMENTO_URL)
public class PagamentoApi extends AbsResource<Pagamento, PagamentoImpl, PagamentoDTO> {

  public PagamentoApi(PagamentoImpl service) {
    super(service);
  }
}
