# social-share

Social share widget supporting: wechat, weibo, linkedin, github, google+, rss, twitter, facebook and more.

Live Demo: http://harttle.com/social-share/

Dependencies: [Fontawesome][font]

Download: <https://github.com/harttle/social-share/releases>

## Installation

Import Fontawesome:

```html
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
```

Import Social Share:

```html
<link rel="stylesheet" href="social-share/dist/social-share.min.css">
<script src="social-share/dist/social-share.min.js"></script>
```

## Mininal Usage

```html
<div id="share-area"></div>
```

```javascript
var el = document.getElementById('share-area');
var links = [{
    url: 'http://harttle.com',
    target: '_qrcode'
}, {
    plugin: 'github',
    url: 'http://github.com/harttle'
}, {
    plugin: 'github',
    args: {
        id: 'harttle'
    }
}];
window.socialShare(el, links);
```

## Full Usage

```javascript
var links = [{
    url: 'http://harttle.com',
    target: '_qrcode',
    color: '#fff',
    background: '#b5b5b5',
    icon: 'fa-code-fork',
    plugin: 'github',
    args: {
        id
    }
}];
var options = {
    size: 'md'
};
window.socialShare(el, links, options);
```

## Options

### links.url

Type: `String`

Default: `location.href`

The url of this icon. Typically, `links.url` will be set to the `href` attribute
of the corresponding anchor.

### links.target

Type: `String`

Default: `""`

This will be set to the `target` attribute of the anchor.
Available targets: `"_self"`, `"_parent"`, `"_blank"`, `"_top"`, `"_qrcode"`

If set to `_qrcode`, the `links.url` will be opened as a qrcode image within a modal.
In the meanwhile, the `links.title` will be set to the QRcode modal title (default: `'Share Link'`). 

### links.icon

Type: `String`

Default: `'fa-code-fork'`

The Fontawesome icon class for the share button.

### links.color

Type: `String`

Defalt: `'#fff'`

The color of the Fontawesome icon.

### links.background

Type: `String`

Default: `'#b5b5b5'`

The background of the Fontawesome icon.

### links.plugin

Type: `String`

Default: `undefined`

The plugin to use. Typically, a plugin is used to generage
the above settings, according to the arguments set by `links.args`.

Note: Settings within `links` will override the settings returned by a plugin.
For example, `github` plugin responds with the url `//foo`, 
while `links.url` is set to `//bar`. The result url will be `//bar`.

### links.args

Type: `Object`

Default: `{}`

The arguments passed to the plugin, which is specified by `links.plugin`.

### options.size

Type: `String`

Default: `"md"`

Size of the buttons, available values: 

* `"lg"`(large)
* `"md"`(medium)
* `"sm"`(small)
* `"xs"`(exteme small)

## Plugin List

### Weibo（微博）

All args will be append to URL query string.

```javascript
var link = {
    plugin: 'weibo',
    args: {
        appid: '<your App ID>',         // Default: ''
        title: 'About Harttle',         // Default: document.title
        url: '//harttle.com/about.html' // Default: location.href
        source: 'http://harttle.com'    // Any other query string you need...
    }
};
```

> `appid`是微博认证的App ID，便于微博跟踪。`title`和`url`用于微博分享内容和参考链接。

### Wechat（微信）

```javascript
var link = {
    plugin: 'wechat'
};
```

`wechat` plugin accept no arguments, while you can still set `links` properties:

```javascript
var link = {
    plugin: 'wechat',
    url: '//yet.another.url',
    color: 'yellow'
};
```

### QR Code（二维码）

```javascript
var link = {
    plugin: 'qrcode'
};
```

Just like `wechat` plugin, with different background and icon.

### RSS

```javascript
var link = {
    plugin: 'rss'
};
```

### Github

```javascript
var link = {
    plugin: 'github',
    args: {
        id: 'harttle'           // Your Github ID
    }
};
```

### Linkedin

```javascript
var link = {
    plugin: 'linkedin',
    args: {
        id: 'harttle'           // Your linkedin ID
    }
};
```

### Google Plus

```javascript
var link = {
    plugin: 'google-plus',
    args: {
        id: 'harttle'           // Your Google+ ID
    }
};
```

### Twitter

```javascript
var link = {
    plugin: 'twitter',
    args: {
        id: 'harttleharttle'    // Your twitter ID
    }
};
```

### Facebook

```javascript
var link = {
    plugin: 'facebook',
    args: {
        id: 'harttle'           // Your facebook ID
    }
}
```

## How to Write Plugins

Plugins are used to generate a `link` Object according to the `links.args`.
For example, the `github` plugin:

```javascript
(function(socialShare) {
    socialShare.plugin('github', function(args) {
        return {
            url: 'https://github.com/' + args.id,
            background: '#b5b5b5',
            icon: 'fa-github'
        };
    });
})(window.socialShare);
```

To use this plugin, simply set `plugin` to `"github"`, and specify the args:

```js
var links = [{
    plugin: 'github',
    args: {
        id: 'harttle'
    }
}];
```

Which is equavalent to:

```js
var links = [{
    url: 'https://github.com/harttle',
    background: '#b5b5b5',
    icon: 'fa-github'
}];
```

## Contribution Guideline

It's wellcome to make contributions by any means.
While we suggest the following guide lines:

1. Fork this repo.
2. Add your plugin within `src/plugins/`.
3. Run `grunt` to build the `dist/` files.
4. Test your plugin in `demo/index.js`
4. Commit and make a pull request.

[font]: http://fontawesome.io/
[jq]: http://jquery.com/
