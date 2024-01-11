package com.galo.minhasfinancas.entrypoint.modelview;

import com.galo.minhasfinancas.config.security.CustomAuthenticationProvider;
import com.galo.minhasfinancas.domain.dto.LoginDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import static com.galo.minhasfinancas.entrypoint.NamesResouces.LOGIN_URL;

@Controller
@RequestMapping({LOGIN_URL})
public class LoginModelView {

  @GetMapping()
  public ModelAndView loginPage(@RequestParam(value = "error", defaultValue = "false") boolean loginError) {
    ModelAndView modelAndView = new ModelAndView("login");
    modelAndView.addObject("usuario", new LoginDTO());
    if (loginError) {
      String msg = CustomAuthenticationProvider.msg;
      modelAndView.addObject("message",msg);
    } else {
      modelAndView.addObject("message", "Sucesso!");
    }
    return modelAndView;
  }

}
