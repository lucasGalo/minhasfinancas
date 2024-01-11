package com.galo.minhasfinancas.config.interceptor;

import com.galo.logstach.group.Logs;
import com.galo.logstach.interfaces.LogProcessInterface;
import com.galo.minhasfinancas.config.beans.HttpRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Classe utilizada para intercetar requisições REST, chamadas aos serviços e acesso aos repositórios.
 *
 * @author Lucas D. Galo
 * @version 1.0.0
 */
@Aspect
@Component
@Profile("dev")
@ConditionalOnProperty(value="spring.jpa.hibernate.ddl-auto", havingValue = "none")
public class LoggerInterceptor {

  @Autowired private Logs logs;
  @Autowired protected HttpRequest request;

  /**
   * {@link Pointcut} para capturar todas as requisições de classes que
   * herdam funcionalidades de {@link Repository}
   */
  @Pointcut("execution(public !void org.springframework.data.repository.Repository+.*(..))")
  public void springDataAopInterceptor() {

  }

  /**
   * {@link Pointcut} para capturar todas as requisições de classes que pertencem aos seguintes pacotes:
   * <ul>
   * <li>com.galo.minhasfinancas.core.execution.*</li>
   * <li>com.galo.minhasfinancas.core.services.*</li>
   * </ul>
   */
  @Pointcut("execution(* com.galo.minhasfinancas.core..*(..))  "
//    "execution(* com.galo.blockefit.entrypoint.resources.impl..*(..)) || " +
//          +"execution(* com.galo.minhasfinancas.core.services..*(..))"
          +"|| execution(* com.galo.minhasfinancas.entrypoint.modelview..*(..))"
  )
  public void pacotesCore() {
  }

  /**
   * @param jp JoinPoint que irá executar a definição do {@link Around}.
   * @return A próxima execução ou método invocado.
   * @throws Throwable lança exceção caso dê algo não esperado.
   */
  @Around("pacotesCore()")
  public Object interceptarPacotesBase(ProceedingJoinPoint jp) throws Throwable {
    String metodo = jp.getSignature().getName();
    String classe = jp.getTarget().getClass().getName();
    addTelemetria();
    return processarJoinPoint(jp, metodo, classe);
  }

  /**
   * @param jp         JoinPoint que irá executar a definição do {@link Around}.
   * @param repository Classe que implementa {@link Repository}.
   * @return A próxima execução ou método invocado.
   * @throws Throwable lança uma exceção caso aconteça algo errado.
   */
  @SuppressWarnings("rawtypes")
  @Around("springDataAopInterceptor() && target(repository)")
  public Object springDataInterceptor(ProceedingJoinPoint jp, Repository repository) throws Throwable {
    Class<?>[] interfaces = jp.getTarget().getClass().getInterfaces();
    Class<?> anInterface = interfaces[0];
    String methodName = jp.getSignature().getName();
    String interfaceName = anInterface.getName();

    return processarJoinPoint(jp, methodName, interfaceName);
  }

  /**
   * @param jp     JoinPoint que irá executar a definição do {@link Around}.
   * @param metodo Nome do método que está sendo executado.
   * @param classe Nome da classe executada.
   * @return objeto a ser processado.
   * @throws Throwable lança uma exceção caso aconteça algo errado.
   */
  private Object processarJoinPoint(ProceedingJoinPoint jp, String metodo, String classe) throws Throwable {
    try {
      exibirMetodoEmExecucao(jp, metodo, classe);
      return jp.proceed();
    } catch (Exception e) {
      exibirErro(metodo, classe, e);
      throw e;
    }
  }

  /**
   * Método utilizado para registrar erros capturados durante a intercetação de classes.
   *
   * @param metodo Método que está a ser executado.
   * @param classe Classe executada.
   * @param e      {@link Exception} que foi lançada.
   */
  private void exibirErro(String metodo, String classe, Exception e) {
    try (LogProcessInterface log = logs.get("[INTERCEPTOR]")) {
      log.error(request.format("Erro ao chamar método " + classe + "." + metodo + ". Motivo: " + e));
    }
  }

  /**
   * Método utilizado para registrar em log.: classes, métodos e parâmetros em execução.
   *
   * @param jp     JoinPoint que irá executar a definição do {@link Around}.
   * @param metodo Método que está a ser executado.
   * @param classe Classe executada.
   */
  private void exibirMetodoEmExecucao(ProceedingJoinPoint jp, String metodo, String classe) {
    try (LogProcessInterface log = logs.get("[INTERCEPTOR]")) {
      log.info(request.format("Chamando método: " + classe + "." + metodo + ". Parâmetros: " + Arrays.toString(jp.getArgs())));
    }
  }

  private void addTelemetria() {
    try (LogProcessInterface log = logs.get("[TELEMETRIA]")) {
      log.info(request.toString());
    }
  }
}