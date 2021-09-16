FROM ubuntu
MAINTAINER "deivisomreis@gmail.com"


ARG JAR_FILE=target/*.war
RUN mkdir /opt/tomcat/

RUN apt-get update
RUN apt-get install -y curl
RUN apt-get install -y unzip
RUN apt-get install -y openjdk-8-jdk
RUN java -version

WORKDIR /opt/tomcat
RUN curl -O https://archive.apache.org/dist/tomcat/tomcat-8/v8.0.17/bin/apache-tomcat-8.0.17.zip
RUN unzip apache*.zip

RUN mv apache-tomcat-8.0.17/* /opt/tomcat/.

RUN rm -rf webapps && mkdir -p webapps/ROOT

COPY ${JAR_FILE} /opt/tomcat/webapps/ROOT

WORKDIR /opt/tomcat/webapps/ROOT
RUN unzip ctf.war && rm ctf.war

WORKDIR /opt/tomcat/bin
RUN chmod +x *.sh

EXPOSE 8080
CMD [ "/opt/tomcat/bin/catalina.sh", "run" ]