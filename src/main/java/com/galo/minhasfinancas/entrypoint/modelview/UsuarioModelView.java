package com.galo.minhasfinancas.entrypoint.modelview;

import com.galo.minhasfinancas.core.execution.UsuarioImpl;
import com.galo.minhasfinancas.domain.dto.usuario.UsuarioDTO;
import com.galo.minhasfinancas.framework.modelview.generics.ControllerGeneric;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static com.galo.minhasfinancas.entrypoint.NamesResouces.USUARIO_URL;

@Controller
@RequestMapping({USUARIO_URL})
public class UsuarioModelView extends ControllerGeneric<UsuarioDTO> {
  private final UsuarioImpl impl;

  public UsuarioModelView(final UsuarioImpl impl) {
    super(impl, new UsuarioDTO(), USUARIO_URL);
    this.impl = impl;
  }


//  @Secured({"ROLE_ADMIN", "ROLE_OPERADOR"})
  @GetMapping("index")
  public ModelAndView index() {

    UsuarioDTO dto = new UsuarioDTO();
    this.TITLE_NEW = "";
    return newFormPageCustom(impl.getStringListMap(), dto, null, "index");
  }
}
