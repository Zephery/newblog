(function(socialShare) {
    socialShare.plugin('twitter', function(args) {
        return {
            url: '//twitter.com/' + args.id,
            icon: 'fa-twitter',
            background: '#33CCFF'
        };
    });
})(window.socialShare);
