var links = [{
        target: '_qrcode',
        url: 'http://harttle.com',
        color: '#fff',
        background: '#b5b5b5',
        icon: 'fa-code-fork'
    }, {
        target: '_blank',
        icon: 'fa-cloud'
    },{
        plugin: 'facebook',
        args: {
            id: 'harttle'
        }
    },{
        plugin: 'facebook',
        url: 'http://xxx.com'
    }, {
        plugin: 'google-plus',
        args: {
            id: '杨珺'
        }
    }, {
        plugin: 'weibo', 
        args: {
            appid: 'xxx',
            source: 'http://harttle.com'
        }
    }, {
        plugin: 'wechat'
    }, {
        plugin: 'qrcode',
        url: 'http://harttle.com'
    }, {
        plugin: 'linkedin',
        args: {
            id: 'harttle'
        }
    }, {
        plugin: 'rss',
        url: 'http://harttle.com/feed.xml'
    }, {
        plugin: 'github',
        args: {
            id: 'harttle'
        }
    }, {
        plugin: 'twitter',
        args: {
            id: 'harttleharttle'
        }
    }];

window.socialShare(document.getElementById('xs'), links, { size: 'xs' });
window.socialShare(document.getElementById('sm'), links, { size: 'sm' });
window.socialShare(document.getElementById('md'), links);
window.socialShare(document.getElementById('lg'), links, { size: 'lg' });
