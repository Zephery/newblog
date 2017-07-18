(function(socialShare) {
    socialShare.plugin('facebook', function(args) {
        return {
            url: '//www.facebook.com/' + args.id,
            icon: 'fa-facebook',
            background: '#3B5998'
        };
    });
})(window.socialShare);
