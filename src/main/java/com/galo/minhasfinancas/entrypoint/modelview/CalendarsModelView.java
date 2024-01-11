package com.galo.minhasfinancas.entrypoint.modelview;

import com.galo.minhasfinancas.core.execution.CalendarImpl;
import com.galo.minhasfinancas.domain.dto.CalendarDTO;
import com.galo.minhasfinancas.framework.modelview.generics.ControllerGeneric;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.galo.minhasfinancas.entrypoint.NamesResouces.CALENDAR_URL;

@Controller
@RequestMapping({CALENDAR_URL})
public class CalendarsModelView extends ControllerGeneric<CalendarDTO> {
  public CalendarsModelView(final CalendarImpl impl) {super(impl, new CalendarDTO(), CALENDAR_URL);}


}
