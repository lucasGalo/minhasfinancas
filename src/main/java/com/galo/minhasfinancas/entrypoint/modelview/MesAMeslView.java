package com.galo.minhasfinancas.entrypoint.modelview;

import com.galo.minhasfinancas.core.execution.MesAMesImpl;
import com.galo.minhasfinancas.domain.dto.MesAMesDTO;
import com.galo.minhasfinancas.framework.modelview.generics.ControllerGeneric;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.galo.minhasfinancas.entrypoint.NamesResouces.MESAMES_URL;

@Controller
@RequestMapping({MESAMES_URL})
public class MesAMeslView extends ControllerGeneric<MesAMesDTO> {

  private final MesAMesImpl impl;
  public MesAMeslView(final MesAMesImpl impl) {super(impl, new MesAMesDTO(), MESAMES_URL);
    this.impl = impl;
  }
  @GetMapping("renda")
  public String Renda(final @RequestParam("ano") Integer ano, Model map) {
    MesAMesDTO resumo = impl.renda(ano);
    map.addAttribute("single", resumo);
    return "paginas/mes/donut/renda :: fragRenda";
  }

  @GetMapping("despesa")
  public String Despesa(final @RequestParam("ano") Integer ano, Model map) {
    MesAMesDTO resumo = impl.despesa(ano);
    map.addAttribute("single", resumo);
    return "paginas/mes/donut/despesa :: fragDespesa";
  }

}
