FROM maven:3.8-openjdk-11

COPY . .

RUN mvn clean install

ENTRYPOINT [ "mvn spring-boot:run" ] 