FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD target/Rakuten-DnD-1.0-SNAPSHOT.jar app.jar
ADD target/newrelic.jar apm.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS="-Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8787,suspend=n"
EXPOSE 8080 8787
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=docker -javaagent:apm.jar -jar /app.jar" ]