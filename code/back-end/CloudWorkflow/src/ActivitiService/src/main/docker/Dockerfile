FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD activiti-service-0.0.1-SNAPSHOT.jar app.jar
RUN mkdir -p /home/stack/hqk/admission/
EXPOSE 8763
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]