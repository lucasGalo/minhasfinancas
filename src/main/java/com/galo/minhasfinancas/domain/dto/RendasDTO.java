package com.galo.minhasfinancas.domain.dto;

import com.galo.minhasfinancas.domain.enums.Mes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import static com.galo.minhasfinancas.sources.DateUtil.toFormate;

public class RendasDTO extends DTO {
  private final List<RendaDTO> rendas;
  public RendasDTO(List<RendaDTO> rendas) {this.rendas = rendas;}
  public List<RendaDTO> getRendas() {return rendas;}

  public Map<String, List<RendaDTO>> getListDeRendasPorMes() {
    Map<String, List<RendaDTO>>  maps =  new HashMap<>();
    for (Mes mes : Mes.values()) {
      maps.put(mes.getValue(), getRendasMes(mes));
    }
    return maps;
  }

  private List<RendaDTO> getRendasMes(Mes mes){return rendas.stream().filter(element -> Integer.parseInt(toFormate(element.getDate(), "MM")) == mes.getCod()).collect(Collectors.toList());}
  public double getQtdCategorias(){return rendas.stream().flatMapToDouble(it ->  DoubleStream.of(it.getTipo().getId())).count();}
  public double getTotalMes(String mes){return getRendasMes(Mes.valueOf(mes)).stream().flatMapToDouble(it ->  DoubleStream.of(it.getVlr())).sum();}
  public double getTotal(){return rendas.stream().flatMapToDouble(it ->  DoubleStream.of(it.getVlr())).sum();}

}
