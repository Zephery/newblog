# Pinterestlike Website(jQuery) #
This is a full set of Front-end Solutions for Pinterestlike websites.


version: 1.1.1

## Features:
1. Multi-waterfalls are supported in one page.
1. First subpage can be preseted in html without length-limit.
1. Seperated js file for dealing with response data.
1. Different from infinite waterfall, contents are paged.
1. Page turning without refreshing. History go back and go forward are supported.
1. Preload next page when you reach the bottom.
1. In sink mode, you can insert one block into waterfall either on the left side or the right side.
1. Special column can be set.
1. Useful next(pre) button composed with go-to-top button.
1. Go-to-top button can bring you to a precise position you want to go.
1. Dazzling resize mode.
1. Flexible configs such as: subpagenum in one Upper Page, unitsnum in one subpage etc
1. if {"exrecycle" : true} is set, invisible units would be recycled during scrolling.
1. Support Zeptojs.

## Requirements:
* jQuery >= 1.6.1

## Included examples
### Example Sink Mode:
This example shows how to insert an outside block into waterfall either on the left side or on the right side. <br/><a href="http://woo-53422.onmodulus.net/examples/sinkleft.html" target="_blank">Click me to have a look!</a>

### Example Special-Column Mode:
This example shows how to set a special column(different column width) in your waterfall.<br/><a href="http://woo-53422.onmodulus.net/examples/specialcol.html" target="_blank">Click me to have a look!</a>

### Example Messup Mode:
This example shows a Messup Mode of waterfall in which column covers each other.<br/><a href="http://woo-53422.onmodulus.net/examples/messup.html" target="_blank">Click me to have a look!</a>

### Example Auto-Recycle Mode:
This example shows how to auto-recycle invisible units during scrolling.<br/><a href="http://woo-53422.onmodulus.net/examples/recyclefinal.html" target="_blank">Click me to have a look!</a>

### Example simplest demo:
This example shows how to build a simple waterfall page without any other data request.<br/><a href="http://woo-53422.onmodulus.net/examples/norequest.html" target="_blank">Click me to have a look!</a>

### Example mobile demo:
This example shows how to build a waterfall page which is running in mobile phone.<br/><a href="http://woo-53422.onmodulus.net/examples/mobile.html" target="_blank">Click me to have a look!</a>

### Example order:
This example shows how to deal with unit datas with order operation.<br/><a href="http://woo-53422.onmodulus.net/example-operate/order.html" target="_blank">Click me to have a look!</a>

### Example delete:
This example shows how to deal with unit datas with delete operation.<br/><a href="http://woo-53422.onmodulus.net/example-operate/delete.html" target="_blank">Click me to have a look!</a>

### Example zeptojs:
This example shows how zeptojs works.<br/><a href="http://woo-53422.onmodulus.net/examples/zeptomain.html" target="_blank">Click me to have a look!</a>

## Response data structure recommended:
{"data":{"blogs":[${unit},...,${unit}],"has_next":true,"totalcount":202},"success":true}

data.blogs must be an array which contains waterfall units. The unit length in one subpage must be concordant with the value of param `unitsnum`. data.has_next is necessary, which is needed to judge the existance of next subpage, while data.totalcount is optional, which will update the total unit count and total page count. success refers to data request status, a tip "busy, retry~" will be shown while the value is false.




# 堆糖瀑布流(jQuery) #

## 涵盖以下特点：

1. 数据配置灵活，可在 html 里直接放置瀑布流单元(一般只放第一子页)。
1. 数据格式灵活，可处理 josn 格式的数据(推荐)，也可兼容 html 字符串格式的数据。
1. 独立的数据控制文件 masnunit.js。
1. 同一页面上通过 tab 切换植入多个不同类型的瀑布流，切换无刷新。
1. 不同于 infinite 瀑布流，可进行翻页控制。子页数量设置无限大时等同于 infinite。
1. 实现 hash 无刷新翻页，并兼容浏览器的前进后退功能。
1. 至页底时，会预加载下一页第一子页内容。
1. 根据当前环境自动控制瀑布流列数，可自适应屏幕宽度，也可通过
`data-domwidth` 设置固定宽度。
1. 可采用 sink 模式，在瀑布流左侧或右侧第一列嵌入外部区块。
1. 可配置直接向前(后)翻页的小翻页器。
1. 自带回到顶部功能，并能控制距离顶部的准确位置，
准确说是回到预埋锚点的位置(可设置偏移量)。
1. 强大的 window resize 自动重绘功能( IE 下不建议打开此功能)。
1. 可通过配置参数激活特殊列(只能是最左或最右列)，此列宽度可不同于其它列。
1. 灵活的参数设置，比如：子页数量、子页内单元数量、瀑布流单元宽度和间距 等等等等。
1. 可轻松通过 "exrecycle" : true 参数开启超出屏幕范围的单元块回收功能，极大的减少了dom节点数。
1. 支持zeptojs

* <a href="http://woo-53422.onmodulus.net/examples/main.html" target="_blank">纯静态demo演示——主功能</a>
* <a href="http://woo-53422.onmodulus.net/examples/sinkleft.html" target="_blank">纯静态demo演示——sink开启</a>
* <a href="http://woo-53422.onmodulus.net/examples/specialcol.html" target="_blank">纯静态demo演示——specialcol开启</a>
* <a href="http://woo-53422.onmodulus.net/examples/messup.html" target="_blank">纯静态demo演示——Messup 混乱模式</a>
* <a href="http://woo-53422.onmodulus.net/examples/recyclefinal.html" target="_blank">纯静态demo演示——自动回收模式</a>
* <a href="http://woo-53422.onmodulus.net/examples/mobile.html" target="_blank">纯静态demo演示——mobile模拟</a>
* <a href="http://woo-53422.onmodulus.net/examples/norequest.html" target="_blank">纯静态demo演示——简版无请求</a>
* <a href="http://woo-53422.onmodulus.net/example-operate/order.html" target="_blank">纯静态demo演示——单元排序</a>
* <a href="http://woo-53422.onmodulus.net/example-operate/delete.html" target="_blank">纯静态demo演示——单元删除</a>
* <a href="http://woo-53422.onmodulus.net/examples/zeptomain.html" target="_blank">纯静态demo演示——zeptojs</a>


