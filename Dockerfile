FROM maven:alpine

WORKDIR /usr/src/app

COPY pom.xml .

RUN mvn package

FROM openjdk:8-jre-alpine

WORKDIR /app

COPY --from=0 /app/target/watch-order-rest-0.0.1.jar /app/watch-order-rest.jar

CMD ["java -jar watch-order-rest.jar"] 



