#!/usr/bin/env bash
/opt/tomcat/bin/startup.sh
redis-server /etc/redis.conf &
nginx
service rabbitmq-server start
nohup mongod -dbpath /data/db/ -logpath /data/db/mongo.log -logappend -fork -port 27017 &
nohup /opt/zookeeper-3.4.10/bin/zkServer.sh start &
