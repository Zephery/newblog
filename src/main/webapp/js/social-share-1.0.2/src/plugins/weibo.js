(function(socialShare) {
    socialShare.plugin('weibo', function(args) {
        args.title = args.title || document.title;
        args.url = args.url || location.href;
        return {
            url: '//v.t.sina.com.cn/share/share.php?' + param(args),
            background: '#E6162D',
            icon: 'fa-weibo'
        };
    });

    function param(obj) {
        var ret = '';
        for (var k in obj) {
            if (obj.hasOwnProperty(k)) {
                if (ret.length) {
                    ret += '&';
                }
                ret += k + '=' + encodeURIComponent(obj[k]);
            }
        }
        return ret;
    }
})(window.socialShare);
