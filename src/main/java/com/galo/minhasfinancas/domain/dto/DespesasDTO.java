package com.galo.minhasfinancas.domain.dto;

import com.galo.minhasfinancas.domain.enums.Mes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import static com.galo.minhasfinancas.sources.DateUtil.toFormate;
import static java.util.stream.Collectors.groupingBy;

public class DespesasDTO extends DTO {
  private final List<CompraDTO> compras;
  public DespesasDTO(List<CompraDTO> compras ) {this.compras = compras;}
  public List<CompraDTO> getCompras() {return compras;}

  public Map<String, List<CompraDTO>> getListDeComprasPorMes() {
    Map<String, List<CompraDTO>>  maps =  new HashMap<>();
    for (Mes mes : Mes.values()) {
      maps.put(mes.getValue(), getComprasMes(mes));
    }
    return maps;
  }


  private List<CompraDTO> getComprasMes(Mes mes){return compras.stream().filter(element -> Integer.parseInt(toFormate(element.getDate(), "MM")) == mes.getCod()).collect(Collectors.toList());}
  public double getQtdCategorias(){return compras.stream().flatMapToDouble(it ->  DoubleStream.of(it.getCategoria().getId())).count();}
  public double getTotalMes(String mes){return getComprasMes(Mes.valueOf(mes)).stream().flatMapToDouble(it ->  DoubleStream.of(it.getVlr())).sum();}
  public double getTotal(){return compras.stream().flatMapToDouble(it ->  DoubleStream.of(it.getVlr())).sum();}

}
