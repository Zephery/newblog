//package com.myblog.util;
//
//import com.alibaba.otter.canal.client.CanalConnector;
//import com.alibaba.otter.canal.client.CanalConnectors;
//import com.alibaba.otter.canal.protocol.CanalEntry.*;
//import com.alibaba.otter.canal.protocol.Message;
//
//import java.net.InetSocketAddress;
//import java.util.List;
//
///**
// * @author Zephery
// * @since 2017/10/18 15:23
// * Description:Canal数据库日志订阅，具体可以参考GitHub里的
// * canal会导致项目起不来 2017/10/19记录
// */
//public class CanalClientUtil {
//    public static void main(String args[]) {
//        // 创建链接
//        CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress("119.23.46.71",
//                11111), "example", "canal", "canal");
//        int batchSize = 1000;
//        int emptyCount = 0;
//        try {
//            connector.connect();
//            connector.subscribe(".*\\..*");
//            connector.rollback();
//            int totalEmtryCount = 1200;
//            while (emptyCount < totalEmtryCount) {
//                Message message = connector.getWithoutAck(batchSize); // 获取指定数量的数据
//                long batchId = message.getId();
//                int size = message.getEntries().size();
//                if (batchId == -1 || size == 0) {
//                    emptyCount++;
//                    System.out.println("empty count : " + emptyCount);
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                } else {
//                    emptyCount = 0;
//                    // System.out.printf("message[batchId=%s,size=%s] \n", batchId, size);
//                    printEntry(message.getEntries());
//                }
//
//                connector.ack(batchId); // 提交确认
//                // connector.rollback(batchId); // 处理失败, 回滚数据
//            }
//
//            System.out.println("empty too many times, exit");
//        } finally {
//            connector.disconnect();
//        }
//    }
//
//    private static void printEntry(List<Entry> entrys) {
//        for (Entry entry : entrys) {
//            if (entry.getEntryType() == EntryType.TRANSACTIONBEGIN || entry.getEntryType() == EntryType.TRANSACTIONEND) {
//                continue;
//            }
//
//            RowChange rowChage = null;
//            try {
//                rowChage = RowChange.parseFrom(entry.getStoreValue());
//            } catch (Exception e) {
//                throw new RuntimeException("ERROR ## parser of eromanga-event has an error , data:" + entry.toString(),
//                        e);
//            }
//
//            EventType eventType = rowChage.getEventType();
//            System.out.println(String.format("================> binlog[%s:%s] , name[%s,%s] , eventType : %s",
//                    entry.getHeader().getLogfileName(), entry.getHeader().getLogfileOffset(),
//                    entry.getHeader().getSchemaName(), entry.getHeader().getTableName(),
//                    eventType));
//
//            for (RowData rowData : rowChage.getRowDatasList()) {
//                if (eventType == EventType.DELETE) {
//                    printColumn(rowData.getBeforeColumnsList());
//                } else if (eventType == EventType.INSERT) {
//                    printColumn(rowData.getAfterColumnsList());
//                } else {
//                    System.out.println("-------> before");
//                    printColumn(rowData.getBeforeColumnsList());
//                    System.out.println("-------> after");
//                    printColumn(rowData.getAfterColumnsList());
//                }
//            }
//        }
//    }
//
//    private static void printColumn(List<Column> columns) {
//        for (Column column : columns) {
//            System.out.println(column.getName() + " : " + column.getValue() + "    update=" + column.getUpdated());
//        }
//    }
//}