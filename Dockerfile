FROM openjdk:11

MAINTAINER 1570631036@qq.com

COPY target/*.war /app/newblog.war

WORKDIR /app

CMD ["java","-Dspring.profiles.active=prod,common","-jar","/app/newblog.war"]
