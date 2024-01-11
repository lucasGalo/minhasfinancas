package com.galo.minhasfinancas.domain.dto.dashboard;

import com.galo.minhasfinancas.domain.dto.CompraDTO;
import com.galo.minhasfinancas.domain.dto.DTO;
import com.galo.minhasfinancas.domain.dto.ItemDTO;
import com.galo.minhasfinancas.domain.dto.PagamentoDTO;
import com.galo.minhasfinancas.domain.dto.usuario.UsuarioDTO;
import com.galo.minhasfinancas.domain.dto.dashboard.donut.PieData;
import com.galo.minhasfinancas.domain.dto.dashboard.sources.PiramideChart;
import com.galo.minhasfinancas.domain.dto.dashboard.sources.PiramideDatasets;
import com.galo.minhasfinancas.domain.dto.dashboard.sources.VisitorsChart;
import com.galo.minhasfinancas.domain.dto.dashboard.sources.VisitorsDatasets;
import com.galo.minhasfinancas.domain.entity.Compra;
import com.galo.minhasfinancas.domain.entity.Dashboard;
import com.galo.minhasfinancas.domain.entity.Usuario;
import com.galo.minhasfinancas.domain.enums.Mes;
import com.galo.minhasfinancas.domain.enums.StatusCompra;
import com.galo.minhasfinancas.domain.enums.Perfil;
import com.galo.minhasfinancas.domain.enums.StatusItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import static com.galo.minhasfinancas.sources.DateUtil.AnoAtualSubtrair;
import static com.galo.minhasfinancas.sources.DateUtil.MesAtual;
import static com.galo.minhasfinancas.sources.DateUtil.compare;
import static com.galo.minhasfinancas.sources.DateUtil.dataAtual;
import static com.galo.minhasfinancas.sources.DateUtil.toFormate;
import static java.util.stream.Collectors.groupingBy;

public class DashboardDTO extends DTO {
  private List<CompraDTO> pedTbl = new ArrayList<>();
  private List<UsuarioDTO> clientes = new ArrayList<>();
  private List<ItemDTO> tblItens = new ArrayList<>();
  private PieData pieData;
  private String inicio;
  private String fim;

  /* preenchimentos*/
  private List<StatusItem> statusItem;

  public DashboardDTO() {
  }

  public DashboardDTO(Dashboard obj, String classe) {
    if (null != obj) {
      setId(obj.getId());
      setNome(obj.getNome());
      setAtivo(obj.getAtivo());
      setGuid(obj.getGuid());
      setUpdateAt(obj.getUpdateAt());
      setCreateAt(obj.getCreateAt());
      if (!Objects.equals(classe, Compra.class.getSimpleName()))
        this.pedTbl = obj.getRols().stream().map(it -> new CompraDTO(it, Dashboard.class.getSimpleName())).collect(Collectors.toList());
      if (!Objects.equals(classe, Usuario.class.getSimpleName()))
        this.clientes = obj.getUsuarios().stream().map(it -> new UsuarioDTO(it, Compra.class.getSimpleName())).collect(Collectors.toList());
    }
  }

  public Usuario getUsuario() {return null;}
  public String getInicio() {return inicio;}
  public void setInicio(String inicio) {this.inicio = inicio;}
  public String getFim() {return fim;}
  public void setFim(String fim) {this.fim = fim;}
  public List<CompraDTO> getPedTbl() {return pedTbl;}
  public void setPedTbl(List<CompraDTO> pedTbl) {
    this.pedTbl = pedTbl;
  }

  public List<UsuarioDTO> getClientes() {return clientes;}
  public void setClientes(List<UsuarioDTO> clientes) {this.clientes = clientes;}
  public List<StatusItem> getStatusItem() {return statusItem;}
  public void setStatusItem(List<StatusItem> statusItem) {this.statusItem = statusItem;}
  public List<ItemDTO> getTblItens() {return tblItens;}
  public void setTblItens(List<ItemDTO> tblItens) {this.tblItens = tblItens;}

  public List<PagamentoDTO> getListPgto() {
    return getPedDiferenteDoEstado(StatusCompra.CANCELADA).stream()
            .flatMap(rol ->rol.getPagamentos().stream())
            .collect(Collectors.toList());
  }
  public List<PagamentoDTO> getListDePagamentos() {
    return getPedDiferenteDoEstado(StatusCompra.CANCELADA).stream()
            .flatMap(rol ->
                    rol.getPagamentos().stream()
//                            .filter(pagamento -> compare(getDateisNotNull(pagamento), dataAtual(), DD_MM_YYYY))
            ).collect(Collectors.toList());
  }

  public double totalGeral(){
    return getPedTbl().stream().flatMapToDouble(it ->  DoubleStream.of(it.getVlr())).sum();
  }
  public double totalSalDev(){
    return getPedTbl().stream().flatMapToDouble(it ->  DoubleStream.of(it.getVlr())).sum();
  }
  public double totalPgto(){
    return getListDePagamentos().stream().flatMapToDouble(it ->  DoubleStream.of(it.getVlr())).sum();
  }
 public List<CompraDTO> getPedNew() {
    return new ArrayList<>(getPedTbl());
  }

  public List<CompraDTO> getPedMesAtual() {
    Mes mes = MesAtual();
    Map<Integer, List<CompraDTO>> listDeRolPorMes = getListDeRolsPorMes();

    return listDeRolPorMes.get(mes.getCod() - 1);
  }

  public double getPgtosTotal() {
    return getPgtosTotalRol(getPedTbl());
  }

  private double getPgtosTotalRol(List<CompraDTO> rols) {
    double total = 0.0;
    for (CompraDTO c : rols) {
      for (PagamentoDTO w : c.getPagamentos()) {
        total += w.getVlr();
      }
    }
    return total;
  }

