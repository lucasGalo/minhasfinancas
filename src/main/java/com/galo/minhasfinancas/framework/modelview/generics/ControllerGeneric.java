package com.galo.minhasfinancas.framework.modelview.generics;

import com.galo.minhasfinancas.config.beans.HttpRequest;
import com.galo.minhasfinancas.domain.dto.DTO;
import com.galo.minhasfinancas.domain.dto.usuario.UsuarioDTO;
import com.galo.minhasfinancas.domain.entity.EntityBase;
import com.galo.minhasfinancas.framework.api.execution.ExecutionAbs;
import com.galo.minhasfinancas.framework.modelview.factory.ListFactory;
import com.galo.logstach.group.Logs;
import com.galo.logstach.interfaces.LogProcessInterface;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.galo.minhasfinancas.domain.enums.ColorEnum.DANGER;
import static com.galo.minhasfinancas.domain.enums.ColorEnum.SUCCESS;
import static com.galo.minhasfinancas.exceptions.exceptions.adpters.FieldErrorAdpter.listErrorAdater;
import static com.galo.minhasfinancas.sources.StringUtil.getUUID;

public abstract class ControllerGeneric<dto extends DTO> extends ModelViewGeneric<dto> {

  public static final String IMPOSSIBLE = "Não foi possível editar esse item";
  @Autowired
  protected Logs logs;
  @Autowired
  protected ListFactory listObjectosFactory;
  @Autowired
  protected HttpRequest request;
  protected Class<dto> tClass;
  protected List listaDto = null;
  protected final ExecutionAbs service;

  protected String origin() {
    HttpRequest request1 = this.request;
    return request1.getRequest().getHeader("referer");
  }

  @SuppressWarnings("all")
  public ControllerGeneric(ExecutionAbs service, dto dto, String url_base) {
    super(url_base);
    this.tClass = (Class<dto>) dto.getClass();
    this.service = service;
  }

