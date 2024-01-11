package com.galo.minhasfinancas.core.services.rol;

import com.galo.minhasfinancas.core.execution.CompraImpl;
import com.galo.minhasfinancas.domain.dto.DTO;
import com.galo.minhasfinancas.domain.dto.ItemDTO;
import com.galo.minhasfinancas.domain.dto.PagamentoDTO;
import com.galo.minhasfinancas.domain.entity.Compra;

public class CompraServices extends ICompraServices {
  public static CompraImpl impl;

  public CompraServices(CompraImpl impl) {
    CompraServices.impl = impl;
  }


  @Override
  public Compra addItem(ItemDTO dto) {
    return null;
  }
  @Override
  public Compra addPagamento(PagamentoDTO dto) {
    return null;
  }

  private static boolean isaBoolean(DTO dto) {
    return null != dto && null != dto.getId();
  }
}