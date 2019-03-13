#!/usr/bin/env bash
if [ $# -lt 1 ]
then
   echo 'Error,you must specify environment,just like "mvn clean package -Ptest -Dmaven.test.skip=true"'
   exit
fi

mvn clean package install -Dmaven.test.skip=true -P$1
