FROM gradle:7.6.4-jdk17 AS build

WORKDIR /app
COPY . .

EXPOSE 8080
WORKDIR /app/app

CMD ["./gradlew", "bootRun"]
