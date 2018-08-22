FROM maven:alpine

WORKDIR /usr/src/app

COPY pom.xml .

RUN mvn package

ADD ./target/watch-order-rest-0.0.1.jar /app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-jar","/app.jar"]
