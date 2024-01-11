package com.galo.minhasfinancas.framework.modelview.enumerados;

import com.galo.minhasfinancas.core.execution.CarteiraImpl;
import com.galo.minhasfinancas.core.execution.CategoriaImpl;
import com.galo.minhasfinancas.core.execution.CompraImpl;
import com.galo.minhasfinancas.core.execution.ContatoImpl;
import com.galo.minhasfinancas.core.execution.ImgImpl;
import com.galo.minhasfinancas.core.execution.ItemImpl;
import com.galo.minhasfinancas.core.execution.PagamentoImpl;
import com.galo.minhasfinancas.core.execution.PesquisaImpl;
import com.galo.minhasfinancas.core.execution.RendaImpl;
import com.galo.minhasfinancas.core.execution.TelemetriaImpl;
import com.galo.minhasfinancas.core.execution.TipoImpl;
import com.galo.minhasfinancas.core.execution.UsuarioImpl;
import com.galo.minhasfinancas.framework.api.execution.ExecutionAbs;

public enum FeignListObjectsEnum {

  DTOS_ENUM;

  public enum Names {

    USUARIOS_DTO("usuarios", "getUsuario", UsuarioImpl.class),
    COMPRA_DTO("compras", "getCompra", CompraImpl.class),
    Item_DTO("items", "getItem", ItemImpl.class),
    Pagamento_DTO("pagamentos", "getPagamento", PagamentoImpl.class),
    Img_DTO("imgs", "getImg", ImgImpl.class),
    Contato_DTO("contatos", "getContato", ContatoImpl.class),
    Pesquisa_DTO("pesquisas", "getPesquisa", PesquisaImpl.class),
    Telemetria_DTO("telemetrias", "getTelemetria", TelemetriaImpl.class),
    CARTEIRA_DTO("carteiras", "getCarteira", CarteiraImpl.class),
    CATEGORIA_DTO("categorias", "getCategoria", CategoriaImpl.class),
    Renda_DTO("rendas", "getRenda", RendaImpl.class),
    Tipo_DTO("tipos", "getTipo", TipoImpl.class)
    ;
    private final String list;
    private final String method;
    private final Class<ExecutionAbs> feign;
    Names(final String list, final String method, final Class feign) {
      this.list = list;
      this.method = method;
      this.feign = feign;
    }
    public String getList() {
      return list;
    }

    public String getMethod() {
      return method;
    }

    public Class getFeign() {
      return feign;
    }

    /**
     * Retornará o enum correspondente ao nome do método passado params String method (deve corresponder a o mesmo nome que existe no enumerado)
     */
    public static Names toEnum(final String method) {
      int tam;
      for (Names enumerado : Names.values()) {
        tam = enumerado.method.length();
        if (method.length() >= tam) {
          if (enumerado.method.equals(method.substring(0, tam))) {
            return enumerado;
          }
        }
      }
      return null;
    }
  }
}
