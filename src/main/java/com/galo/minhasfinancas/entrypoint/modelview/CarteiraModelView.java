package com.galo.minhasfinancas.entrypoint.modelview;

import com.galo.minhasfinancas.core.execution.CarteiraImpl;
import com.galo.minhasfinancas.domain.dto.CarteiraDTO;
import com.galo.minhasfinancas.framework.modelview.generics.ControllerGeneric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.galo.minhasfinancas.entrypoint.NamesResouces.CARTEIRA_URL;

@Controller
@RequestMapping({CARTEIRA_URL})
public class CarteiraModelView extends ControllerGeneric<CarteiraDTO> {

  @Autowired
  public CarteiraModelView(final CarteiraImpl impl) {super(impl, new CarteiraDTO(), CARTEIRA_URL);}
}
