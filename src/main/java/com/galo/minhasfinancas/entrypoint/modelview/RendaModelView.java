package com.galo.minhasfinancas.entrypoint.modelview;

import com.galo.minhasfinancas.core.execution.RendaImpl;
import com.galo.minhasfinancas.domain.dto.RendaDTO;
import com.galo.minhasfinancas.framework.modelview.generics.ControllerGeneric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.galo.minhasfinancas.entrypoint.NamesResouces.RENDA_URL;

@Controller
@RequestMapping({RENDA_URL})
public class RendaModelView extends ControllerGeneric<RendaDTO> {

  @Autowired
  public RendaModelView(final RendaImpl impl) {super(impl, new RendaDTO(), RENDA_URL);}
}
