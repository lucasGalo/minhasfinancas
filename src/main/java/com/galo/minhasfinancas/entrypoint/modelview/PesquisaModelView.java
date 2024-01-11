package com.galo.minhasfinancas.entrypoint.modelview;

import com.galo.minhasfinancas.core.execution.PesquisaImpl;
import com.galo.minhasfinancas.domain.dto.PesquisaDTO;
import com.galo.minhasfinancas.framework.modelview.generics.ControllerGeneric;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.galo.minhasfinancas.entrypoint.NamesResouces.PESQUISA_URL;

@Controller
@RequestMapping({PESQUISA_URL})
public class PesquisaModelView extends ControllerGeneric<PesquisaDTO> {
  public PesquisaModelView(final PesquisaImpl impl) {super(impl, new PesquisaDTO(), PESQUISA_URL);}
}
