package com.galo.minhasfinancas.entrypoint.modelview;

import com.galo.minhasfinancas.core.execution.TelemetriaImpl;
import com.galo.minhasfinancas.domain.dto.TelemetriaDTO;
import com.galo.minhasfinancas.framework.modelview.generics.ControllerGeneric;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.galo.minhasfinancas.entrypoint.NamesResouces.TELEMETRIA_URL;

@Controller
@RequestMapping({TELEMETRIA_URL})
public class TelemetriaModelView extends ControllerGeneric<TelemetriaDTO> {
  public TelemetriaModelView(final TelemetriaImpl impl) {super(impl, new TelemetriaDTO(), TELEMETRIA_URL);}
}
