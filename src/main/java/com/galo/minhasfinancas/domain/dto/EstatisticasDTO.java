package com.galo.minhasfinancas.domain.dto;

import com.galo.minhasfinancas.domain.enums.Mes;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import static com.galo.minhasfinancas.sources.DateUtil.toFormate;
import static java.util.stream.Collectors.groupingBy;

public class EstatisticasDTO extends DTO {
  private List<CompraDTO> compras = new ArrayList<>();
  private Map<Integer, List<CompraDTO>> comprasPorAno = new HashMap<>();
  private Map<Integer, DespesasDTO> despesasPorAno = new HashMap<>();
  private Map<Integer, List<CompraDTO>> comprasPorCategoria = new HashMap<>();
  private final List<Mes> meses = new ArrayList<>(List.of(Mes.values()));
  public EstatisticasDTO() {}

  public EstatisticasDTO(List<CompraDTO> compras) {
    this.compras = compras;
  }

  public List<CompraDTO> getCompras() {return compras;}
  public void setCompras(List<CompraDTO> compras) {this.compras = compras;}

  public List<Mes> getMeses() {return meses;}

  public Map<Integer, DespesasDTO> getListDeDespesasPorAno() {

    Map<Integer, List<CompraDTO>> yyyy = compras.stream().collect(groupingBy(element -> Integer.parseInt(toFormate(element.getDate(), "YYYY"))));

    Map<Integer,  DespesasDTO> anoDespesas = new HashMap<>();
    for ( int ano: yyyy.keySet()) {
      anoDespesas.put(ano, new DespesasDTO(yyyy.get(ano)));
    }
    return anoDespesas;
  }

  public Map<Integer, DespesasDTO> getDespesasPorAno() {
    if(despesasPorAno == null || despesasPorAno.size()==0) this.despesasPorAno = getListDeDespesasPorAno();
    return despesasPorAno;
  }

  public Map<Integer, List<CompraDTO>> getComprasPorAno() {
      if(comprasPorAno == null || comprasPorAno.size()==0) this.comprasPorAno = getListDeComprasPorAno();
    return comprasPorAno;
  }

  public Map<Integer, List<CompraDTO>> getComprasPorCategoria() {
    if(comprasPorCategoria == null || comprasPorCategoria.size()==0) this.comprasPorCategoria = getListDeComprasPorCategoria();
    return comprasPorCategoria;
  }

  public Map<CategoriaDTO, List<CompraDTO>> getCategoriasComprasPorAno(Integer ano){ return getDespesasPorAno().get(ano).getCompras().stream().collect(groupingBy(CompraDTO::getCategoria));}

  private Map<Integer, List<CompraDTO>> getListDeComprasPorAno() {return compras.stream().collect(groupingBy(element -> Integer.parseInt(toFormate(element.getDate(), "YYYY"))));}
  public Map<String, List<CompraDTO>> getListDeComprasNoMesDiaAdia(List<CompraDTO> list) {return list.stream().collect(groupingBy(element -> toFormate(element.getDate(), "dd/MM/yyyy")));}
  public DespesasDTO getDespesas(List<CompraDTO> list){return new DespesasDTO(list);}
  private Map<Integer, List<CompraDTO>> getListDeComprasPorCategoria() {return compras.stream().collect(groupingBy(element -> element.getCategoria().getId()));}
  public List<CategoriaDTO> getCategorias(){return compras.stream().map(CompraDTO::getCategoria).collect(Collectors.toList());}
  public double getQtdCategorias(){return compras.stream().flatMapToDouble(it ->  DoubleStream.of(it.getCategoria().getId())).count();}
  public double getTotalMes(DespesasDTO despesa, String mes){return despesa.getTotalMes(mes);}
  public double getTotalMes(List<CompraDTO> list){return list.stream().flatMapToDouble(it ->  DoubleStream.of(it.getVlr())).sum();}
  public double getSaldo(List<CompraDTO> list){return -1*list.stream().flatMapToDouble(it ->  DoubleStream.of(it.getVlr())).sum();}
  public double getTotalAno(int ano){return getListDeComprasPorAno().get(ano).stream().flatMapToDouble(it ->  DoubleStream.of(it.getVlr())).sum();}
  public double getTotal(){return compras.stream().flatMapToDouble(it ->  DoubleStream.of(it.getVlr())).sum();}
  public String getMesAtual(){return Mes.toEnum(Integer.parseInt(toFormate(new Date(), "MM"))).getValue();}
  public int getAnoAtual(){return Integer.parseInt(toFormate(new Date(), "YYYY"));}

}
