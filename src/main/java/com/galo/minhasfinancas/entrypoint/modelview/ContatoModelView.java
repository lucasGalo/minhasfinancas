package com.galo.minhasfinancas.entrypoint.modelview;

import com.galo.minhasfinancas.domain.dto.CompraDTO;
import com.galo.logstach.interfaces.LogProcessInterface;
import com.galo.minhasfinancas.core.execution.ContatoImpl;
import com.galo.minhasfinancas.domain.dto.ContatoDTO;
import com.galo.minhasfinancas.framework.modelview.generics.ControllerGeneric;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static com.galo.minhasfinancas.entrypoint.NamesResouces.CONTATO_URL;
import static com.galo.minhasfinancas.exceptions.exceptions.adpters.FieldErrorAdpter.listErrorAdater;
import static com.galo.minhasfinancas.sources.StringUtil.getUUID;

@Controller
@RequestMapping({CONTATO_URL})
public class ContatoModelView extends ControllerGeneric<ContatoDTO> {


  public ContatoModelView(final ContatoImpl impl) {super(impl, new ContatoDTO(), CONTATO_URL);
    this.PAGE_FORM = "free/contato";
  }

  @PostMapping(value = "/novo")
  public ModelAndView Novo(final ContatoDTO dto, BindingResult result) {
    try (LogProcessInterface log = logs.get("[KEY] "+getUUID())) {
      log.info(request.format("[POST] [Novo] - [REQUEST] @T dto: %s, @BindingResult: %s", dto, result));
      if (result.hasErrors()) {
        log.error(request.format("[POST] [Novo] - [RESPONSE] @hasErrors : %s", result.getAllErrors()));
        return newFormPageCustom(listObjectosFactory.map(dto), dto, result.getAllErrors());
      }
      try {
        reiniciarVariaveisDeClasseESalva(dto);
      } catch (Exception e) {
        log.error(request.format("[POST] [Novo] - [RESPONSE] @Exception : %s", e.getMessage()));
        return newFormPageCustom(listObjectosFactory.map(dto), dto, listErrorAdater(e, dto.getClass().getSimpleName()));
      }
      return newFormPageOfObject(new CompraDTO(), "free/sucesso");
    }
  }

}