## 一些保留命名 >>

```
co(n)              单元块所在列数对应的 className
sc(n)              单元块所在屏数对应的 className
woo-(*)            Woo 内部特殊用途的 className or id
woo-form-(*)       form 表单的 id，用作请求地址 url 的拼装
srcd               单元块内的图片 src 属性的替代，图片延迟加载功能使用
```


## 一些依赖 >>

* 依赖 browser.js 用于兼容1.10 及以上jQuery 版本的 `$.browser` 对象
* 依赖 history.js 监控 `hashchange` 事件
* 依赖(非必需) template.min.js 组装 html 字符串，详见 masnunit.js
* 依赖 tabswitch.js 用于不同瀑布流的切换


## 一些使用方法 >>

1. 在 main.html 代码底部每一个 `<form >` 对应一个瀑布流的数据请求地址。
1. 如页面上只需要一个瀑布流，请相应的删除掉多余的 form 表单和 `.woo-swa`
`.woo-swb` 节点。
1. 如不给定 `.woo-masned` 节点的宽度，会自动适应屏幕宽度。
专辑类型示例中给定了 1000px 的宽度。
1. `.woo-pcont` 节点内可预先放好若干个单元(个数没有限制)，
预先放置的会被当做第一子页数据，后面会直接从第二子页开始。
1. 一个页面可以有多个瀑布流，不同瀑布流的配置可以差异化。
通过在各自 `.woo-pcont` 节点上设置 `data-` 属性，例如：`data-subpagenum`
`data-unitsnum` `data-sink` 来覆盖全局配置。
1. `.woo-pcont` 节点上目前支持的 `data-` 配置有：`data-domwidth`
`data-subpagenum` `data-unitsnum` `data-totalunits` `data-wootemp`
`data-sink` `data-sinkheight` `data-sinkright`。
1. 是否有下一大页的设定最优先依赖于请求返回里的hasnext字段，如果hasnext=true，即便总单元已经超过`data-totalunits`的设定，仍然会显示下一页按钮。
1. `data-wootemp` 取值为从0开始的整数，对应 masnunit.js 文件里定义的不同类型瀑布流。
1. 示例使用了 DEBUG 并分别在 main.html 和 masnunit.js 里给定了模拟数据。
1. 可在 masnunit.js 里编写不同种类的瀑布流，示例给出了两种：图片类型和专辑类型。
1. 请仔细研究 masnunit.js。

## 请求返回数据格式推荐:
{"data":{"blogs":[${unit},...,${unit}],"has_next":true,"totalcount":202},"success":true}

data.blogs 必须是数组，由单元数据组成的数组。一个子页请求返回的单元个数，必须和配置参数里的`unitsnum` 保持一致，否则会出现页码数计算错误。返回数据 data.has_next 必须有，依赖它判断是否还有下一子页；data.totalcount 可以有(参考示例) ，用于更新当前的总单元数从而更新总页码数；success 表示请求数据成功，如果为 false 则会进入请求出错流程页面上会提示“网络繁忙，点此重试~”。

寻求更多帮助请看<a href="https://github.com/duitang/waterfall/issues/6" target="_blank">使用问题答疑贴</a>

## 如果对本产品感兴趣，请关注博客：

<a href="http://blog.duitang.com/2013/10/woo/" target="_blank">http://blog.duitang.com/2013/10/woo/</a>

## License

Duitang/Waterfall is published under the terms of the MIT License.

## Change Log

* **1.0.1** 2013-10-14
 * 新增 demo for mobile
 * 新增配置参数 arrgap

* **1.0.2** 2013-10-16
 * 新增 demo norequest.html 无请求依赖的最简版
 * bug fix. 修复浏览器不能正常后退的问题
 * 是否有下一大页最优先依赖请求返回里的hasnext字段

* **1.0.3** 2013-10-29
 * Waterfall switch optimized by recording the page number you have read.
 * Column counts caculation optimized by considering cols margin.
 * new added config param `ajaxdatatype` for ajax repsonse.
 * new demo `fixedw.html` showing how to build waterfall with fixed width.
 * 新增 demo example-operate/delete.html
 * 新增 demo example-operate/order.html 
 * 新增事件监听 onOnePageOver requestAlways

* **1.0.3** 2013-11-4
 * bug fixed, img delay load in units preset via html.
 * bug fixed, sink-right when units total num is smaller than cols num.

* **1.0.3** 2013-11-19
 * Add a config `refreshwhenswitch` to decide whether to refresh to page one or to keep the latest pagenum when switching happens.
 * Set a default height value to node `.woo-pcont` when switching happens. If u don't do this, the scrollbar position will move to top due to the height collapse.


* **1.0.3** 2014-1-7
 * Add Messup Mode in which column covers each other.
 * Clean a case of global variable pollution.



* **1.1.0** 2014-4-14
 * Add Auto-Recycle Mode in which invisible units are recycled during scrolling.
 * Add 3 demos for Auto-Recycle Mode.

* **1.1.1** 2014-4-22
 * Add Support for Zeptojs.