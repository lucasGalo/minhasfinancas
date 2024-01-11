package com.galo.minhasfinancas.sources;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class LocalDateUtil {

  private static DateTimeFormatter formatter;

  public static LocalDate toLocalDate(String data, String formato){
    formatter = DateTimeFormatter.ofPattern(formato);
    return  LocalDate.parse(data,formatter);
  }

  public static long subtrair(Date inicio, Date fim, ChronoUnit chronoUnit ){
    LocalDate inicioLocal = inicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    LocalDate fimLocal = fim.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    return chronoUnit.between(inicioLocal, fimLocal);
  }

  public static LocalDate addDias(Date data, int dias ){
    LocalDate date = data.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    return date.plusDays(dias);
  }

  public static String format(LocalDate date,String formato){
    formatter = DateTimeFormatter.ofPattern(formato);
    return date.format(formatter);
  }

}
