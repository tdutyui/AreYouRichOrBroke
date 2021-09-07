FROM openjdk:11
COPY . /ExchangeRate
WORKDIR /ExchangeRate
RUN ./gradlew build
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "build/libs/ExchangeRate.jar"]