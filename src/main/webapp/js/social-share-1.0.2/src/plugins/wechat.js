(function(socialShare) {
    socialShare.plugin('wechat', function(args) {
        return {
            target: '_qrcode',
            icon: 'fa-wechat',
            background: '#77cc6d'
        };
    });
})(window.socialShare);
