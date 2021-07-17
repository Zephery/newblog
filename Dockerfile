FROM openjdk:11

MAINTAINER 1570631036@qq.com

RUN apt-get update && apt-get install python -y && apt-get install python3-pip -y && apt-get clean \
    && pip3 install redis

ADD src/main/resources/pythonfiles/getbaidu.py /app/getbaidu.py

COPY target/*.war /app/newblog.war

WORKDIR /app

CMD ["java","-Dspring.profiles.active=prod,common","-jar","/app/newblog.war"]
