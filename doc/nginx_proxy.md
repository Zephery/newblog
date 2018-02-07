

## 方法一
```java
    @RequestMapping("/cacheIndex")
    @ResponseBody
    @SuppressWarnings("unchecked")
    public String cacheIndex() {
        String content = HttpHelper.getInstance().get("http://www.wenzhihuai.com");
        redisTemplate.opsForValue().set("index", content);
        return "success";
    }

    @RequestMapping("/readIndex")
    public void readIndex(HttpServletResponse response) throws Exception {
        String content = redisTemplate.opsForValue().get("index").toString();
        response.getWriter().write(content);
    }
```

## 方法二
