package com.galo.minhasfinancas.core.execution;

import com.galo.minhasfinancas.dataprovider.repositories.CarteiraRepository;
import com.galo.minhasfinancas.domain.dto.CarteiraDTO;
import com.galo.minhasfinancas.domain.dto.CategoriaDTO;
import com.galo.minhasfinancas.domain.entity.Carteira;
import com.galo.minhasfinancas.domain.entity.Categoria;
import com.galo.minhasfinancas.domain.mappers.CarteiraMapper;
import com.galo.minhasfinancas.framework.api.execution.ExecutionAbs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.galo.minhasfinancas.config.security.UserUtil.getIdUserLogado;
import static com.galo.minhasfinancas.reflectionCuston.ReflexaoCustom.custom;

@Service
public class CarteiraImpl extends ExecutionAbs<Carteira, CarteiraRepository, CarteiraDTO, CarteiraMapper> {

  @Autowired protected CarteiraImpl(CarteiraRepository repo, CarteiraMapper mapper) {
    super(repo, mapper);
  }
  @Override public String getName() {
    return Carteira.class.getSimpleName();
  }

  @Override public List findAll() {return custom(carteiraRepository.findAllUsuario(getIdUserLogado()), CarteiraDTO.class,  Carteira.class);}
}
