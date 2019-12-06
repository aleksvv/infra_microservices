FROM openjdk:13-alpine
ADD target/order-0.0.1-SNAPSHOT.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "sleep 15s && java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
