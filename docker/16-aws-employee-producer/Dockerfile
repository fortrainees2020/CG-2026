FROM openjdk:8u131-jdk-alpine
EXPOSE 7171
COPY ./target/aws-employee-0.0.1-SNAPSHOT.jar 	app.jar
ENTRYPOINT ["java","-jar","app.jar"]
