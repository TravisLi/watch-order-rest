FROM maven:alpine

WORKDIR /usr/src/app

COPY pom.xml .

RUN mvn package

RUN apk --update add tzdata && \
    cp /usr/share/zoneinfo/Asia/Hong_Kong /etc/localtime && \
    apk del tzdata && \
    rm -rf /var/cache/apk/*

ADD target/watch-order-rest*.jar /app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom -Xmx1024m -XX:+UseConcMarkSweepGC","-jar","/app.jar"]
