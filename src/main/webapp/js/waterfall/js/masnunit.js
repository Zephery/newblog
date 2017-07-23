/*
@说明：瀑布流 Woo unit 单元在此组装，已经尽量简化过，搞定此文件才能真正将Woo 纳为己有。
@准备事项：
我们使用了artTemplate 生成html 字符串，在此感谢腾讯CDC 为前端事业做出的重大贡献。
编辑此文件之前请先学习artTemplate 的使用方法，具体地址如下：
https://github.com/aui/artTemplate
*/


//####################################################################
// 你可能会用到的 字符串截取功能 "abc".cut(1)
/**
 * 字符串左起字节数
 * @return {String}   返回字符串左起字节数
 */
if (!String.prototype.lenB) {
    String.prototype.lenB = function () {
        return this.replace(/[^\x00-\xff]/g, "**").length;
    }
}
/**
 * 截取字符串左起字节数
 * @param {String} o string对象
 * @param {Number} n 截取个数
 * @return {String}   返回左起字符串
 */
if (!String.prototype.leftB) {
    String.prototype.leftB = function (n) {
        var s = this,
            s2 = s.slice(0, n),
            i = s2.replace(/[^\x00-\xff]/g, "**").length;
        if (i <= n) {
            return s2;
        }
        i -= s2.length;
        switch (i) {
            case 0:
                return s2;
            case n:
                return s.slice(0, n >> 1);
            default:
                var k = n - i,
                    s3 = s.slice(k, n),
                    j = s3.replace(/[\x00-\xff]/g, "").length;
                return j ? s.slice(0, k) + s3.leftB(j) : s.slice(0, k);
        }
    }
}

/**
 * 按需截取字符串，如有截取则按需补足，比如'...'
 * @依赖: leftB 方法
 * @param {String} s string对象
 * @param {Number} n 截取个数
 * @param {String} a 如有截取用来补足，默认为'...'
 * @param {Bool}   b 是否按字节截取，true 按字符 false(默认) 按字节
 * @return {String}   返回截取后的字符串
 */
if (!String.prototype.cut) {
    String.prototype.cut = function (n, a, b) {
        var s = this;
        r = b ? s.substr(0, n) : s.leftB(n);
        return r == s ? r : r + (typeof a === 'undefined' ? '…' : a);
    }
}


