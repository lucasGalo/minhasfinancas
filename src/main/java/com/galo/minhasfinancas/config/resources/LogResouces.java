package com.galo.minhasfinancas.config.resources;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Component
@ConfigurationProperties(prefix = LogResouces.LOG_PREFIX)
public class LogResouces {

    public static final String LOG_PREFIX = "log";

    private String base;
    private String path;
    private String version;

    public String getBase() {return base;}

    public String getPath() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", new Locale("pt", "BR"));
        return String.format("%s/%s", path, sdf.format(new Date()));
    }

    public String getVersion() {return version;}

    public void setBase(String base) {this.base = base;}

    public void setPath(String path) {this.path = path;}

    public void setVersion(String version) {this.version = version;}
}
