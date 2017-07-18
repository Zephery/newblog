(function(socialShare) {
    socialShare.plugin('google-plus', function(args) {
        return {
            url: '//plus.google.com/+' + args.id,
            icon: 'fa-google-plus',
            background: '#C63D2D'
        };
    });
})(window.socialShare);
