package com.galo.minhasfinancas.sources;

import com.galo.minhasfinancas.domain.enums.Ano;
import com.galo.minhasfinancas.domain.enums.BaseEnum;
import com.galo.minhasfinancas.domain.enums.DiaSemana;
import com.galo.minhasfinancas.domain.enums.Mes;
import com.galo.minhasfinancas.exceptions.exceptions.FormatDataException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

  public static final String MMM = "MMM";
  public static final String YYYY = "yyyy";
  public static final String DD_MM_YYYY = "dd-MM-yyyy";
  public static final String YYYY_MM_DD = "yyyy-MM-dd";
  public static final String YYYY_MM_DD_hh_mm_ss = "yyyy-MM-dd HH:mm:ss";
  public static final String DD_MM_YYYY_hh_mm_ss = "dd-MM-yyyy HH:mm:ss";

  private static SimpleDateFormat formato;

  public static final Locale LOCALE = new Locale("pt", "BR");

  public static Date subtrairDias(String data, int dias){
    Date date = toDate(data, "yyyy-MM-dd HH:mm:ss.ss");
    return subtrairDias(date, dias);
  }
  public static Date subtrairDias(Date data, int dias){
    Calendar c = Calendar.getInstance();
    c.setTime(data);
    c.add(Calendar.DAY_OF_YEAR, -dias );//adicionamos 5 dias a data aual
    return c.getTime();
  }
  public static Date getdata(String hora){
    if(null == hora) return null;
    String s = toFormate(new Date(), "dd/MM/YYYY") + " " + hora + ":00";
      return toDate(s, "dd/MM/yyyy HH:mm:ss");

  }

  public static Date toDate(String data, String format) {
    try {
      formato = new SimpleDateFormat(format);
      return formato.parse(data);
    } catch (ParseException e) {
      throw new RuntimeException("Data " + data + " não pode ser convertida no formato " + format);
    }
  }

  public static String toFormate(Date data, String format) {
    formato = new SimpleDateFormat(format);
    return formato.format(data);
  }

  public static String diaAnoMes() {
    try {
      formato = new SimpleDateFormat(DD_MM_YYYY, LOCALE);
      return formato.format(new Date());
    } catch (Exception ex) {
      throw new RuntimeException("Não foi possivel formatar a data atual do sistema");
    }
  }

  public static Date dataAtual() {
    return new Date();
  }

  public static int AnoAtualSubtrair(int qtd) {
    int i = getTypeDate(Calendar.YEAR);
    return i - qtd;
  }

  public static boolean isDateInBetweenIncludingEndPoints(final Date min, final Date max, final Date date){
    if(null == date) return false;
    return !(date.before(min) || date.after(max));
  }

  public static boolean compare(Date inicio, String fim, String formato) {

    String data2 = toFormate(inicio, formato);
    return fim.compareTo(data2) == 0;
  }
  public static boolean compare(Date inicio, Date fim, String formato) {

    String data1 = toFormate(fim, formato);
    String data2 = toFormate(inicio, formato);
    return data1.compareTo(data2) == 0;
  }

  public static Mes MesAtual() {
    try {
      int i = getTypeDate(Calendar.MONTH) + 1;
      return (Mes) BaseEnum.getEnumInstance(Mes.values(), i);
    } catch (Exception ex) {
      throw new RuntimeException("Não foi possivel obter o mês atual erro: " + ex.getMessage());
    }
  }

  public static int AnoAtualInteiro() {
    try {
      return getTypeDate(Calendar.YEAR);
    } catch (Exception ex) {
      throw new RuntimeException("Não foi possivel obter o ano atual erro: " + ex.getMessage());
    }
  }

  public static Ano AnoAtual() {
    try {
      int i = getTypeDate(Calendar.YEAR);
      return (Ano) BaseEnum.getEnumInstance(Ano.values(), i);
    } catch (Exception ex) {
      throw new RuntimeException("Não foi possivel obter o ano atual erro: " + ex.getMessage());
    }
  }

  public static String anoMesDia_Hora_minutoSegundo() {
    try {
      formato = new SimpleDateFormat(DD_MM_YYYY_hh_mm_ss, LOCALE);
      return formato.format(new Date());
    } catch (Exception ex) {
      throw new FormatDataException("Não foi possivel formatar a data atual do sistema");
    }
  }

  public static String getDiaSemana() {
    Calendar cal = Calendar.getInstance();
    DiaSemana diaSemana = (DiaSemana) BaseEnum.getEnumInstance(DiaSemana.values(), cal.get(Calendar.DAY_OF_WEEK));
    return diaSemana.getValue();
  }
  public static int getTypeDate(int type) {
    Calendar cal = Calendar.getInstance();
    return cal.get(type);
  }

  public static String visual(String data){
    String[] split = data.split("-");
    return split[2]+"/"+split[1]+"/"+split[0];
  }
}
