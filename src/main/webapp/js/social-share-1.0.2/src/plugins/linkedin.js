(function(socialShare) {
    socialShare.plugin('linkedin', function(args) {
        return {
            url: '//www.linkedin.com/in/' + args.id,
            background: '#4875B4',
            icon: 'fa-linkedin'
        };
    });
})(window.socialShare);
