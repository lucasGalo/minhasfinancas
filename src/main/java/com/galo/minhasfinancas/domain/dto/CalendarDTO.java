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

public class CalendarDTO extends DTO {

  private List<RendaDTO> rendas = new ArrayList<>();
  private List<CompraDTO> compras = new ArrayList<>();
  private List<GruppingDTO> gruppings = new ArrayList<>();
  private Map<Integer, List<GruppingDTO>> gruppingProAno = new HashMap<>();
  private List<Events> events = new ArrayList<>();
  public CalendarDTO() {}
  public List<CompraDTO> getCompras() {return compras;}
  public void setCompras(List<CompraDTO> compras) {this.compras = compras;}

  public String getCalendarsToJson() {return toJson(getEvents());}

  public List<Events> getEvents() {
    if(events == null || events.size() == 0)
      events = getEventsCompras();
    return events;
  }

  private List<Events> getEventsCompras() {
    List<Events> e = new ArrayList<>();
    for (GruppingDTO c : getGruppings()){
     e.add(new Events(c.getCategoria().toUpperCase()+", "+c.getVlr()+"R$", c.getDate(), "f56954", "f56954", false));
    }
    return e;
  }

  public List<GruppingDTO> getGruppings() {
    if(gruppings == null || gruppings.size()==0) this.gruppings = getGrupping();
    return gruppings;
  }

  private List<GruppingDTO> getGrupping(){
    List<GruppingDTO> gruppings = new ArrayList<>();
    gruppings.addAll(rendas.stream().map(it -> new GruppingDTO(it.getNome(),it.getStatusRenda().getValue(), it.getVlr(), it.getDate(), it.getTipo().getNome(), it.getTipo().getIcone(), it.getTipo().getCor(), RendaDTO.class.getSimpleName())).collect(Collectors.toList()));
    gruppings.addAll(compras.stream().map(it -> new GruppingDTO(it.getNome(),it.getStatusCompra().getValue(), -1*it.getVlr(), it.getDate(), it.getCategoria().getNome(), it.getCategoria().getIcone(), it.getCategoria().getCor(), CompraDTO.class.getSimpleName())).collect(Collectors.toList()));
    return gruppings;
  }

  public Map<Integer, List<GruppingDTO>> getGruppingProAno() {
    if(gruppingProAno == null || gruppingProAno.size()==0) this.gruppingProAno = getListPorAno();
    return gruppingProAno;
  }


  private Map<Integer, List<GruppingDTO>> getListPorAno() {return gruppings.stream().collect(groupingBy(element -> Integer.parseInt(toFormate(element.getDate(), "YYYY"))));}
  public Map<String, List<GruppingDTO>> getListMesDiaAdia(List<GruppingDTO> list) {return list.stream().collect(groupingBy(element -> toFormate(element.getDate(), "dd/MM/yyyy")));}

  public double getTotalMes(List<GruppingDTO> list, String klas){return list.stream().filter(it -> it.getMae().equals(klas)).flatMapToDouble(it ->  DoubleStream.of(it.getVlr())).sum();}
  public double getTotal(List<GruppingDTO> list){return list.stream().flatMapToDouble(it ->  DoubleStream.of(it.getVlr())).sum();}

  public Map<String, List<GruppingDTO>> getListDeComprasPorMes(Integer ano) {
    Map<String, List<GruppingDTO>>  maps =  new HashMap<>();
    for (Mes mes : Mes.values()) {
      maps.put(mes.getValue(), getComprasMes(mes, ano));
    }
    return maps;
  }

  private List<GruppingDTO> getComprasMes(Mes mes, Integer ano){return getListPorAno().get(ano).stream().filter(element -> Integer.parseInt(toFormate(element.getDate(), "MM")) == mes.getCod()).collect(Collectors.toList());}

  public String getMesAtual(){return Mes.toEnum(Integer.parseInt(toFormate(new Date(), "MM"))).getValue();}
  public int getAnoAtual(){return Integer.parseInt(toFormate(new Date(), "YYYY"));}

  public List<RendaDTO> getRendas() {return rendas;}
  public void setRendas(List<RendaDTO> rendas) {this.rendas = rendas;}
}
