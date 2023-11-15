FROM eclipse-temurin:17-jdk-jammy

ARG APP_HOME=/app
WORKDIR $APP_HOME
COPY build/libs/otus_homework-*.jar $APP_HOME/otus_homework.jar

EXPOSE 8000

ENTRYPOINT exec java $JAVA_OPTS  -jar ./otus_homework.jar
