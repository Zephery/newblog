FROM openjdk:11

MAINTAINER 1570631036@qq.com

ENV TZ=Asia/Shanghai

RUN echo 'deb https://mirrors.tuna.tsinghua.edu.cn/debian/ buster main contrib non-free\n\
  deb https://mirrors.tuna.tsinghua.edu.cn/debian/ buster-updates main contrib non-free\n\
  deb https://mirrors.tuna.tsinghua.edu.cn/debian/ buster-backports main contrib non-free\n\
  deb https://mirrors.tuna.tsinghua.edu.cn/debian-security buster/updates main contrib non-free\n'\
   > /etc/apt/sources.list

RUN apt-get update && apt-get install python3-pip -y && apt-get clean \
    && pip3 install redis

ADD src/main/resources/pythonfiles/getbaidu.py /app/getbaidu.py

COPY target/*.war /app/newblog.war

WORKDIR /app

CMD ["java","-Dspring.profiles.active=prod,common","-jar","/app/newblog.war"]
