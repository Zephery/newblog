#!/usr/bin/env bash

kill -9 $(netstat -nlp | grep :8081 | awk '{print $7}' | awk -F"/" '{ print $1 }')
cd /usr/local/app/newblog/
git fetch --all
git reset --hard origin/master
git pull origin master

mvn clean install -Dmaven.test.skip=true

nohup java -Dspring.profiles.active=prod,common -jar target/newblog-1.0.jar >/dev/null 2>&1 &

curl http://www.wenzhihuai.com

python3 /usr/local/app/newblog/src/main/resources/pythonfiles/getbaidu.py

