
FROM java:8-jdk-alpine
COPY ./target/ayoholdings-0.0.1-SNAPSHOT.jar /usr/app/
EXPOSE 8080
WORKDIR /usr/app
RUN sh -c 'touch ayoholdings-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java","-jar","ayoholdings-0.0.1-SNAPSHOT.jar"]
