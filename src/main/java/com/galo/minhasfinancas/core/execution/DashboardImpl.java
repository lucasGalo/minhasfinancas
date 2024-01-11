package com.galo.minhasfinancas.core.execution;

import com.galo.minhasfinancas.dataprovider.repositories.DashboardRepository;
import com.galo.minhasfinancas.domain.dto.ItemDTO;
import com.galo.minhasfinancas.domain.dto.usuario.UsuarioDTO;
import com.galo.minhasfinancas.domain.dto.dashboard.DashboardDTO;
import com.galo.minhasfinancas.domain.entity.Dashboard;
import com.galo.minhasfinancas.domain.entity.Item;
import com.galo.minhasfinancas.domain.entity.Usuario;
import com.galo.minhasfinancas.domain.enums.Perfil;
import com.galo.minhasfinancas.domain.enums.StatusItem;
import com.galo.minhasfinancas.domain.mappers.DashboardMapper;
import com.galo.minhasfinancas.framework.api.execution.ExecutionAbs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DashboardImpl extends ExecutionAbs<Dashboard, DashboardRepository, DashboardDTO, DashboardMapper> {
  @Autowired
  protected DashboardImpl(DashboardRepository repo, DashboardMapper mapper) {
    super(repo, mapper);
  }

  public List allItens(){
    DashboardDTO dashboard = new DashboardDTO();
    dashboard.setTblItens(itemRepository.findAll().stream().map( it -> new ItemDTO(it, Item.class.getSimpleName())).collect(Collectors.toList()));
    return List.of(dashboard);
  }
  public List allClientes(){
    DashboardDTO dashboard = new DashboardDTO();
    dashboard.setClientes(usuarioRepository.findAllUsuarioByPerfil(Perfil.CLIENTE.getCod()).stream().map(it -> new UsuarioDTO(it, Usuario.class.getSimpleName())).collect(Collectors.toList()));
    return List.of(dashboard);
  }

  @Override
  public String getName() {
    return Dashboard.class.getSimpleName();
  }
}
