package com.galo.minhasfinancas.entrypoint.modelview;

import com.galo.minhasfinancas.core.execution.PagamentoImpl;
import com.galo.minhasfinancas.domain.dto.PagamentoDTO;
import com.galo.minhasfinancas.framework.modelview.generics.ControllerGeneric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.galo.minhasfinancas.entrypoint.NamesResouces.PAGAMENTO_URL;

@Controller
@RequestMapping({PAGAMENTO_URL})
public class PagamentoModelView extends ControllerGeneric<PagamentoDTO> {

  @Autowired
  public PagamentoModelView(final PagamentoImpl impl) {super(impl, new PagamentoDTO(), PAGAMENTO_URL);}
}
