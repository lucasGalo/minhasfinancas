package com.galo.minhasfinancas.entrypoint.api.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.galo.minhasfinancas.entrypoint.NamesResouces.SISTEMA_URL;

@RestController
@RequestMapping(value = "/api"+SISTEMA_URL)
public class Sistema {
  @RequestMapping("/resource")
  public Map<String, Object> home(Principal principal) {
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("id", UUID.randomUUID().toString());
    model.put("content", "Hello " + principal.getName());
    return model;
  }
}
