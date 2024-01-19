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

public class ManipulacaoDTO extends DTO {
  private List<RendaDTO> rendas = new ArrayList<>();
  private Map<Integer, List<RendaDTO>> rendaPorAno = new HashMap<>();
  private Map<Integer, RendasDTO> rendasPorAno = new HashMap<>();
  private Map<Integer, List<RendaDTO>> rendasPorTipo = new HashMap<>();
  private final List<Mes> meses = new ArrayList<>(List.of(Mes.values()));
  public ManipulacaoDTO() {}

  public ManipulacaoDTO(List<RendaDTO> rendas) {
    this.rendas = rendas;
  }

  public List<RendaDTO> getRendas() {return rendas;}
  public void setRendas(List<RendaDTO> compras) {this.rendas = compras;}

  public List<Mes> getMeses() {return meses;}

  public Map<Integer, RendasDTO> getListRendaPorAno() {

    Map<Integer, List<RendaDTO>> yyyy = rendas.stream().collect(groupingBy(element -> Integer.parseInt(toFormate(element.getDate(), "YYYY"))));

    Map<Integer,  RendasDTO> anoDespesas = new HashMap<>();
    for ( int ano: yyyy.keySet()) {
      anoDespesas.put(ano, new RendasDTO(yyyy.get(ano)));
    }
    return anoDespesas;
  }

  public Map<Integer, RendasDTO> getRendasPorAno() {
    if(rendasPorAno == null || rendasPorAno.size()==0) this.rendasPorAno = getListRendaPorAno();
    return rendasPorAno;
  }

  public Map<Integer, List<RendaDTO>> getRendaPorAno() {
      if(rendaPorAno == null || rendaPorAno.size()==0) this.rendaPorAno = getListDeRendasPorAno();
    return rendaPorAno;
  }

  public Map<Integer, List<RendaDTO>> getRendasPorTipo() {
    if(rendasPorTipo == null || rendasPorTipo.size()==0) this.rendasPorTipo = getListDeRendasPorTipo();
    return rendasPorTipo;
  }

  public Map<TipoDTO, List<RendaDTO>> getTiposRendasPorAno(Integer ano){ return getRendasPorAno().get(ano).getRendas().stream().collect(groupingBy(RendaDTO::getTipo));}
  private Map<Integer, List<RendaDTO>> getListDeRendasPorAno() {return rendas.stream().collect(groupingBy(element -> Integer.parseInt(toFormate(element.getDate(), "YYYY"))));}
  public Map<String, List<RendaDTO>> getListDeRendasNoMesDiaAdia(List<RendaDTO> list) {return list.stream().collect(groupingBy(element -> toFormate(element.getDate(), "dd/MM/yyyy")));}
  public RendasDTO getRendas(List<RendaDTO> list){return new RendasDTO(list);}
  private Map<Integer, List<RendaDTO>> getListDeRendasPorTipo() {return rendas.stream().collect(groupingBy(element -> element.getTipo().getId()));}
  public List<TipoDTO> getTipos(){return rendas.stream().map(RendaDTO::getTipo).collect(Collectors.toList());}
  public double getQtdTipos(){return rendas.stream().flatMapToDouble(it ->  DoubleStream.of(it.getTipo().getId())).count();}
  public double getTotalMes(RendasDTO despesa, String mes){return despesa.getTotalMes(mes);}
  public double getTotalMes(List<RendaDTO> list){return list.stream().flatMapToDouble(it ->  DoubleStream.of(it.getVlr())).sum();}
  public double getSaldo(List<RendaDTO> list){return -1*list.stream().flatMapToDouble(it ->  DoubleStream.of(it.getVlr())).sum();}
  public double getTotalAno(int ano){return getListDeRendasPorAno().get(ano).stream().flatMapToDouble(it ->  DoubleStream.of(it.getVlr())).sum();}
  public double getTotal(){return rendas.stream().flatMapToDouble(it ->  DoubleStream.of(it.getVlr())).sum();}
  public String getMesAtual(){return Mes.toEnum(Integer.parseInt(toFormate(new Date(), "MM"))).getValue();}
  public int getAnoAtual(){return Integer.parseInt(toFormate(new Date(), "YYYY"));}

}
