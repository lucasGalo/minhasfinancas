package com.galo.minhasfinancas.framework.modelview.factory;

import com.galo.minhasfinancas.core.execution.CarteiraImpl;
import com.galo.minhasfinancas.core.execution.CategoriaImpl;
import com.galo.minhasfinancas.core.execution.ContatoImpl;
import com.galo.minhasfinancas.core.execution.DashboardImpl;
import com.galo.minhasfinancas.core.execution.ImgImpl;
import com.galo.minhasfinancas.core.execution.ItemImpl;
import com.galo.minhasfinancas.core.execution.PagamentoImpl;
import com.galo.minhasfinancas.core.execution.CompraImpl;
import com.galo.minhasfinancas.core.execution.PesquisaImpl;
import com.galo.minhasfinancas.core.execution.RendaImpl;
import com.galo.minhasfinancas.core.execution.TelemetriaImpl;
import com.galo.minhasfinancas.core.execution.TipoImpl;
import com.galo.minhasfinancas.core.execution.UsuarioImpl;
import com.galo.minhasfinancas.domain.dto.DTO;
import com.galo.minhasfinancas.framework.api.execution.ExecutionAbs;
import com.galo.minhasfinancas.framework.modelview.enumerados.FeignListObjectsEnum;
import com.galo.minhasfinancas.framework.modelview.enumerados.ListObjectsEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ListFactory {

  public ListFactory() {}
  @Autowired
  public ListFactory(UsuarioImpl usuarioImpl, CompraImpl compraImpl, ItemImpl itemImp, PagamentoImpl pagamentoImpl, DashboardImpl dashboardImpl, ImgImpl imgImpl,
                     ContatoImpl contatoImpl, PesquisaImpl pesquisaImpl, TelemetriaImpl telemetriaIml, CarteiraImpl carteiraImpl, CategoriaImpl categoriaImpl, TipoImpl tipoImpl, RendaImpl rendaImpl) {
    this.listExecution.addAll(Arrays.asList(usuarioImpl, compraImpl, itemImp, pagamentoImpl, dashboardImpl, imgImpl, contatoImpl, pesquisaImpl, telemetriaIml, carteiraImpl, categoriaImpl, tipoImpl, rendaImpl));
  }

  private final List<ExecutionAbs> listExecution = new ArrayList<>();

  /**
   * Obtendo o Feing correspondente a uma instância passada
   */
  private <F extends ExecutionAbs> F toExecution(Class<F> feign) {
    for (ExecutionAbs obj : listExecution) {
      if (feign.isAssignableFrom(obj.getClass())) {
        return (F) obj;
      }
    }
    return null;
  }

  /**
   * Retornará a lista do dto correspondente ao nome encontrado.
   */
  private <F extends ExecutionAbs> List list(Class<F> nameFeign) {
    List list = null;

    ExecutionAbs iFeign = toExecution(nameFeign);
    if (iFeign != null) {
      list = iFeign.findAll();
    }
    return list;
  }

  /**
   * Alimentará um Map com uma lista de nomes dos obj encontrados dos dtos
   */
  public <T extends DTO> Map map(T dto) {

    Map<String, List> hm = new HashMap<>();

    for (Method p : dto.getClass().getMethods()) {// percorerá todos os métodos do dto obtendo os nomes destes.
      String name = p.getName();

      /* verificando de for uma entidade */
      FeignListObjectsEnum.Names objEnums = FeignListObjectsEnum.Names.toEnum(name);
      if (objEnums != null) {
        List list = list(objEnums.getFeign());
        if (list != null) {
          hm.put(objEnums.getList(), list);
        }
      } else {
        // caso nao for entidade
        // verifica se é um tipo enumerado
        ListObjectsEnum.Nomes obj = ListObjectsEnum.Nomes.toEnum(name);
        if (obj != null) {
          hm.put(obj.getList(), Arrays.asList(obj.getListValues()));
        }
      }
    }
    return hm;
  }
}
