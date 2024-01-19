package com.galo.minhasfinancas.entrypoint.modelview;

import com.galo.minhasfinancas.core.execution.ResumoImpl;
import com.galo.minhasfinancas.domain.dto.ResumoDTO;
import com.galo.minhasfinancas.framework.modelview.generics.ControllerGeneric;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.galo.minhasfinancas.entrypoint.NamesResouces.RESUMO_URL;

@Controller
@RequestMapping({RESUMO_URL})
public class ResumolView extends ControllerGeneric<ResumoDTO> {

  private final ResumoImpl impl;
  public ResumolView(final ResumoImpl impl) {super(impl, new ResumoDTO(), RESUMO_URL);
    this.impl = impl;
  }
  @GetMapping("renda")
  public String Renda(final @RequestParam("ano") Integer ano, Model map) {
    ResumoDTO resumo = impl.renda(ano);
    map.addAttribute("single", resumo);
    return "paginas/resumos/donut/renda :: fragRenda";
  }

  @GetMapping("despesa")
  public String Despesa(final @RequestParam("ano") Integer ano, Model map) {
    ResumoDTO resumo = impl.despesa(ano);
    map.addAttribute("single", resumo);
    return "paginas/resumos/donut/despesa :: fragDespesa";
  }

}
