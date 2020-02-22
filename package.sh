#!/usr/bin/env bash

kill -9 $(netstat -nlp | grep :8080 | awk '{print $7}' | awk -F"/" '{ print $1 }')
cd /data/newblog/
git fetch --all
git reset --hard origin/master
git pull origin master

mvn clean install -Dmaven.test.skip=true

nohup java -jar target/newblog-1.0.jar &

curl http://www.wenzhihuai.com

python3 /data/newblog/src/main/resources/pythonfiles/getbaidu.py

