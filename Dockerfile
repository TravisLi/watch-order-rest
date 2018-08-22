FROM maven:3.5-jdk-8-alpine as build

WORKDIR /app

COPY pom.xml .

RUN mvn package

FROM openjdk:8-jre-alpine

ARG artifactid
ARG version

ENV artifact ${artifactid}-${version}.jar

WORKDIR /app

COPY --from=build /app/target/${artifact} /app

EXPOSE 9999

ENTRYPOINT ["sh", "-c"]
CMD ["java -jar ${artifact}"] 



