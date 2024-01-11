FROM maven:3.8.1-openjdk-17 AS build
ENV HOME=/home/minhas-financas
COPY src $HOME/src
COPY pom.xml /home/minhas-financas
COPY lib $HOME/lib
RUN mvn -f /home/minhas-financas/pom.xml clean package -Dmaven.test.skip=true

#
# Package stage
#
FROM openjdk:19-alpine
WORKDIR /opt/minhasfinancas
ENV ARTIFACT_NAME=minhas-financas.jar
ENV APP_HOME=/opt/minhasfinancas
COPY --from=build /home/minhas-financas/target/*.jar $APP_HOME/$ARTIFACT_NAME
COPY --from=build /home/minhas-financas/lib $APP_HOME/lib
EXPOSE 12458
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","-Duser.timezone=-America/Sao_Paulo", "/opt/minhasfinancas/minhas-financas.jar"]
