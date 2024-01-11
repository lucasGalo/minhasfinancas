package com.galo.minhasfinancas.core.execution;

import com.galo.minhasfinancas.core.services.rol.CompraServices;
import com.galo.minhasfinancas.core.services.rol.ICompraServices;
import com.galo.minhasfinancas.dataprovider.repositories.CompraRepository;
import com.galo.minhasfinancas.domain.dto.CategoriaDTO;
import com.galo.minhasfinancas.domain.dto.CompraDTO;
import com.galo.minhasfinancas.domain.entity.Compra;
import com.galo.minhasfinancas.domain.mappers.CompraMapper;
import com.galo.minhasfinancas.framework.api.execution.ExecutionAbs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.galo.minhasfinancas.config.security.UserUtil.getIdUserLogado;
import static com.galo.minhasfinancas.reflectionCuston.ReflexaoCustom.custom;

@Service
public class CompraImpl extends ExecutionAbs<Compra, CompraRepository, CompraDTO, CompraMapper> {
  private final ICompraServices services;
  @Autowired
  protected CompraImpl(CompraRepository repo, CompraMapper mapper) {
    super(repo, mapper);
    this.services = new CompraServices(this);
  }

  @Override
  public String getName() {
    return Compra.class.getSimpleName();
  }

  @Override
  public List findAll() {return custom(compraRepository.findAllUsuario(getIdUserLogado()), CompraDTO.class,  Compra.class);}
}