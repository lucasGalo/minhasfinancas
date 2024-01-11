package com.galo.minhasfinancas.entrypoint.modelview;

import com.galo.minhasfinancas.core.execution.CategoriaImpl;
import com.galo.minhasfinancas.domain.dto.CategoriaDTO;
import com.galo.minhasfinancas.framework.modelview.generics.ControllerGeneric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.galo.minhasfinancas.entrypoint.NamesResouces.CATEGORIA_URL;

@Controller
@RequestMapping({CATEGORIA_URL})
public class CategoriaModelView extends ControllerGeneric<CategoriaDTO> {

  @Autowired
  public CategoriaModelView(final CategoriaImpl impl) {super(impl, new CategoriaDTO(), CATEGORIA_URL);}
}
