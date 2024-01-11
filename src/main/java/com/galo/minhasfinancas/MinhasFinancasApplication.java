package com.galo.minhasfinancas;

import com.galo.logstach.group.Logs;
import com.galo.logstach.interfaces.LogProcessInterface;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;
import java.nio.charset.Charset;
import java.time.ZoneId;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import static com.galo.minhasfinancas.sources.DateUtil.anoMesDia_Hora_minutoSegundo;
import static com.galo.minhasfinancas.sources.StringUtil.getUUID;

@SpringBootApplication
@EnableGlobalMethodSecurity(securedEnabled = true)
@ComponentScan(basePackages = { "com.galo.minhasfinancas.*" })
public class MinhasFinancasApplication extends SpringBootServletInitializer implements DisposableBean {

    public static void main(String[] args) {
        SpringApplication.run(MinhasFinancasApplication.class, args);
    }

    @Autowired private Logs logs;
    @Autowired private ApplicationContext context;

    private Long inicio;

    private final String UUID = getUUID();

    @PostConstruct
    public void inicializaApp() {
        try (LogProcessInterface log = logs.get(UUID)) {
            TimeZone.setDefault(TimeZone.getTimeZone(ZoneId.of("America/Sao_Paulo")));
            logSystemInfo();
            inicio = System.currentTimeMillis();
            log.info(String.format("Inicializando aplicação: %s, versão: %s, data: %s", context.getApplicationName(), context.getId(), anoMesDia_Hora_minutoSegundo()));
        }
    }

    @Override
    public void destroy() {
        try (LogProcessInterface log = logs.get(UUID)) {
            long tempo = System.currentTimeMillis() - inicio;
            long duracao;
            String unidade;
            long minutos = TimeUnit.MILLISECONDS.toMinutes(tempo);
            long horas = TimeUnit.MILLISECONDS.toHours(tempo);
            long dias = TimeUnit.MILLISECONDS.toDays(tempo);
            if (minutos < 120) {
                duracao = minutos;
                unidade = "minutos";
            } else if (horas < 24) {
                duracao = horas;
                unidade = "horas";
            } else {
                duracao = dias;
                unidade = "dias";
            }
            log.info(String.format("Finalizando aplicação, tempo no ar: %s %s", duracao, unidade));
        }
    }
    public void logSystemInfo() {
        try (LogProcessInterface log = logs.get(UUID)) {
            try {
                OperatingSystemMXBean operatingSystemBean = ManagementFactory.getOperatingSystemMXBean();
                log.info("Operating system"
                        + " name: " + operatingSystemBean.getName()
                        + " version: " + operatingSystemBean.getVersion()
                        + " architecture: " + operatingSystemBean.getArch());

                RuntimeMXBean runtimeBean = ManagementFactory.getRuntimeMXBean();
                log.info("Java runtime"
                        + " name: " + runtimeBean.getVmName()
                        + " vendor: " + runtimeBean.getVmVendor()
                        + " version: " + runtimeBean.getVmVersion());

                MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
                log.info("Memory limit"
                        + " heap: " + memoryBean.getHeapMemoryUsage().getMax() / (1024 * 1024) + "mb"
                        + " non-heap: " + memoryBean.getNonHeapMemoryUsage().getMax() / (1024 * 1024) + "mb");

                log.info("Character encoding: "
                        + System.getProperty("file.encoding") + " charset: " + Charset.defaultCharset());

            } catch (Exception error) {
                log.error("Failed to get system info");
            }
        }
    }
}