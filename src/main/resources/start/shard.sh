#!/usr/bin/env bash
#不正确的代码

for index in $(curl -s 'http://localhost:9200/_cat/shards' | grep UNASSIGNED | awk '{print $1}' | sort | uniq); do
    for shard in $(curl -s 'http://localhost:9200/_cat/shards' | grep UNASSIGNED | grep $index | awk '{print $2}' | sort | uniq); do
        echo  $index $shard
        curl -H "Content-Type: application/json" -XPOST 'localhost:9200/_cluster/reroute' -d '{
            "commands" : [ {
                  "move" : {
                      "index" : "'$index'",
                      "shard" : "'$shard'",
                      "from_node" : "master", "to_node" : "slave"
                  }
                }
            ]
        }'
        sleep 5
    done
done
