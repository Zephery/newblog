FROM openjdk:11

MAINTAINER 1570631036@qq.com

COPY *.jar /app/newblog.jar

WORKDIR /app

CMD ["java","-Dspring.profiles.active=prod,common","-jar","/app/newblog.jar"]