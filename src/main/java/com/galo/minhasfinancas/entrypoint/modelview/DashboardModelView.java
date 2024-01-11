package com.galo.minhasfinancas.entrypoint.modelview;

import com.galo.minhasfinancas.core.execution.DashboardImpl;
import com.galo.minhasfinancas.domain.dto.dashboard.DashboardDTO;
import com.galo.minhasfinancas.framework.modelview.generics.ControllerGeneric;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.galo.minhasfinancas.entrypoint.NamesResouces.DASHBOARD_URL;

@Controller
@RequestMapping({DASHBOARD_URL})
public class DashboardModelView extends ControllerGeneric<DashboardDTO> {
  public DashboardModelView(final DashboardImpl impl) {super(impl, new DashboardDTO(), DASHBOARD_URL);}


}
