package com.galo.minhasfinancas.config.beans;

import com.galo.minhasfinancas.config.security.UserUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Formatter;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component("HttpRequest")
public class HttpRequest {

  @Autowired
  protected HttpServletRequest request;

  private String session;
  private String ip;
  private String metodo;
  private String URI;
  private String headferNames;

  public HttpServletRequest getRequest() {
    return request;
  }

  public String getSession() {
    return request.getRequestedSessionId();
  }

  public String getIp() {
    return request.getRemoteAddr();
  }

  public String getMetodo() {
    return request.getMethod();
  }

  public String getURI() {
    return request.getRequestURI();
  }

  public String getHeadferNames() {
    return request.getHeaderNames().toString();
  }

  @Override
  public String toString() {
    return "HttpRequest {"
            + "Guid: '" + UserUtil.getIdUserLogado() + '\''
            + ", Session:'" + getSession() + '\''
            + ", ip:'" + getIp() + '\''
            + ", metodo:'" + getMetodo() + '\''
            + ", URI:'" + getURI() + '\''
            + ", Headers " + headers()
            + '}';
  }

  public String format(String format, Object... args) {
    String s = "Guid: '" + UserUtil.getIdUserLogado() + '\''
            + ", Session: '" + getSession() + '\''
            + ", ip: '" + getIp() + '\''
            + ", " + format;
    return new Formatter().format(s, args).toString();
  }

  public String headers() {
    Map<String, List<String>> headersMap = Collections.list(getRequest().getHeaderNames())
            .stream()
            .collect(Collectors.toMap(
                    Function.identity(),
                    h -> Collections.list(getRequest().getHeaders(h))
            ));
    return headersMap.toString();
  }
}
