package com.galo.minhasfinancas.framework.api.resources;

import com.galo.logstach.group.Logs;
import com.galo.logstach.interfaces.LogProcessInterface;
import com.galo.minhasfinancas.config.beans.HttpRequest;
import com.galo.minhasfinancas.framework.api.execution.ExecutionAbs;
import com.galo.minhasfinancas.domain.dto.DTO;
import com.galo.minhasfinancas.domain.entity.EntityBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.galo.minhasfinancas.sources.StringUtil.getUUID;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@SuppressWarnings("all")
public abstract class AbsResource<T extends EntityBase, J extends ExecutionAbs, R extends DTO> {

    @Autowired protected Logs logs;
    @Autowired protected Environment env;
    @Autowired protected HttpRequest request;

    public J service;
    protected static final String ADMIN_HAS_ANY_ROLE = "hasAnyRole('ADMIN')";

    public AbsResource(J service) {
        this.service = service;
    }

    @RequestMapping(value = "/ativo/{id}", method = GET)
    public ResponseEntity<R> isAtivo(@PathVariable Integer id) {
        try (LogProcessInterface log = logs.get("[KEY] "+getUUID())) {
            log.info(request.format("[GET] [isAtivo] - [REQUEST]  @PathVariable : %s", id));
            T t = (T) service.isAtivo(id);
            R dto = (R) service.toDTO(t);
            log.info(request.format("[GET] [isAtivo] - [RESPONSE] @OBJ: : %s", t));
            return ResponseEntity.ok().body(dto);
        }
    }

    @RequestMapping(value = "/{id}", method = GET)
    public ResponseEntity<R> findById(@PathVariable Integer id) {
        try (LogProcessInterface log = logs.get("[KEY] "+getUUID())) {
            log.info(request.format("[GET] [find] - [REQUEST]  @PathVariable : %s", id));
            T t = (T) service.find(id);
            R dto = (R) service.toDTO(t);
            log.info(request.format("[GET] [find] - [RESPONSE] @OBJ: : %s", t));
            return ResponseEntity.ok().body(dto);
        }
    }

    // @PreAuthorize(value = ADMIN_HAS_ANY_ROLE) // removido para realizar os testes de integração
    @RequestMapping(method = POST)
    public ResponseEntity<R> insert(@RequestBody R obj) {
        try (LogProcessInterface log = logs.get("[KEY] "+getUUID())) {
            log.info(request.format("[POST] [insert] - [REQUEST] @RequestBody : %s", obj));
            T t = (T) service.insert(obj);
            R dto = (R) service.toDTO(t);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}").buildAndExpand(dto.getId()).toUri();
            log.info(request.format("[POST] [insert] - [RESPONSE] @OBJ : %s, @uri : %s", dto, uri));
            return ResponseEntity.created(uri).build();
        }
    }

    @RequestMapping(value = "/query", method = POST)
    public ResponseEntity<Object> query(@RequestBody String json) {
        try (LogProcessInterface log = logs.get("[KEY] "+getUUID())) {
            log.info(request.format("[POST] [query] - [REQUEST] @RequestBody : %s", json));
            Object obj = service.execut(json);
            log.info(request.format("[POST] [query] - [RESPONSE] @OBJ : %s", obj));
            return ResponseEntity.ok().body(obj);
        }
    }

    //@PreAuthorize(value = PreAuthorize) // removido para realizar os testes de integração
    @RequestMapping(value = "/dto/{id}", method = PUT)
    public ResponseEntity<R> update(@RequestBody R dto, @PathVariable Integer id) {
        try (LogProcessInterface log = logs.get("[KEY] "+getUUID())) {
            log.info(request.format("[PUT] [update] - [REQUEST] @RequestBody : %s, @PathVariable : %s", dto, id));
            T obj = (T) service.save(dto);
            obj.setId(id);
            obj = (T) service.update(obj);
            R r = (R) service.toDTO(obj);
            log.info(request.format("[PUT] [update] - [RESPONSE] @OBJ : %s", r));
            return ResponseEntity.ok().body(r);
        }
    }

    //@PreAuthorize(value = PreAuthorize)
    @RequestMapping(value = "/{id}", method = DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try (LogProcessInterface log = logs.get("[KEY] "+getUUID())) {
            log.info(request.format("[DELETE] [delete] - [REQUEST] @PathVariable : %s", id));
            service.delete(id);
            log.info("[DELETE] [delete] - [RESPONSE]");
            return ResponseEntity.noContent().build();
        }
    }

    //@PreAuthorize(value = ADMIN_HAS_ANY_ROLE)
    @RequestMapping(method = GET)
    public ResponseEntity findAll() {
        try (LogProcessInterface log = logs.get("[KEY] "+getUUID())) {
            log.info("[GET] [findAll] - [REQUEST] ");
            final List listDto = service.findAll();
            log.info(request.format("[GET] [findAll] - [RESPONSE] @listDto : %s", listDto));
            return ResponseEntity.ok().body(listDto);
        }
    }

    @RequestMapping(value = "/page", method = GET)
    public ResponseEntity<Page<R>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        try (LogProcessInterface log = logs.get("[KEY] "+getUUID())) {
            log.info(request.format("[GET] [findPage] - [REQUEST] @page : %s; @linesPerPage : %s; ; @orderBy : %s; @direction : %s ", page, linesPerPage, orderBy, direction));
            Page<T> list = service.findPage(page, linesPerPage, orderBy, direction);
            log.info(request.format("[GET] [findPage] - [RESPONSE] @list : %s", list));
            return ResponseEntity.ok().body(service.toPageDTO(list));
        }
    }
}
