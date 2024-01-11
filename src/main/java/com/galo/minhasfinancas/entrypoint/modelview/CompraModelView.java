package com.galo.minhasfinancas.entrypoint.modelview;

import com.galo.minhasfinancas.core.execution.CompraImpl;
import com.galo.minhasfinancas.domain.dto.CompraDTO;
import com.galo.minhasfinancas.framework.modelview.generics.ControllerGeneric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.galo.minhasfinancas.entrypoint.NamesResouces.COMPRA_URL;

@Controller
@RequestMapping({COMPRA_URL})
public class CompraModelView extends ControllerGeneric<CompraDTO> {

  @Autowired
  public CompraModelView(final CompraImpl impl) {super(impl, new CompraDTO(), COMPRA_URL);}
}