;(function () {
    if (!$.Woo) {
        return
    }


    var ua = navigator.userAgent.toString().toLowerCase(),
        ipad = !!ua.match(/ipad/ig),
        // 是否使用 srcd 替代<img /> 的src 属性，用于图片延迟加载
        // ipad 不适用，因为出过 bug
        SRCD = !ipad,
        // (function数组) 每次请求成功后，对数据进行分析处理
        ANALYZERESPONSE = [],
        // (function数组) 使用artTemplate 拼装数据
        RENDER = [],
        // (template数组) 存放不同类型的template
        TEMPLATES = [],
        // (extra数据数组) ANALYZERESPONSE 方法内填充 extra 数据供 RENDER 方法使用
        EXTRADATA = [];

    /*
    ANALYZERESPONSE RENDER TEMPLATES EXTRADATA
    均为数组，同一下标对应一类瀑布流形态(瀑布流单元里的内容或形状不相同)
    不同类别的瀑布流通过在 .woo-pcont 节点上的 data-wootemp="1" 设置
    */


    //####################################################################
    TEMPLATES = [
        '<% for (var i = 0; i < list.length; i ++) { %><% var u = list[i],indx = $unit(u.id,u),olnk = $outlnk(u); %><% if(!indx) continue; %><div class="woo"><div class="j"> \
        <div class="mbpho" style="height:<%=u.iht > 800 ? 800 : u.iht%>px;"><a href="javascript:show(<%=u.id%>)" class="a"><img <%=srcd ? "srcd" : "src"%>="<%=u.isrc%>" height="<%=u.iht%>" /><%= u.iht > 800 ? "<u style=\'margin-top:-"+(u.iht-720)+"px\'></u>" : ""%></a> \
        </div> ' +
        '<% } %>',
        null,
        null
    ],

        //####################################################################
        RENDER = [
            function (arr) {
                if (arr && arr.length && $.isPlainObject(arr[0])) {
                    // 如果传进来的arr 是dic字典组成的数组
                    // 形如：[{},{}]
                    var dat = EXTRADATA[0],
                        data = {
                            list: arr,
                            srcd: SRCD,					// 是否要使用 srcd
                            hasrp: dat.hasrp				// 是否采用新型的回复列表
                        },
                        render = template.compile(TEMPLATES[0]),
                        html = $.trim(render(data));

                    // 这里返回的是 html 字符串，将会被 $() 处理
                    return html
                } else {
                    // 如果是 dom 或 [<jQuery对象>] 数组，直接返回，将会被 $() 处理
                    return arr
                }
            },

            null,
            null
        ],

        //####################################################################
        // 这里给出了两种不同的数据组装方式
        // 第一种使用 artTemplate 将字典对象转化成 html 字符串
        // 第二种直接使用 + 号连接字符串
        // 方法返回值必须是数组 ret = [cont,hasnext,totalcount] 前两个必须有，totalcount 可选
        // ret[0]=cont 可以为字典(对应第一种组装方式)，也可以返回dom树(对应第二种组装方式)
        // data-wootemp is set on the Node <div class="woo-pcont woo-masned my-album" data-wootemp="1" >  对应组装方法 ANALYZERESPONSE[1]
        ANALYZERESPONSE = [
            // ANALYZERESPONSE[0] 使用第一种组装方式，return 的主体内容 ret[0] 是字典
            // 第一种组装方式依赖 RENDER TEMPLATES EXTRADATA
            // 相应的 RENDER[0] TEMPLATES[0] 需要定义
            // EXTRADATA 没有初始值，只在需要的时候使用
            // repsonse data h:
            // {"data":{"blogs":[${unit},...,${unit}],"has_next":true},"success":true}
            // see more here : https://github.com/duitang/waterfall/issues/6
            // ret = [cont,hasnext,totalcount] cont,hasnext are necessary while totalcount is optional
            function (h) {
                var strrt = _strReturn(h);
                if (strrt) {
                    return strrt;
                }

                var ret = [[], true];


                // 转json对象
                try {
                    var jsn = $.isPlainObject(h) ? h : $.parseJSON(h)
                } catch (e) {
                    // 如果parse 失败，直接返回初始状态的 ret;
                    return ret;
                }


                // 判断jsn 请求是否成功返回数据
                if (jsn.success) {
                    var dat = jsn.data,
                        undefined;
                    EXTRADATA[0] = {
                        "hasrp": !!dat.hasrp			// 是否采用新型的回复列表
                    }
                    ret = [
                        dat.blogs,
                        dat.has_next,
                        dat.totalcount
                    ]
                }
                return ret;
            },
            // ANALYZERESPONSE[1] 使用第二种组装方式，return 的主体内容 ret[0] 是dom 树
            // 采用第二种方式的话，不需要使用 artTemplate
            // 因此，RENDER TEMPLATES EXTRADATA (后两者均只为RENDER服务)都不需要
            // RENDER[1] 设为 null，其依赖的 TEMPLATES[1] 也设为 null
            // EXTRADATA 没有初始值，只在需要的时候使用
            // repsonse data h:
            // {"data":{"albums":[${unit},...,${unit}],"has_next":true,"totalcount":8907},"success":true}
            // see more here : https://github.com/duitang/waterfall/issues/6
            // data.totalcount is optional for totalcount returned in ret
            // ret = [cont,hasnext,totalcount] cont,hasnext are necessary while totalcount is optional
            function (h) {
                typeof DEBUG !== 'undefined' && DEBUG && (h = {
                    "data": {
                        "totalcount": 8907,
                        "has_next": true,
                        "albums": [/*测试用*/]
                    }, "success": true
                }, h.data.albums[23] = 0);

                var strrt = _strReturn(h);
                if (strrt) {
                    return strrt;
                }

                var ret = [[], true];

                // 转json对象
                try {
                    var jsn = $.isPlainObject(h) ? h : $.parseJSON(h)
                } catch (e) {
                    // 如果parse 失败，直接返回初始状态的 ret;
                    return ret;
                }


                // 判断jsn 请求是否成功返回数据
                if (jsn.success) {
                    var $rt = $(null),
                        dat = jsn.data;
                    for (var i = 0, d = dat.albums, l = d.length; i < l; i++) {
                        var unt = [
                            '<div class="woo"  data-ht="328"> <div class="albbigimg"> <p class="lev2"></p> <p class="lev1"></p> <a class="lev0" href="http://www.duitang.com/topics/" target="_blank" ><img src="http://cdn.duitang.com/uploads/item/201208/07/20120807235954_URvcE.thumb.200_200_c.jpeg" alt="动漫集 ."></a> <p class="lev3"></p> <div>动漫集 .</div> </div> <ul> <li><span>3736个收集 | 103人喜欢</span></li> <li class="clr"><a href="http://www.duitang.com/topics/" target="_blank" ><img src="http://cdn.duitang.com/uploads/people/201309/15/20130915021315_kyMMu.thumb.24_24_c.jpeg" />笑我作茧自缚还…</a></li> <li>有壁纸.</li> </ul> </div>'
                        ].join('')

                        $rt = $rt.add($(unt))
                    }
                    ret = [$rt.toArray(), dat.has_next]
                }
                return ret;
            },
            // ANALYZERESPONSE[2] 使用第二种组装方式，return 的主体内容 ret[0] 是dom 树
            // 采用第二种方式的话，不需要使用 artTemplate
            // 因此，RENDER TEMPLATES EXTRADATA (后两者均只为RENDER服务)都不需要
            // RENDER[2] 设为 null，其依赖的 TEMPLATES[2] 也设为 null
            // EXTRADATA 没有初始值，只在需要的时候使用
            function (h) {
                var strrt = _strReturn(h);
                if (strrt) {
                    return strrt;
                }

                var ret = [[], true];

                // 转json对象
                try {
                    var jsn = $.isPlainObject(h) ? h : $.parseJSON(h)
                } catch (e) {
                    // 如果parse 失败，直接返回初始状态的 ret;
                    return ret;
                }


                // 判断jsn 请求是否成功返回数据
                if (jsn.success) {
                    var $rt = $(null),
                        dat = jsn.data,
                        picw = 96;
                    for (var i = 0, d = dat.blogs, l = d.length; i < l; i++) {
                        var pich = Math.round(d[i].iht * picw / 200),  //pic size
                            mask = d[i].it > 800 ? '<div class="mask"></div>' : '',
                            ht = mask ? 384 : pich;
                        unt = [
                            '<div class="woo" data-ht="' + ht + '"><div class="j" style="height:auto;"><a href="http://m.duitang.com/people/mblog/' + d[i].id + '/detail/"><img srcd="' + d[i].isrc + '" width="96" /></a>' + mask + '</div></div>'
                        ].join('');

                        $rt = $rt.add($(unt))
                    }
                    ret = [$rt.toArray(), dat.has_next]
                }
                return ret;
            }
        ];


    // 内部调用，请求返回数据是 html字符串的情况下统一处理
    function _strReturn(h) {
        var rt = $.trim(h),
            fw = rt.substr(0, 1);

        if (fw != '{' && fw != '[') {
            if (rt.substr(0, 9) === '<!doctype') {
                return [[], true];
            }

            if (SRCD) {
                rt = rt.replace(/(<img[^>]* class=[\'\"]?i[\'\"]?[^>\"\']*)src/ig, function (a, b) {
                    return b + 'srcd'
                })
            }

            var $rt = $(rt).filter('.woo'),
                hasnext = $rt.attr('hasnext') === 'False' ? false : true;
            return [$rt.toArray(), hasnext];
        } else {
            // 如果不是html 返回null
            return null
        }
    }


    template.helper('$price', function (u, olnk) {
        return u.buylnk ? '<a class="dymprice bl dib ' + (!u.price ? 'by' : '') + '" href="' + olnk + '" target="_blank"><u class="_tb" title="去购买">' + (u.price ? '￥' + u.price : '&nbsp;') + '</u></a>' : ''
    });

    template.helper('$outlnk', function (u) {
        var olnk = u.buylnk || '';

        // 这里可以进行更多的链接组装工作
        return olnk
    });

    template.helper('$cut', function (s, num) {
        return s.cut(num, '…')
    });

    template.helper('$unit', function (id, jsn) {
        id += '';
        // here shows how to avoid repeated unit being added
        // the third param indicates that duplication-avoid is open
        return WT.addUnit(id, jsn, false);
    });


    /*
  @说明：$.Woo.WooTemp 类
  */
    var WT = (function () {
        var WT = {
            ulen: 0,
            latestUnits: {},
            init: function (a, b) {
                WT.analyzeResponse = a,
                    WT.render = b,

                    // 当前可见瀑布流的数据集合
                    WT.masnUnits = {};
            },
            reset: function () {
                WT.ulen = 0,
                    WT.masnUnits = {};
            },
            getLatestUnits: function () {
                return WT.latestUnits;
            },
            resetLatestUnits: function () {
                WT.latestUnits = {};
            },
            setUnitsFromLatest: function () {
                var jsnunits = WT.latestUnits;
                if ($.isPlainObject(jsnunits)) {
                    WT.masnUnits = jsnunits;
                }
            },
            addUnit: function (id, jsn, avoidduplicate) {
                var munits = WT.masnUnits;
                // munits 去重工作
                if (!avoidduplicate || !munits[id]) {
                    WT.latestUnits[id] = jsn,
                        munits[id] = jsn,
                        WT.ulen++,
                        munits[id].indx = WT.ulen - 1;

                    return WT.ulen;
                } else {
                    // 如果有重复，返回0，则不做添加动作
                    return 0
                }
            }
        }
        return WT;
    })()

    WT.init(ANALYZERESPONSE, RENDER);


    $.Woo.WooTemp = WT;

})()
