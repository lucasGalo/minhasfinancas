package com.galo.minhasfinancas.framework.api.execution;

import com.galo.minhasfinancas.domain.dto.DTO;
import com.galo.minhasfinancas.domain.entity.EntityBase;
import com.galo.minhasfinancas.domain.enums.BaseEnum;
import com.galo.minhasfinancas.domain.enums.StatusItem;
import com.galo.minhasfinancas.domain.mappers.MAPPER;
import com.galo.minhasfinancas.domain.querys.QUERY;
import com.galo.minhasfinancas.exceptions.exceptions.ObjectNotFoundException;
import com.galo.minhasfinancas.framework.api.action.ActionEnum;
import com.galo.minhasfinancas.framework.api.action.ListBaseActions;
import com.galo.minhasfinancas.framework.api.operation.IBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.format.Formatter;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.galo.minhasfinancas.config.security.UserUtil.getIdUserLogado;

@SuppressWarnings("rawtypes")
public abstract class ExecutionAbs<T extends EntityBase, J extends JpaRepository, R extends DTO, M extends MAPPER> extends IBaseRepository<J> implements Formatter<R> {

    @Autowired public BCryptPasswordEncoder pe ;
    protected final M _mapper;

    protected static Map<String, List> map;

    public ExecutionAbs(J repo, M mapper) {
        super(repo);
        this._mapper = mapper;
    }

    @SuppressWarnings("all")
    public T isObj(Integer id) {
        Optional<T> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + getName()));
    }

    public T find(Integer id) {
        return isObj(id);
    }
    @Transactional
    @SuppressWarnings("all")
    public T updateNotGuid(T obj) {
        isObj(obj.getId());
        obj = (T) repo.save(obj);
        return obj;
    }
    @Transactional
    @SuppressWarnings("all")
    public T update(T obj) {
        isObj(obj.getId());
        obj.setGuid(getIdUserLogado());
        obj = (T) repo.save(obj);
        map = null;
        return obj;
    }

    @Transactional
    @SuppressWarnings("all")
    public T update(R dto) {
        isObj(dto.getId());
        T t = save(dto);
        t.setGuid(getIdUserLogado());
        t = (T) repo.save(t);
        map = null;
        return t;
    }

    @Transactional
    public T insert(R obj) {
        T t = save(obj);
        t.setGuid(getIdUserLogado());
        t = (T) repo.save(t);
        map = null;
        return t;
    }

    @Transactional
    public T insert(T obj) {
        obj.setId(null);
        obj.setGuid(getIdUserLogado());
        obj = (T) repo.save(obj);
        map = null;
        return obj;
    }

    @Async
    @Transactional
    @SuppressWarnings("all")
    public void delete(Integer id) {
        map = null;
        repo.deleteById(id);
    }

    @Transactional
    public T isAtivo(Integer id) {
        T obj = isObj(id);
        obj.setGuid(getIdUserLogado());
        obj.setAtivo(!obj.getAtivo());
        return (T) repo.save(obj);
    }

    @SuppressWarnings("all")
    public Page<T> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    public String get(Class _class) {
        return _class.getSimpleName();
    }

    @SuppressWarnings("all")
    public List findAll() {
        List<T> all = repo.findAll();
        return all.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @SuppressWarnings("all")
    public Object execut(String json) {

        QUERY query = toClass(json, getQuery());

        ActionEnum action = (ActionEnum) BaseEnum.getEnumInstance(ListBaseActions.list, query.getAction());
        return action.execute(query, getService());
    }

    public T save(R dto){return objetoOrNew(dto);}

    public Page<R> toPageDTO(Page<T> page) {return page.map(this::toDTO);}

    @SuppressWarnings("all")
    public R toDTO(T obj) {
        return (R) _mapper.toDto(obj);
    }

    public abstract String getName();

    public Class<? extends QUERY> getQuery() {return null;}

    public Object getService(){return null;}

    public T objetoOrNew(R save) {
        T t =  (T) _mapper.toObj(save);

        if(!Objects.isNull(save.getId())){
            T obj = isObj(save.getId());
            t.setCreateAt(obj.getCreateAt());
        }

        isValidation(t);
        return t;
    }

    @SuppressWarnings("all")
    public void isValidation(T obj){}

    /**
     * O thymeleaf utiliza este método em conjunto com o método parse para encadear eles no select 2
     */
    @Override
    public String print(R dto, Locale locale) {
        if(dto.getId() == null) return "";
        return dto.getId().toString();
    }
    @Override
    public R parse(String id, Locale locale) throws ParseException {
        if("".equals(id)) return null;
        T t = find(Integer.valueOf(id));
        return toDTO(t);
    }

    public Map<String, List> getStringListMap() {
        if(map == null) {
            map = new HashMap<>();
            map.put("itemStatuslist", Arrays.asList(StatusItem.values()));
        }
        return map;
    }
}
