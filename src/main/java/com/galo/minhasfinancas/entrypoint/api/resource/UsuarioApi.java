package com.galo.minhasfinancas.entrypoint.api.resource;

import com.galo.minhasfinancas.core.execution.UsuarioImpl;
import com.galo.minhasfinancas.domain.dto.usuario.UsuarioDTO;
import com.galo.minhasfinancas.domain.entity.Usuario;
import com.galo.minhasfinancas.framework.api.resources.AbsResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.galo.minhasfinancas.entrypoint.NamesResouces.USUARIO_URL;

@RestController
@RequestMapping(value = "/api" + USUARIO_URL)
public class UsuarioApi extends AbsResource<Usuario, UsuarioImpl, UsuarioDTO> {

  public UsuarioApi(UsuarioImpl service) {
    super(service);
  }

  @RequestMapping(value = "/email", method = RequestMethod.GET)
  public boolean isEmail(@RequestParam(value = "email") String email) {
    return service.isEmail(email);
  }

  @RequestMapping(value = "/nome", method = RequestMethod.GET)
  public boolean isNome(@RequestParam(value = "nome") String email) {
    return service.isNome(email);
  }

}