  private List<CompraDTO> getPedDiferenteDoEstado(StatusCompra status) {
    return this.pedTbl.stream().filter((p) -> p.getStatusCompra() != status).collect(Collectors.toList());
  }


  public List<ItemDTO> allItens(){ return getTblItens();}

  public double totalItemAnoAtual(){return allItens().stream().flatMapToDouble(it ->  DoubleStream.of(it.getVlrTotal())).sum();}
  public double totalItemAnoAnterior(){return allItensAnoAnterior().stream().flatMapToDouble(it ->  DoubleStream.of(it.getVlrTotal())).sum();}
  public List<ItemDTO> allItensAnoAnterior(){
    return getPedTbl().stream()
            .filter(it -> compare(getDateisNotNull(it), String.valueOf(AnoAtualSubtrair(1)), "YYYY"))
            .flatMap(it -> it.getItems().stream())
            .collect(Collectors.toList());}

  private Map<Integer, List<ItemDTO>> getListDeItensPorMes(List<ItemDTO> list) {
    return list.stream().collect(groupingBy(element -> Integer.parseInt(toFormate(getDateisNotNull(element), "MM")) - 1));
  }

  public String getLinearToJson() {return toJson(getLinearChartData());}
  public String getPiramideToJson() {return toJson(getPiramideChartData());}

  public VisitorsChart getLinearChartData() {

    /* Gráficos */
    final VisitorsChart VisitorsChartData = new VisitorsChart();
    String[] labes = getMeses();
    VisitorsChartData.setLabels(labes);

    final Map<Integer, List<ItemDTO>> collect = getListDeItensPorMes(allItens());
    final Map<Integer, List<ItemDTO>> anteiror = getListDeItensPorMes(allItensAnoAnterior());

    int[] data = new int[labes.length];
    int[] dataAnterior = new int[labes.length];

    for (Integer mes : collect.keySet()) {
      data[mes] += collect.get(mes).size();
    }

    for (Integer mes : anteiror.keySet()) {
      dataAnterior[mes] += anteiror.get(mes).size();
    }

    final VisitorsDatasets atual = new VisitorsDatasets("line", data, "transparent", "#007bff", "#007bff", "#007bff", false);
    final VisitorsDatasets anterior = new VisitorsDatasets("line", dataAnterior, "tansparent", "#ced4da", "#ced4da", "#ced4da", false);
    VisitorsChartData.setDatasets(List.of(atual, anterior));

    return VisitorsChartData;
  }

  public PiramideChart getPiramideChartData() {

    /* Gráficos */
    final PiramideChart piramideChartData = new PiramideChart();
    String[] labes = getMeses();
    piramideChartData.setLabels(labes);

    final Map<Integer, List<ItemDTO>> atual = getListDeItensPorMes(allItens());
    final Map<Integer, List<ItemDTO>> anterior = getListDeItensPorMes(allItensAnoAnterior());

    int[] data = new int[labes.length];
    int[] dataAnterior = new int[labes.length];

    for (Integer mes : atual.keySet()) {
      data[mes] += atual.get(mes).stream().flatMapToDouble(it ->  DoubleStream.of(it.getVlrTotal())).sum();
    }

    for (Integer mes : anterior.keySet()) {
      dataAnterior[mes] += anterior.get(mes).stream().flatMapToDouble(it ->  DoubleStream.of(it.getVlrTotal())).sum();
    }

    final PiramideDatasets atualP = new PiramideDatasets("#007bff", "#007bff",data);
    final PiramideDatasets anteriorP = new PiramideDatasets("#ced4da", "#ced4da",dataAnterior);
    piramideChartData.setDatasets(List.of(atualP, anteriorP));

    return piramideChartData;
  }

  private static String[] getMeses() {
    return new String[]{"JANEIRO", "FEVEREIRO", "MARÇO", "ABRIL", "MAIO", "JUNHO", "JULHO", "AGOSTO", "SETEMBRO", "OUTUBRO", "NOVEMBRO", "DEZEMBRO"};
  }


  public List<CompraDTO> getPedCanceladosConcluido() {
    return this.pedTbl.stream()
            .filter((p) -> p.getStatusCompra() == StatusCompra.CANCELADA || p.getStatusCompra() == StatusCompra.CONCLUIDA)
            .filter(it -> compare(getDateisNotNull(it), dataAtual(), DD_MM_YYYY))
            .collect(Collectors.toList());
  }
  public List<UsuarioDTO> getUsuariosNew() {
    return getListDeClientesPorMes().get(MesAtual().getCod() - 1);
  }
  public int getUsuariosNovosQtd() {
    List<UsuarioDTO> usuarioDTOS = getUsuariosNew();
    if (null == usuarioDTOS) return 0;
    return usuarioDTOS.size();
  }
  private Map<Integer, List<CompraDTO>> getListDeRolsPorMes() {
    return getPedDiferenteDoEstado(StatusCompra.CANCELADA).stream()
            .filter(it -> compare(getDateisNotNull(it), dataAtual(), "YYYY"))
            .collect(groupingBy(element -> Integer.parseInt(toFormate(getDateisNotNull(element), "MM")) - 1));
  }
  private Map<Integer, List<UsuarioDTO>> getListDeClientesPorMes() {
    return getClientes().stream()
            .filter(it -> it.getPerfis().contains(Perfil.CLIENTE.getCod()) && compare(getDateisNotNull(it), dataAtual(), "YYYY"))
            .collect(groupingBy(element -> Integer.parseInt(toFormate(getDateisNotNull(element), "MM")) - 1));
  }
}
