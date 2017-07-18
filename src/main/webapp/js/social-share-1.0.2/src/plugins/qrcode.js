(function(socialShare) {
    socialShare.plugin('qrcode', function(args) {
        return {
            target: '_qrcode',
            icon: 'fa-qrcode',
            title: 'Here is the Link!',
            background: '#555'
        };
    });
})(window.socialShare);
