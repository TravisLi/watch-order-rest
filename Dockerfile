FROM maven:alpine

WORKDIR /usr/src/app

COPY pom.xml .

RUN mvn package

ENTRYPOINT ["java","-jar","/usr/src/app/target/watch-order-rest-0.0.1.jar"]