  @GetMapping(value = "/ativo/{id}")
  public ResponseEntity<dto> findUpdateAtivoById(final @PathVariable Integer id) {
    try (LogProcessInterface log = logs.get("[KEY] " + getUUID())) {
      log.info(request.format("[GET] [findUpdateAtivoById] - [REQUEST]  @PathVariable : %s", id));
      EntityBase t = service.isAtivo(id);
      dto dto = (dto) service.toDTO(t);
      log.info(request.format("[GET] [findUpdateAtivoById] - [RESPONSE] @DTO: : %s", dto));
      return ResponseEntity.ok().body(dto);
    }
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<dto> findById(final @PathVariable Integer id) {
    try (LogProcessInterface log = logs.get("[KEY] " + getUUID())) {
      log.info(request.format("[GET] [findById] - [REQUEST] @PathVariable : %s", id));
      EntityBase t = service.find(id);
      dto dto = (dto) service.toDTO(t);
      log.info(request.format("[GET] [findById] - [RESPONSE] @DTO : %s", dto));
      return ResponseEntity.ok().body(dto);
    }
  }

  @PostMapping(value = "/query")
  public ResponseEntity<Object> query(final @RequestBody String json) {
    try (LogProcessInterface log = logs.get("[KEY] " + getUUID())) {
      log.info(request.format("[POST] [query] - [REQUEST] @RequestBody : %s", json));
      Object obj = service.execut(json);
      log.info(request.format("[POST] [query] - [RESPONSE] @OBJ : %s", obj));
      return ResponseEntity.ok().body(obj);
    }
  }

  @PostMapping(value = "/model")
  public ModelAndView modelQuery(final @RequestBody String json) {
    try (LogProcessInterface log = logs.get("[KEY] " + getUUID())) {
      log.info(request.format("[POST] [modelQuery] - [REQUEST] @RequestBody : %s", json));
      Object query = service.execut(json);
      log.info(request.format("[POST] [modelQuery] - [RESPONSE] @OBJ : %s", query));
      return newFormPageOfObject(query);
    }
  }

  @SuppressWarnings("all")
  @GetMapping(value = "/novo")
  public ModelAndView Novo() throws InstantiationException, IllegalAccessException {
    try (LogProcessInterface log = logs.get("[KEY] " + getUUID())) {
      log.info(request.format("[GET] [Novo] - [REQUEST] "));
      dto dto = tClass.newInstance();
      dto.setPersistido(false);
      dto.setAtivo(true);
      Map hm = listObjectosFactory.map(dto);
      log.info(request.format("[GET] [Novo] - [RESPONSE] @OBJ : %s, @listObjectosFactory : %s", dto, hm));
      return newFormPageCustom(hm, dto, null);
    }
  }

  @SuppressWarnings("all")
  @PostMapping(value = "/novo")
  public ModelAndView Novo(final dto dto, BindingResult result) {
    try (LogProcessInterface log = logs.get("[KEY] " + getUUID())) {
      log.info(request.format("[POST] [Novo] - [REQUEST] @T dto: %s, @BindingResult: %s", dto, result));
      if (result.hasErrors()) {
        log.error(request.format("[POST] [Novo] - [RESPONSE] @hasErrors : %s", result.getAllErrors()));
        return newFormPageCustom(listObjectosFactory.map(dto), dto, result.getAllErrors());
      }
      try {
        setImgUser(dto);
        reiniciarVariaveisDeClasseESalva(dto);
      } catch (Exception e) {
        log.error(request.format("[POST] [Novo] - [RESPONSE] @Exception : %s", e.getMessage()));
        return newFormPageCustom(listObjectosFactory.map(dto), dto, listErrorAdater(e, dto.getClass().getSimpleName()));
      }
      listaDto = service.findAll();
      log.info(request.format("[POST] [Novo] - [RESPONSE] @ListDto : %s", listaDto));
      return listPage(listaDto, SUCCESS_MESSAGE, null, SUCCESS);
    }
  }

  protected void setImgUser(final dto dto) {
    if (dto instanceof UsuarioDTO) {
      UsuarioDTO usuario = (UsuarioDTO) dto;
      if (null != usuario.getFoto() && !usuario.getFoto().equals("data:image/jpeg;base64,"))
        usuario.setImgbytes(Base64.decodeBase64(usuario.getFoto().substring(23)));
    }
  }

  @SuppressWarnings("all")
  @PostMapping(value = "/foto")
  public ModelAndView Foto(final dto dto, final @RequestParam(value = "file") MultipartFile file, BindingResult result) {
    try (LogProcessInterface log = logs.get("[KEY] " + getUUID())) {
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
      listaDto = service.findAll();
      log.info(request.format("[POST] [Novo] - [RESPONSE] @ListDto : %s", listaDto));
      return listPage(listaDto, SUCCESS_MESSAGE, null, SUCCESS);
    }
  }

  @GetMapping("/list")
  public ModelAndView listar() {
    try (LogProcessInterface log = logs.get("[KEY] " + getUUID())) {
      log.info(request.format("[GET] [listar] - [REQUEST]"));
      listaDto = service.findAll();
      log.info(request.format("[GET] [listar] - [RESPONSE] @listaDto : %s", listaDto));
      return listPage(listaDto, null, null, SUCCESS);
    }
  }

  @SuppressWarnings("all")
  @GetMapping("editar")
  public ModelAndView editar(final @RequestParam("id") Integer id) {
    try (LogProcessInterface log = logs.get("[KEY] " + getUUID())) {
      log.info(request.format("[GET] [editar] - [REQUEST] @RequestParam id %s", id));
      EntityBase t = service.find(id);
      dto dto = (dto) service.toDTO(t);
      if (!Objects.isNull(dto)) {
        dto.setPersistido(true);
        Map hm = listObjectosFactory.map(dto);
        log.info(request.format("[GET] [editar] - [RESPONSE] @OBJ : %s, @listObjectosFactory : %s", dto, hm));
        return editFormPage(hm, dto, null);
      }

      if (Objects.isNull(listaDto)) {
        listaDto = service.findAll();
      }
      log.info(request.format("[GET] [editar] - [RESPONSE] @OBJ : %s", listaDto));
      return listPage(listaDto, IMPOSSIBLE, null, SUCCESS);
    }
  }

  @SuppressWarnings("all")
  @PostMapping("editar")
  public ModelAndView editar(final dto dto, BindingResult result) {
    try (LogProcessInterface log = logs.get("[KEY] " + getUUID())) {
      log.info(request.format("[POST] [editar] - [REQUEST] @T dto: %s, @BindingResult: %s", dto, result));
      if (result.hasErrors()) {
        log.error(request.format("[POST] [editar] - [RESPONSE] @hasErrors : %s", result.getAllErrors()));
        return editFormPage(listObjectosFactory.map(dto), dto, result.getAllErrors());
      }
      try {
        setImgUser(dto);
        reiniciarVariaveisDeClasseESalva(dto);
      } catch (Exception e) {
        log.error(request.format("[POST] [editar] - [RESPONSE] @Exception : %s", e.getMessage()));
        return editFormPage(listObjectosFactory.map(dto), dto, listErrorAdater(e, dto.getClass().getSimpleName()));
      }
      listaDto = service.findAll();
      log.info(request.format("[POST] [editar] - [RESPONSE] @ListDto : %s", listaDto));
      return listPage(listaDto, EDITABLE_MESSAGE, null, SUCCESS);
    }
  }

  @GetMapping("remover")
  public ModelAndView remover(final @RequestParam("id") Integer id) {
    try (LogProcessInterface log = logs.get("[KEY] " + getUUID())) {
      try {
        log.info(request.format("[GET] [remover] - [REQUEST] @RequestParam id %s", id));
        service.delete(id);
        listaDto = service.findAll();
        log.info(request.format("[GET] [remover] - [RESPONSE] @ListDto : %s", listaDto));
        return listPage(listaDto, null, null, SUCCESS);
      } catch (Exception e) {
        log.error(request.format("[GET] [remover] - [RESPONSE] @Exception : %s", e.getMessage()));
        return listPage(listaDto, REMOVE_MESSAGE + e.getCause().getCause().getMessage(), null, DANGER);
      }
    }
  }

  public void reiniciarVariaveisDeClasseESalva(final dto dto) {
    if (dto.isPersistido()) {
      service.update(dto);
    } else {
      service.insert(dto);
    }
    this.listaDto = null;
  }

}
