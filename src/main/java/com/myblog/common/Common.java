package com.myblog.common;

public class Common {
    public final static String COMMON_JSON = "{\n" +
            " \"is_robot\": false,\n" +
            " \"cmts\": [],\n" +
            " \"good\": false,\n" +
            " \"common\": false,\n" +
            " \"album_wait_audit\": false,\n" +
            " \"price\": 0,\n" +
            " \"rid\": 95800538,\n" +
            " \"buylnk\": \"\",\n" +
            " \"sender_wait_audit\": false,\n" +
            " \"zanc\": 0,\n" +
            " \"sta\": 0,\n" +
            " \"coupon_price\": 0,\n" +
            " \"albnm\": \"花花女子与花花世界\",\n" +
            " \"albid\": 5542595,\n" +
            " \"favc\": 99,\n" +
            " \"wait_audit\": false,\n" +
            " \"ruid\": 802123,\n" +
            " \"id\": 97290923,\n" +
            " \"repc\": 0\n" +
            " }";
    public final static String BAIDU_APP_ID = "10530811";

    public final static String BAIDU_APP_KEY = "LwqnDLTHYIdH3H5pOIM5H3wB";

    public final static String BAIDU_SECRET_KEY = "EIa2DECI9udOOK3acNrg3mxsIGcT7nDK";

    public final static String PYTHON_PATH = "/root/anaconda3/bin/python3.6";

    public final static String MYSQL_DUMP = "/usr/bin/mysqldump";

    public static final String TEMPLATE = "{\n" +
            "    \"order\": 0,\n" +
            "    \"index_patterns\": \"" + "newbloglogs-20*\",\n" +
            "    \"settings\": {\n" +
            "        \"refresh_interval\":\"1s\"" +
            "    },\n" +
            "    \"mappings\": {\n" +
            "        \"blog\": {\n" +
            "            \"properties\": {\n" +
            "                \"conditions\": {\n" +
            "                    \"properties\": {\n" +
            "                        \"max_age\": {\n" +
            "                            \"type\": \"text\",\n" +
            "                            \"fields\": {\n" +
            "                                \"keyword\": {\n" +
            "                                    \"type\": \"keyword\",\n" +
            "                                    \"ignore_above\": 256\n" +
            "                                }\n" +
            "                            }\n" +
            "                        },\n" +
            "                        \"max_docs\": {\n" +
            "                            \"type\": \"long\"\n" +
            "                        }\n" +
            "                    }\n" +
            "                },\n" +
            "                \"ip\": {\n" +
            "                    \"type\": \"text\",\n" +
            "                    \"fields\": {\n" +
            "                        \"keyword\": {\n" +
            "                            \"type\": \"keyword\",\n" +
            "                            \"ignore_above\": 256\n" +
            "                        }\n" +
            "                    }\n" +
            "                },\n" +
            "                \"level\": {\n" +
            "                    \"type\": \"keyword\"\n" +
            "                },\n" +
            "                \"logger\": {\n" +
            "                    \"type\": \"keyword\"\n" +
            "                },\n" +
            "                \"message\": {\n" +
            "                    \"type\": \"keyword\"\n" +
            "                },\n" +
            "                \"timestamp\": {\n" +
            "                    \"type\": \"date\"\n" +
            "                }\n" +
            "            }" +
            "        }\n" +
            "    },\n" +
            "    \"aliases\": {" +
            "   \"newbloglogs\": {}\n" +
            "  }\n" +
            "}";

    public static final String ALIASES = "{\n" +
            "  \"aliases\": {\n" +
            "    \"newbloglogs_write\": {}\n" +
            "  }\n" +
            "}";
    public static final String TYPE = "newblog";

    public static final String ROLLOVER = "{\n" +
            "  \"conditions\": {\n" +
            "    \"max_age\":   \"1d\",\n" +
            "    \"max_docs\":  1000\n" +
            "  }\n" +
            "}";

}
