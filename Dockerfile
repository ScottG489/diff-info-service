#FROM ubuntu:latest
FROM java:8

RUN mkdir /opt/diff-info-service/
#ADD https://dl.bintray.com/scottg489/maven/com/github/scottg489/diff-info-service/0.5/diff-info-service-0.5-capsule.jar /opt/diff-info-service/
ADD https://dl.bintray.com/scottg489/maven/com/github/scottg489/diff-info-service/0.6/diff-info-service-0.6-capsule.jar /opt/diff-info-service/
CMD ["java", "-jar", "/opt/diff-info-service/diff-info-service-0.5-capsule.jar", "server"]
