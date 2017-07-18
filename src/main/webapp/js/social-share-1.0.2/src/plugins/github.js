(function(socialShare) {
    socialShare.plugin('github', function(args) {
        return {
            url: '//github.com/' + args.id,
            background: '#b5b5b5',
            icon: 'fa-github'
        };
    });
})(window.socialShare);
