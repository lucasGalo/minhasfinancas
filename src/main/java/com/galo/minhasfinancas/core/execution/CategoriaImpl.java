package com.galo.minhasfinancas.core.execution;

import com.galo.minhasfinancas.dataprovider.repositories.CategoriaRepository;
import com.galo.minhasfinancas.domain.dto.CategoriaDTO;
import com.galo.minhasfinancas.domain.entity.Categoria;
import com.galo.minhasfinancas.domain.mappers.CategoriaMapper;
import com.galo.minhasfinancas.framework.api.execution.ExecutionAbs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.galo.minhasfinancas.config.security.UserUtil.getIdUserLogado;
import static com.galo.minhasfinancas.reflectionCuston.ReflexaoCustom.custom;

@Service
public class CategoriaImpl extends ExecutionAbs<Categoria, CategoriaRepository, CategoriaDTO, CategoriaMapper> {

  @Autowired protected CategoriaImpl(CategoriaRepository repo, CategoriaMapper mapper) {
    super(repo, mapper);
  }
  @Override public String getName() {return Categoria.class.getSimpleName();}

  @Override public List findAll() {return custom(categoriaRepository.findAllUsuario(getIdUserLogado()), CategoriaDTO.class,  Categoria.class);}
}
