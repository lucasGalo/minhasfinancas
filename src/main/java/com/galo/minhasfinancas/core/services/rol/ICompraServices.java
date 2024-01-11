package com.galo.minhasfinancas.core.services.rol;

import com.galo.minhasfinancas.domain.dto.ItemDTO;
import com.galo.minhasfinancas.domain.dto.PagamentoDTO;
import com.galo.minhasfinancas.domain.entity.Compra;

public abstract class ICompraServices {
  public abstract Compra addItem(ItemDTO item);
  public abstract Compra addPagamento(PagamentoDTO item);

}
