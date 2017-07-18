(function(global) {
    var plugins = {};

    function socialShare(el, links, config) {
        config = assign({
            size: 'md',
            links: {}
        }, config);

        var ul = document.createElement('ul');
        ul.className = "list-unstyled social-share social-share-" + config.size;
        links.forEach(function(link) {
            ul.appendChild(render(link));
        });
        el.appendChild(ul);
    }

    socialShare.plugin = function(name, cb) {
        plugins[name] = cb;
    };

    function callPlugin(name, args) {
        var plugin = plugins[name];
        if (!plugin) {
            console.warn('[socialShare] plugin not found:' + name);
            return null;
        }
        return plugin(args);
    }

    function assign(dst, src) {
        for (var k in src) {
            if (src.hasOwnProperty(k)) {
                dst[k] = src[k];
            }
        }
        return dst;
    }

    function normalizeLinkConfig(config) {
        var defaultConfig = {
            url: location.href,
            target: '',
            icon: 'fa-share-alt',
            color: '#fff',
            background: '#b5b5b5'
        };
        if (config.plugin) {
            assign(defaultConfig, callPlugin(config.plugin, config.args || {}));
        }
        return assign(defaultConfig, config);
    }

    function render(config) {
        config = normalizeLinkConfig(config);

        var li = document.createElement('li');
        li.className = "social-share-item";
        var a = document.createElement('a');
        a.href = config.url;
        a.target = config.target;
        a.style.background = config.background;
        var i = document.createElement('i');
        i.className = 'fa ' + config.icon;
        i.style.color = config.color;

        a.appendChild(i);
        li.appendChild(a);

        if (config.target === '_qrcode') {
            a.removeAttribute('target');
            li.addEventListener('click', function(e) {
                e.preventDefault();
                qrCodeHandler(config.url, config.title || 'Share Link');
            });
        }

        return li;
    }

    function qrCodeHandler(url, title) {
        var bg = document.createElement('div');
        bg.className = "social-share-dialog-bg";

        var modal = document.createElement('div');
        modal.className = "social-share-dialog";

        var header = document.createElement('div');
        header.className = "dialog-header";
        modal.appendChild(header);

        var dismissBtn = document.createElement('span');
        dismissBtn.className = "dismiss";
        dismissBtn.innerHTML = '&times';
        header.appendChild(dismissBtn);

        var titleEl = document.createElement('header');
        titleEl.innerHTML = title;
        header.appendChild(titleEl);

        var body = document.createElement('div');
        body.className = "dialog-body";
        modal.appendChild(body);

        var qrcode = new QRCode(body, {
            text: url,
            width: 256,
            height: 256,
            colorDark: "#000000",
            colorLight: "#ffffff",
            correctLevel: QRCode.CorrectLevel.H
        });

        dismissBtn.addEventListener('click', dismiss);
        modal.addEventListener('click', stopPropagation)
        bg.addEventListener('click', dismiss);
        window.addEventListener('keydown', onKeyDown);

        bg.appendChild(modal);
        document.body.appendChild(bg);
        setTimeout(function() {
            bg.className += " show";
        });

        function stopPropagation(event){
            event.stopPropagation();
        }
        function onKeyDown(event){
            if (event.keyCode == 27) dismiss();
        }
        function dismiss(e) {
            document.body.removeChild(bg);
            window.removeEventListener('keydown', onKeyDown);
        }
    }

    global.socialShare = socialShare;

})(window);
