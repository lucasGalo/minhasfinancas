package com.galo.minhasfinancas.entrypoint.modelview;

import com.galo.minhasfinancas.core.execution.TipoImpl;
import com.galo.minhasfinancas.domain.dto.TipoDTO;
import com.galo.minhasfinancas.framework.modelview.generics.ControllerGeneric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.galo.minhasfinancas.entrypoint.NamesResouces.TIPO_URL;

@Controller
@RequestMapping({TIPO_URL})
public class TipoModelView extends ControllerGeneric<TipoDTO> {

  @Autowired
  public TipoModelView(final TipoImpl impl) {super(impl, new TipoDTO(), TIPO_URL);}
}
