# WIP: Experimental


FROM openjdk:21-jdk

ADD target/IronHide-1.0.jar app.jar

# Create logs directory
RUN mkdir -p /app/logs && chown -R 1000:1000 /app/logs
RUN mkdir -p /app/logs/accesslogs && chown -R 1000:1000 /app/logs/accesslogs

# Java options
ENV JAVA_OPTS="-server -Xms2g -Xmx4g"
ENV SPRING_PROFILES_ACTIVE=docker

ENTRYPOINT [ "sh", "-c", "java -server $JAVA_OPTS -Dspring.profiles.active=$SPRING_PROFILES_ACTIVE -jar /app.jar" ]

