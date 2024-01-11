package com.galo.minhasfinancas.config.beans;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class Details implements EnvironmentAware {

  private String datasourceURL;
  private String contextPath;
  @Override
  public void setEnvironment(Environment env) {
    this.datasourceURL = env.getProperty("spring.datasource.url");
    this.contextPath = env.getProperty("server.servlet.contextPath");
  }

  public String getDatasourceURL() {return datasourceURL;}
  public String getContextPath() {return contextPath;}
}
