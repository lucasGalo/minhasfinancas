package com.galo.minhasfinancas.entrypoint.modelview;

import com.galo.minhasfinancas.core.execution.EstatisticasImpl;
import com.galo.minhasfinancas.domain.dto.EstatisticasDTO;
import com.galo.minhasfinancas.framework.modelview.generics.ControllerGeneric;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.galo.minhasfinancas.entrypoint.NamesResouces.ESTATISTICA_URL;

@Controller
@RequestMapping({ESTATISTICA_URL})
public class EstatisticaModelView extends ControllerGeneric<EstatisticasDTO> {
  public EstatisticaModelView(final EstatisticasImpl impl) {super(impl, new EstatisticasDTO(), ESTATISTICA_URL);}


}
