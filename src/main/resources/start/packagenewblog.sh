#!/usr/bin/env bash
kill -9 $(netstat -nlp | grep :8080 | awk '{print $7}' | awk -F"/" '{ print $1 }')
cd /opt/tomcat/temp/newblog/
git fetch --all
git reset --hard origin/master
git pull origin master
rm -rf /opt/tomcat/webapps/*
cd /opt/tomcat/temp/newblog/src/main/webapp/WEB-INF
sed -in-place -e 's/develop/product/g' web.xml
cd /opt/tomcat/temp/newblog/
mvn clean install -Dmaven.test.skip=true
rm -rf /opt/tomcat/webapps/myblog.war
cp target/myblog.war /opt/tomcat/webapps/
unzip /opt/tomcat/webapps/myblog.war -d /opt/tomcat/webapps/myblog
/opt/tomcat/bin/startup.sh
cd /root/weibonlp/weiboflask
curl http://www.wenzhihuai.com
/root/anaconda3/bin/python3.6 /root/everydate/getbaidu.py


#指定日期(7天前)
DATA=`date -d "1 week ago" +%Y.%m.%d`

#当前日期
time=`date`

#删除7天前的日志
curl -XDELETE http://119.29.188.224:9200/*-${DATA}

if [ $? -eq 0 ];then
  echo ${time}"-->del $DATA log success.." >> /tmp/es-index-clear.log
else
  echo ${time}"-->del $DATA log fail.." >> /tmp/es-index-clear.log
fi


aDATA=`date -d "1 week ago" +%Y%m%d`
curl -XDELETE http://119.29.188.224:9200/*-${aDATA}-*