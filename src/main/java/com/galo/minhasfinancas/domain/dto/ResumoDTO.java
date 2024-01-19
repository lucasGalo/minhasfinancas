package com.galo.minhasfinancas.domain.dto;

import com.galo.minhasfinancas.domain.dto.dashboard.donut.Data;
import com.galo.minhasfinancas.domain.dto.dashboard.donut.DonutData;
import com.galo.minhasfinancas.domain.dto.dashboard.donut.PieData;
import com.galo.minhasfinancas.domain.entity.EntityBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.DoubleStream;

public class ResumoDTO extends DTO {
  private EstatisticasDTO estatisticas;
  private ManipulacaoDTO manipulacao;
  private int ano;
  public ResumoDTO() {}
  public ResumoDTO(EstatisticasDTO estatisticas, ManipulacaoDTO manipulacao, int ano) {this.estatisticas = estatisticas; this.manipulacao = manipulacao; this.ano = ano;}
  public EstatisticasDTO getEstatisticas() {return estatisticas;}
  public void setEstatisticas(EstatisticasDTO estatisticas) {this.estatisticas = estatisticas;}
  public ManipulacaoDTO getManipulacao() {return manipulacao;}
  public void setManipulacao(ManipulacaoDTO manipulacao) {this.manipulacao = manipulacao;}
  public int getAno() {return ano;}
  public void setAno(int ano) {this.ano = ano;}
  public String getPieChartToJson() {return toJson(getPieChartData());}
  public String getDonutDataDespesaToJson() {return toJson(getDunoutData());}
  public String getDonutDataRendaToJson() {return toJson(getDunoutDataRenda());}

  private List<DonutData> getDunoutDataRenda() {

    /* DONUT */

    Map<TipoDTO, List<RendaDTO>> tiposRendasPorAno = manipulacao.getTiposRendasPorAno(ano);
    List<DonutData> datas =  new ArrayList<>();

    for (TipoDTO id : tiposRendasPorAno.keySet()) {
      datas.add(new DonutData(
              id.getNome(),
              (int) tiposRendasPorAno.get(id).stream().flatMapToDouble(element -> DoubleStream.of(element.getVlr())).sum(),
              id.getCor()
      ));
    }

    return datas;
  }

  private List<DonutData> getDunoutData() {

    /* DONUT */

    Map<CategoriaDTO, List<CompraDTO>> categoriasComprasPorAno = estatisticas.getCategoriasComprasPorAno(ano);
    List<DonutData> datas =  new ArrayList<>();

    for (CategoriaDTO id : categoriasComprasPorAno.keySet()) {
      datas.add(new DonutData(
              id.getNome(),
              (int) categoriasComprasPorAno.get(id).stream().flatMapToDouble(element -> DoubleStream.of(element.getVlr())).sum(),
              id.getCor()
      ));
    }

    return datas;
  }

  private PieData getPieChartData() {

    /* DONUT */
    String[] labes = estatisticas.getCategorias().stream().map(EntityBase::getNome).toArray(String[]::new);

    String[] backgrounds = estatisticas.getCategorias().stream().map(CategoriaDTO::getCor).toArray(String[]::new);


    Map<Integer, List<CompraDTO>> comprasPorCategoria = estatisticas.getComprasPorCategoria();

    int[] data = new int[labes.length];

    for (Integer id : comprasPorCategoria.keySet()) {
      data[id] += comprasPorCategoria.get(id).stream().flatMapToDouble(element -> DoubleStream.of(element.getVlr())).sum();
    }

    Data data1 = new Data(data, backgrounds);
    return new PieData(labes, new Data[]{data1});
  }

}
