package com.galo.minhasfinancas.config.statics;

public class Matches {

  public static final String ALL = "/*";

  public static final String[] PUBLIC_MATCHES = {
      "/h2-console/*"
  };
  public static final String[] PUBLIC_MATCHES_FILE_STATICS = {
      "/lte3/**",
      "/adminlte/**",
      "/commun/**",
      "/img/**",
      "/js/**",
      "/paginas/**",
      "/toastr/**"
  };
  public static final String[] PUBLIC_PAGES_FREE = {
      "/free/**",
      "/home",
      "/404",
      "/403",
      "/500",
      "/login",
      "/politicas"
  };
  public static final String[] ADMIN_MATCHES = {
      ALL,
  };
  public static final String[] CLIENTE_MATCHES_GET = {
      ALL,
      "/contatos/novo",
  };
  public static final String[] CLIENTE_MATCHES_POST = {
      "/api/telemetrias/query",
  };
}
