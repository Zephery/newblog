/*
 *  Project: Chroma Gallery
 *  Description: A Gallery that shows the colors of the pictures
 *  Author: codecrafting.net
 *  License: Licensed under the BSD License: http://www.opensource.org/licenses/bsd-license.php
 */

;(function ($,window,document,undefined) 
{

    /*!
     * Color Thief v2.0
     * by Lokesh Dhakar - http://www.lokeshdhakar.com
     *
     * Thanks
     * ------
     * Nick Rabinowitz - For creating quantize.js.
     * John Schulz - For clean up and optimization. @JFSIII
     * Nathan Spady - For adding drag and drop support to the demo page.
     *
     * License
     * -------
     * Copyright 2011, 2015 Lokesh Dhakar
     * Released under the MIT license
     * https://raw.githubusercontent.com/lokesh/color-thief/master/LICENSE
     *
     */
    var CanvasImage=function(a){this.canvas=document.createElement("canvas"),this.context=this.canvas.getContext("2d"),document.body.appendChild(this.canvas),this.width=this.canvas.width=a.width,this.height=this.canvas.height=a.height,this.context.drawImage(a,0,0,this.width,this.height)};CanvasImage.prototype.clear=function(){this.context.clearRect(0,0,this.width,this.height)},CanvasImage.prototype.update=function(a){this.context.putImageData(a,0,0)},CanvasImage.prototype.getPixelCount=function(){return this.width*this.height},CanvasImage.prototype.getImageData=function(){return this.context.getImageData(0,0,this.width,this.height)},CanvasImage.prototype.removeCanvas=function(){this.canvas.parentNode.removeChild(this.canvas)};var ColorThief=function(){};/*!
     * quantize.js Copyright 2008 Nick Rabinowitz.
     * Licensed under the MIT license: http://www.opensource.org/licenses/mit-license.php
     */
    /*!
     * Block below copied from Protovis: http://mbostock.github.com/protovis/
     * Copyright 2010 Stanford Visualization Group
     * Licensed under the BSD License: http://www.opensource.org/licenses/bsd-license.php
     */
    if(ColorThief.prototype.getColor=function(a,b){var c=this.getPalette(a,5,b),d=c[0];return d},ColorThief.prototype.getPalette=function(a,b,c){"undefined"==typeof b&&(b=10),("undefined"==typeof c||1>c)&&(c=10);for(var d,e,f,g,h,i=new CanvasImage(a),j=i.getImageData(),k=j.data,l=i.getPixelCount(),m=[],n=0;l>n;n+=c)d=4*n,e=k[d+0],f=k[d+1],g=k[d+2],h=k[d+3],h>=125&&(e>250&&f>250&&g>250||m.push([e,f,g]));var o=MMCQ.quantize(m,b),p=o?o.palette():null;return i.removeCanvas(),p},!pv)var pv={map:function(a,b){var c={};return b?a.map(function(a,d){return c.index=d,b.call(c,a)}):a.slice()},naturalOrder:function(a,b){return b>a?-1:a>b?1:0},sum:function(a,b){var c={};return a.reduce(b?function(a,d,e){return c.index=e,a+b.call(c,d)}:function(a,b){return a+b},0)},max:function(a,b){return Math.max.apply(null,b?pv.map(a,b):a)}};var MMCQ=function(){function a(a,b,c){return(a<<2*i)+(b<<i)+c}function b(a){function b(){c.sort(a),d=!0}var c=[],d=!1;return{push:function(a){c.push(a),d=!1},peek:function(a){return d||b(),void 0===a&&(a=c.length-1),c[a]},pop:function(){return d||b(),c.pop()},size:function(){return c.length},map:function(a){return c.map(a)},debug:function(){return d||b(),c}}}function c(a,b,c,d,e,f,g){var h=this;h.r1=a,h.r2=b,h.g1=c,h.g2=d,h.b1=e,h.b2=f,h.histo=g}function d(){this.vboxes=new b(function(a,b){return pv.naturalOrder(a.vbox.count()*a.vbox.volume(),b.vbox.count()*b.vbox.volume())})}function e(b){var c,d,e,f,g=1<<3*i,h=new Array(g);return b.forEach(function(b){d=b[0]>>j,e=b[1]>>j,f=b[2]>>j,c=a(d,e,f),h[c]=(h[c]||0)+1}),h}function f(a,b){var d,e,f,g=1e6,h=0,i=1e6,k=0,l=1e6,m=0;return a.forEach(function(a){d=a[0]>>j,e=a[1]>>j,f=a[2]>>j,g>d?g=d:d>h&&(h=d),i>e?i=e:e>k&&(k=e),l>f?l=f:f>m&&(m=f)}),new c(g,h,i,k,l,m,b)}function g(b,c){function d(a){var b,d,e,f,g,h=a+"1",j=a+"2",k=0;for(i=c[h];i<=c[j];i++)if(o[i]>n/2){for(e=c.copy(),f=c.copy(),b=i-c[h],d=c[j]-i,g=d>=b?Math.min(c[j]-1,~~(i+d/2)):Math.max(c[h],~~(i-1-b/2));!o[g];)g++;for(k=p[g];!k&&o[g-1];)k=p[--g];return e[j]=g,f[h]=e[j]+1,[e,f]}}if(c.count()){var e=c.r2-c.r1+1,f=c.g2-c.g1+1,g=c.b2-c.b1+1,h=pv.max([e,f,g]);if(1==c.count())return[c.copy()];var i,j,k,l,m,n=0,o=[],p=[];if(h==e)for(i=c.r1;i<=c.r2;i++){for(l=0,j=c.g1;j<=c.g2;j++)for(k=c.b1;k<=c.b2;k++)m=a(i,j,k),l+=b[m]||0;n+=l,o[i]=n}else if(h==f)for(i=c.g1;i<=c.g2;i++){for(l=0,j=c.r1;j<=c.r2;j++)for(k=c.b1;k<=c.b2;k++)m=a(j,i,k),l+=b[m]||0;n+=l,o[i]=n}else for(i=c.b1;i<=c.b2;i++){for(l=0,j=c.r1;j<=c.r2;j++)for(k=c.g1;k<=c.g2;k++)m=a(j,k,i),l+=b[m]||0;n+=l,o[i]=n}return o.forEach(function(a,b){p[b]=n-a}),d(h==e?"r":h==f?"g":"b")}}function h(a,c){function h(a,b){for(var c,d=1,e=0;k>e;)if(c=a.pop(),c.count()){var f=g(i,c),h=f[0],j=f[1];if(!h)return;if(a.push(h),j&&(a.push(j),d++),d>=b)return;if(e++>k)return}else a.push(c),e++}if(!a.length||2>c||c>256)return!1;var i=e(a),j=0;i.forEach(function(){j++});var m=f(a,i),n=new b(function(a,b){return pv.naturalOrder(a.count(),b.count())});n.push(m),h(n,l*c);for(var o=new b(function(a,b){return pv.naturalOrder(a.count()*a.volume(),b.count()*b.volume())});n.size();)o.push(n.pop());h(o,c-o.size());for(var p=new d;o.size();)p.push(o.pop());return p}var i=5,j=8-i,k=1e3,l=.75;return c.prototype={volume:function(a){var b=this;return(!b._volume||a)&&(b._volume=(b.r2-b.r1+1)*(b.g2-b.g1+1)*(b.b2-b.b1+1)),b._volume},count:function(b){var c=this,d=c.histo;if(!c._count_set||b){var e,f,g,h=0;for(e=c.r1;e<=c.r2;e++)for(f=c.g1;f<=c.g2;f++)for(g=c.b1;g<=c.b2;g++)index=a(e,f,g),h+=d[index]||0;c._count=h,c._count_set=!0}return c._count},copy:function(){var a=this;return new c(a.r1,a.r2,a.g1,a.g2,a.b1,a.b2,a.histo)},avg:function(b){var c=this,d=c.histo;if(!c._avg||b){var e,f,g,h,j,k=0,l=1<<8-i,m=0,n=0,o=0;for(f=c.r1;f<=c.r2;f++)for(g=c.g1;g<=c.g2;g++)for(h=c.b1;h<=c.b2;h++)j=a(f,g,h),e=d[j]||0,k+=e,m+=e*(f+.5)*l,n+=e*(g+.5)*l,o+=e*(h+.5)*l;k?c._avg=[~~(m/k),~~(n/k),~~(o/k)]:c._avg=[~~(l*(c.r1+c.r2+1)/2),~~(l*(c.g1+c.g2+1)/2),~~(l*(c.b1+c.b2+1)/2)]}return c._avg},contains:function(a){var b=this,c=a[0]>>j;return gval=a[1]>>j,bval=a[2]>>j,c>=b.r1&&c<=b.r2&&gval>=b.g1&&gval<=b.g2&&bval>=b.b1&&bval<=b.b2}},d.prototype={push:function(a){this.vboxes.push({vbox:a,color:a.avg()})},palette:function(){return this.vboxes.map(function(a){return a.color})},size:function(){return this.vboxes.size()},map:function(a){for(var b=this.vboxes,c=0;c<b.size();c++)if(b.peek(c).vbox.contains(a))return b.peek(c).color;return this.nearest(a)},nearest:function(a){for(var b,c,d,e=this.vboxes,f=0;f<e.size();f++)c=Math.sqrt(Math.pow(a[0]-e.peek(f).color[0],2)+Math.pow(a[1]-e.peek(f).color[1],2)+Math.pow(a[2]-e.peek(f).color[2],2)),(b>c||void 0===b)&&(b=c,d=e.peek(f).color);return d},forcebw:function(){var a=this.vboxes;a.sort(function(a,b){return pv.naturalOrder(pv.sum(a.color),pv.sum(b.color))});var b=a[0].color;b[0]<5&&b[1]<5&&b[2]<5&&(a[0].color=[0,0,0]);var c=a.length-1,d=a[c].color;d[0]>251&&d[1]>251&&d[2]>251&&(a[c].color=[255,255,255])}},{quantize:h}}();
        
    "use strict";

    // Create the defaults once
    var pluginName = 'chromaGallery',
        $window = $(window),
        $body = $("body"),
        animationTime = 350,
        chromaScreen = null,
        vendorPrefix = getVendorPrefix(),
        baseUrl = window.location.protocol +"//"+ window.location.host + window.location.pathname,
        defaults = 
        {
            color: "chroma",
            maxColumns:4,
            items:null,
            dof:false, //experimental
            screenOpacity:0.98,
            lazyLoad:true,
            gridMargin:7,
            fullscreen:true,
            easing:'easeInOutQuart',
            onLoad:function(){},
            onOpen:function(){},
            onClose:function(){},
            onNext:function(){},
            onPrev:function(){},
            onFullscreen:function(){}
        },
        keys = 
        {
            next:39, //right arrow
            prev:37, //left arrow
            fullscreen:70, //letter f
            close:27 //escape key
        };

    //Get the browser specific vendor prefix
    function getVendorPrefix()
    {
        var result;
        var properties = ["transform", "msTransform", "webkitTransform", "mozTransform", "oTransform"];

        for (var i = 0; i < properties.length; i++) 
        {
            if (typeof document.body.style[properties[i]] != "undefined") 
            {
                result = properties[i];
                break;
            }
        }

        switch (result) 
        {
            case "msTransform":
            {
                return "-ms-"; break;
            }
            case "webkitTransform":
            {
                return "-webkit-"; break;
            }
            case "mozTransform":
            {
                return "-moz-"; break;
            }
            case "oTransform":
            {
                return "-o-"; break;
            }
            default:
            {
                return ""; break;
            }
        }
    }

    //Get rgb color from color name
    function colorToRGB(colorName)
    {  
        var temp = document.createElement("div"),color;
        temp.style.color = colorName;
        document.body.appendChild(temp);
        color = window.getComputedStyle(temp).color;
        document.body.removeChild(temp);
        return color;
    }

    //Get filename from url
    function getUrlFilename(url)
    {
        if(typeof url === 'string')
        {
            //remove query strings
            return url.split(/[?#]/)[0].split("/").pop();
        }
        return undefined;
    }

    //Validate user settings
    function validateSettings(settings)
    {
        for (var prop in settings) 
        {
            if (settings.hasOwnProperty(prop)) 
            {
                var val = settings[prop];
                switch (prop) 
                {
                    case "color":
                    {
                        if(typeof val !== 'string')
                            settings[prop] = defaults[prop];
                        break;
                    }

                    case "maxColumns":
                    {
                        var intVal = parseInt(val,10);
                        if(intVal != val)
                            settings[prop] = defaults[prop];
                        else if(intVal == 0)
                            settings[prop] = defaults[prop];
                        break;
                    }

                    case "items":
                    {
                        if(val !== null && !Array.isArray(val))
                            settings[prop] = [];
                        break;
                    }

                    case "dof":
                    {
                        if(typeof val !== 'boolean' || !Modernizr.cssfilters)
                            settings[prop] = defaults[prop];
                        break;
                    }

                    case "screenOpacity":
                    {
                        if(typeof val !== 'number')
                            settings[prop] = defaults[prop];
                        break;
                    }

                    case "lazyLoad":
                    {
                        if(typeof val !== 'boolean')
                            settings[prop] = defaults[prop];
                        break;
                    }

                    case "gridMargin":
                    {
                        var intVal = parseInt(val,10);
                        if(intVal != val)
                            settings[prop] = defaults[prop];
                        break;
                    }

                    case "fullscreen":
                    {
                        if(typeof val !== 'boolean')
                            settings[prop] = defaults[prop];
                        if(!Modernizr.fullscreen) settings[prop] = false;
                        break;
                    }

                    case "easing":
                    {
                        if(typeof val !== 'string')
                            settings[prop] = defaults[prop];
                        break;
                    }

                    case "onLoad":
                    {
                        if(typeof val !== 'function')
                            settings[prop] = defaults[prop];                        
                        break;
                    }

                    case "onOpen":
                    {
                        if(typeof val !== 'function')
                            settings[prop] = defaults[prop];
                        break;
                    }

                    case "onClose":
                    {
                        if(typeof val !== 'function')
                            settings[prop] = defaults[prop];
                        break;
                    }

                    case "onNext":
                    {
                        if(typeof val !== 'function')
                            settings[prop] = defaults[prop];
                        break;
                    }

                    case "onPrevious":
                    {
                        if(typeof val !== 'function')
                            settings[prop] = defaults[prop];
                        break;
                    }

                    case "onFullscreen":
                    {
                        if(typeof val !== 'function')
                            settings[prop] = defaults[prop];
                        break;
                    }
                }
            }
        }
    }

    // The actual plugin constructor
    function Plugin(el, options) 
    {
        var self = this;
        this.$el = $(el);
        this.isLoaded = false;
        this.grid = null;
        this.settings = $.extend({},defaults,options);
        validateSettings(this.settings);
        if(this.settings.color != "chroma")
            this.settings.color = colorToRGB(this.settings.color);

        if(chromaScreen == null)
        {
            chromaScreen = new ChromaScreen();

            //force the icons to load
            this.$el.append("<span class='chrg-loadicon chrgi-image'></span>");
            setTimeout(function()
            {
                self.$el.find(".chrg-loadicon").remove();
            },100);            
        }         
        this._init();
    }

    $.extend(Plugin.prototype,
    {
        //Private methods

        //Initialize the gallery grid
        _init:function()
        {
            var self = this;
            if(!this.$el.hasClass("chroma-gallery")) this.$el.addClass("chroma-gallery");
            this.$el.show();
            setTimeout(function()
            {
                var aux = 0;
                self._createGrid();
                self._setEvents();
                self.grid.find("img").imagesLoaded()
                .always(function()
                {
                    self.isLoaded = true;
                    self.$el.trigger("chrg.load");
                })
                .progress(function(instance,image)
                {
                    var $img = $(image.img),
                        delay = (self.settings.lazyLoad) ? aux*150:0;
                    aux++;
                    if(image.isLoaded)
                    {
                        var imgDescription = ($img.attr('alt') !== undefined) ? $img.attr('alt').substring(0,144) : "";
                        
                        $img.parent().attr("data-isloaded","true");
                        $img.parent().find("p").append(imgDescription);
                        
                        //Incremental delay
                        setTimeout(function()
                        {
                            $img.parent().css({"opacity":"1"});
                            self.grid.masonry('layout');
                        },delay);
                    }
                    else
                    {
                        $img.parent().find("p").append("<span class='chrgi-image'></span><br>"+getUrlFilename($img.attr("src"))+" failed to load");
                        $img.parent().attr("data-isloaded","false").css({'cursor':'default'});
                        $img.css({'height':'200px',"opacity":"0"});

                        //Incremental delay
                        setTimeout(function()
                        {
                            $img.parent().css({"opacity":"1"});
                            $img.parent().find(".chrg-description").css({"opacity":"1"});
                            self.grid.masonry('layout');
                        },delay);                                               
                    }
                });

            },50);
        },

        //Get the percentage of column width
        _getColumnWidth: function(qtdItems)
        {
            var numColumns,
                galWidth = this.$el.width();

            if(galWidth <= 400) numColumns = 2;
            else if(galWidth/this.settings.maxColumns >= 200)
                numColumns = Math.min(this.settings.maxColumns,qtdItems);
            else numColumns = Math.floor(galWidth/200);

            if(this.settings.gridMargin > 0)
                return 100/numColumns * ((galWidth - (numColumns)*this.settings.gridMargin)/galWidth);
            return 100/numColumns;
        },

        //Create the gallery grid
        _createGrid:function()
        {
            var self = this;
            if(this.settings.items)
            {
                var columnWidth = this._getColumnWidth(this.settings.items.length);
                this.$el.html("<div class='chrg-grid'></div>");
                this.grid = this.$el.find(".chrg-grid");
                for (var i = 0; i < this.settings.items.length; i++) 
                {
                    if(this.settings.items[i].src &&
                        this.settings.items[i].alt &&
                        this.settings.items[i].largesrc)
                    {
                        this.grid.append("<div class='chrg-item chrg-no-select' style='width:"+columnWidth+"%; margin-bottom:"+this.settings.gridMargin+"px;'>"+
                            "<img src='"+this.settings.items[i].src+"' alt='"+this.settings.items[i].alt+"' data-largesrc='"+this.settings.items[i].largesrc+"'/>"+
                            "<div class='chrg-description'><p></p></div></div>");
                    }
                }
            }

            else
            {
                var columnWidth = this._getColumnWidth(this.$el.find("img").length);
                this.$el.append("<div class='chrg-grid'></div>");
                this.grid = this.$el.find(".chrg-grid");
                this.$el.find("img")
                    .wrap("<div class='chrg-item chrg-no-select' style='width:"+columnWidth+"%; margin-bottom:"+this.settings.gridMargin+"px;'></div>")
                    .parent().append("<div class='chrg-description'><p></p></div>");
                this.grid.append(this.$el.find(".chrg-item"));

            }

            this.grid.masonry
            ({
                itemSelector: '.chrg-item',
                gutter:self.settings.gridMargin,
                percentPosition: true
            });
        },

        //Reload the gallery grid
        _reloadGrid:function()
        {
            var columnWidth = this._getColumnWidth(this.grid.find(".chrg-item").length);
            this.grid.find(".chrg-item").css("width",columnWidth+"%");
            this.grid.masonry('layout');
        },

        //Set the gallery events
        _setEvents:function()
        {
            var self = this;

            //Click on grid image
            this.grid.find(".chrg-item").on("click",function()
            {   
                self.openImg($(this).index());
            });

            //On gallery grid load
            this.$el.on("chrg.load",function()
            {
                self.settings.onLoad.call(self.$el);
            });

            //On grid image open
            this.$el.on("chrg.openImg",function()
            {
                self.settings.onOpen.call(self.$el);
            });

            //On grid image close
            this.$el.on("chrg.closeImg",function()
            {
                self.settings.onClose.call(self.$el);
            });

            //On go to next image
            this.$el.on("chrg.next",function()
            {
                self.settings.onNext.call(self.$el);
            });

            //On go to previous image
            this.$el.on("chrg.prev",function()
            {
                self.settings.onPrev.call(self.$el);
            });     

            //On fullscreen
            this.$el.on("chrg.fullscreen",function()
            {
                self.settings.onFullscreen.call(self.$el);
            }); 

            //Resize window
            $window.resize(function()
            {
                if(self.isLoaded) self._reloadGrid();
            });

            //Window Orientation change
            window.addEventListener('orientationchange', function()
            {
                if(self.isLoaded) self._reloadGrid();
            });
        },

        //Public methods

        //Open a image from index
        openImg:function(index)
        {
            if(!chromaScreen.isOpen && this.isLoaded)
            {
                var gridItem = $(this.grid.find(".chrg-item").get(index));
                if(gridItem.length > 0 && gridItem.data("isloaded"))
                {
                    chromaScreen.curGallery = this.$el;
                    chromaScreen.curItem = gridItem;
                    chromaScreen.settings = 
                    {
                        color: this.settings.color,
                        dof:this.settings.dof,
                        screenOpacity:this.settings.screenOpacity,
                        fullscreen:this.settings.fullscreen,
                        easing:this.settings.easing                        
                    };
                    chromaScreen.openImg();
                }
            }
        },

        //Close any opened image
        closeImg:function()
        {
            if(chromaScreen.isOpen) chromaScreen.closeImg();
        },

        //Go to specific image index
        goTo:function(index)
        {
            if(chromaScreen.isOpen && !chromaScreen.lock)
            {
                var gridItem = $(this.grid.find(".chrg-item").get(index));
                if(gridItem.length > 0 && gridItem.data("isloaded"))
                {
                    chromaScreen.goTo(gridItem);
                }
            }
        },

        //Go to next image
        next:function()
        {
            if(chromaScreen.isOpen && !chromaScreen.lock)
                chromaScreen.next();
        },

        //Go to previous image
        prev:function()
        {
            if(chromaScreen.isOpen && !chromaScreen.lock)
                chromaScreen.prev();
        }
    });

    //ChromaScreen constructor
    function ChromaScreen()
    {
        this.colorThief = new ColorThief();
        this.settings = null;
        this.isOpen = false;
        this.isFullscreen = false;
        this.curItem = null;

        //Separate trigger events by gallery
        this.curGallery = null;
        this.init();
    }
    
    //ChromaScreen methods
    $.extend(ChromaScreen.prototype,
    {
        //Initialize ChromaScreen
        init:function()
        {
            $body.append
            (
                "<div class='chroma-screen'><div class='chrg-bg'></div>"+
                    "<div class='chrg-wrap'>"+
                        "<div class='chrg-content'><div class='chrg-imgwrap'></div></div>"+
                        "<div class='chrg-ui'>"+
                            "<button class='chrgi-close chrg-no-select'></button>"+
                            "<button class='chrg-fullscreen chrgi-maximize chrg-no-select'></button>"+
                            "<button class='chrgi-next chrg-no-select'></button>"+
                            "<button class='chrgi-previous chrg-no-select'></button>"+
                            "<div class='chrg-loader'>"+
                                "<div class='chrg-loader-line-wrap-wrap'>"+
                                    "<div class='chrg-loader-line-wrap'></div></div>"+
                                "<div class='chrg-loader-line-wrap-wrap'>"+
                                    "<div class='chrg-loader-line-wrap'></div></div>"+
                                "<div class='chrg-loader-line-wrap-wrap'>"+
                                    "<div class='chrg-loader-line-wrap'></div></div>"+
                                "<div class='chrg-loader-line-wrap-wrap'>"+
                                    "<div class='chrg-loader-line-wrap'></div></div>"+
                                "<div class='chrg-loader-line-wrap-wrap'>"+
                                    "<div class='chrg-loader-line-wrap'></div>"+
                            "</div></div>"+
                "</div></div></div>"
            );

            //Cache some important elements
            this.$screen = $body.find(".chroma-screen");
            this.$imgWrap = this.$screen.find(".chrg-imgwrap");
            this.$fullscreenBtn = this.$screen.find(".chrg-fullscreen");
            this.$loader = this.$screen.find(".chrg-loader");
            this.$ui = this.$screen.find(".chrg-ui");
            this.$bg = this.$screen.find(".chrg-bg");
            this.lock = false;
            this.setEvents();
        },

        //Set ChromaScreen events
        setEvents:function()
        {
            var self = this;

            //Close button
            this.$screen.find(".chrgi-close").on("click",function(event)
            {
                event.stopPropagation();
                self.closeImg();
            });

            //Next button
            this.$screen.find(".chrgi-next").on("click",function(event)
            {
                event.stopPropagation();
                self.next();
            });

            //Prevent to close if the image was clicked
            this.$imgWrap.on("click",function(event)
            {
                event.stopPropagation();
            });

            //Previous button
            this.$screen.find(".chrgi-previous").on("click",function(event)
            {
                event.stopPropagation();
                self.prev();
            });

            $window.resize(function()
            {
                if(self.isOpen) self.resizeImgWrap();
            });

            window.addEventListener('orientationchange', function()
            {
                if(self.isOpen) self.resizeImgWrap();
            });

            $window.keydown(function(e)
            {
                if(self.isOpen)
                {
                    switch (e.keyCode) 
                    {
                        case keys.next: {self.next();break;}
                        case keys.prev: {self.prev();break;}
                        case keys.close: {self.closeImg();break;}
                    }
                }
            });            

            //Background click to close only at non touchable screens
            if(!Modernizr.touchevents)
            {
                this.$screen.find(".chrg-wrap").on("click",function()
                {
                    self.closeImg();
                });
            }

            //Fullscreen button
            if(Modernizr.fullscreen)
            {   

                this.$fullscreenBtn.on("click",function(event)
                {
                    event.stopPropagation();
                    self.toggleFullscreen();
                });             
            }            
        },

        //Enter or exit from fullscreen mode
        toggleFullscreen:function()
        {
            //Enter on fullscreen mode
            if (!document.fullscreenElement && !document.mozFullScreenElement && !document.webkitFullscreenElement && !document.msFullscreenElement) 
            {
                this.isFullscreen = true;
                this.$fullscreenBtn.removeClass("chrgi-maximize").addClass("chrgi-minimize");
                this.curGallery.trigger("chrg.fullscreen");

                //Cross browser implementations
                if (document.documentElement.requestFullscreen)
                    document.documentElement.requestFullscreen();
                else if (document.documentElement.msRequestFullscreen)
                    document.documentElement.msRequestFullscreen();
                else if (document.documentElement.mozRequestFullScreen)
                    document.documentElement.mozRequestFullScreen();
                else if (document.documentElement.webkitRequestFullscreen)
                    document.documentElement.webkitRequestFullscreen(Element.ALLOW_KEYBOARD_INPUT);
            }

            //Exit of fullscreen mode
            else 
            {
                this.isFullscreen = false;
                this.$fullscreenBtn.removeClass("chrgi-minimize").addClass("chrgi-maximize");

                //Cross browser implementations
                if (document.exitFullscreen) document.exitFullscreen();
                else if (document.msExitFullscreen) document.msExitFullscreen();
                else if (document.mozCancelFullScreen) document.mozCancelFullScreen();
                else if (document.webkitExitFullscreen) document.webkitExitFullscreen();
            }
        },

        //Resize ImgWrap
        resizeImgWrap:function()
        {
            var sizeImgWrap = {w:this.curItem.width(),h:this.curItem.height()},
                scale = Math.min($window.width()*0.92/sizeImgWrap.w,$window.height()*0.92/sizeImgWrap.h),
                imgWrapCss,
                pos = 
                {
                    x:($window.width() - sizeImgWrap.w*scale)/2,
                    y:($window.height() - sizeImgWrap.h*scale)/2
                };

            imgWrapCss = 
            {
                width:sizeImgWrap.w*scale,
                height:sizeImgWrap.h*scale
            };

            if(Modernizr.csstransforms3d)
                imgWrapCss[vendorPrefix+"transform"] = "translate3d("+pos.x+"px,"+pos.y+"px,0) scale(1)";
            else
                imgWrapCss[vendorPrefix+"transform"] = "translate("+pos.x+"px,"+pos.y+"px) scale(1)";

            //Here is important that css is applied once
            this.$imgWrap.css(imgWrapCss);
        },

        loadImg:function(newImg)
        {
            var self = this,
                largeimg = new Image();

            if(Modernizr.cssanimations) this.$loader.show();
            largeimg.onload = function () 
            {
                self.$imgWrap.append(this);
                if(Modernizr.cssanimations)
                    self.$loader.hide();                      
            };
            largeimg.onerror = function()
            {
                self.$loader.hide();
            };
            largeimg.src = newImg.data("largesrc");
        },

        //Get the background rgb color
        getRGBColor: function(image)
        {   
            //Use image dominant color
            if(this.settings.color == "chroma")
            {
                //If the dominant color was not previously defined
                if(image.data("color") === undefined)
                {
                    var rgbColor,
                        rgbArr = this.colorThief.getColor(image[0]);

                    if(rgbArr && rgbArr.length == 3)
                        rgbColor = "rgb("+rgbArr[0]+","+rgbArr[1]+","+rgbArr[2]+")";
                    else rgbColor = "rgb(0,0,0)";

                    //cache the img color
                    image.attr("data-color",rgbColor);
                    return rgbColor;
                }
                return image.data("color");
            }
            return this.settings.color;
        },

        //Open a img
        openImg:function()
        { 
            if(!this.lock)
            {
                this.lock = true;
                var self = this,                 
                    curItemOffset = this.curItem.offset(),
                    rgbColor = this.getRGBColor(this.curItem.find("img")),
                    maxSize = {width:$window.width()*0.92,height:$window.height()*0.92},
                    imgClone = this.curItem.find("img").clone(),
                    scale,dim;

                this.$imgWrap.html("");
                if(this.settings.fullscreen) this.$fullscreenBtn.show();
                else this.$fullscreenBtn.hide();
                scale = Math.min(maxSize.width/this.curItem.width(),maxSize.height/this.curItem.height());
                dim = 
                {
                    x:curItemOffset.left - $window.scrollLeft(),
                    y:curItemOffset.top - $window.scrollTop(),
                    width:this.curItem.width()*scale,
                    height:this.curItem.height()*scale
                };

                this.$imgWrap.css({width:dim.width,height:dim.height});
                if(Modernizr.csstransforms3d)
                    this.$imgWrap.css(vendorPrefix+"transform","translate3d("+dim.x+"px,"+dim.y+"px,0) scale("+(1/scale)+")");
                else
                    this.$imgWrap.css(vendorPrefix+"transform","translate("+dim.x+"px,"+dim.y+"px) scale("+(1/scale)+")");

                if(this.settings.dof)
                {
                    $body.wrapInner("<div class='chrg-dof-body'></div>");
                    this.$screen.appendTo($body);
                } 

                dim.x = ($window.width() - dim.width)/2;
                dim.y = ($window.height() - dim.height)/2;
                this.$imgWrap.addClass("chrg-animation chrg-easing-"+this.settings.easing);
                this.$imgWrap.append(imgClone);
                this.$screen.show();
                this.isOpen = true;
                setTimeout(function()
                {
                    self.curItem.css("visibility","hidden");
                    self.$ui.css("opacity",0.01);
                    self.$ui.css("opacity",1);
                    self.$bg.css("opacity",0.01);
                    self.$bg.css
                    ({
                        "opacity":self.settings.screenOpacity,
                        "background-color":rgbColor
                    });

                    if(self.settings.dof) $body.find(".chrg-dof-body").addClass("chrg-dof-effect");
                    if(Modernizr.cssanimations) self.$loader.show();
                    if(Modernizr.csstransforms3d)
                        self.$imgWrap.css(vendorPrefix+"transform","translate3d("+dim.x+"px,"+dim.y+"px,0) scale(1)");
                    else
                        self.$imgWrap.css(vendorPrefix+"transform","translate("+dim.x+"px,"+dim.y+"px) scale(1)");

                    setTimeout(function()
                    {
                        self.lock = false;
                        self.curGallery.trigger("chrg.openImg");
                        self.loadImg(imgClone);
                    },animationTime+50);
                },80);
            }
        },

        //Close any opened img
        closeImg:function()
        {
            if(this.isOpen && !this.lock)
            {
                this.lock = true;
                var self = this,
                    scale = this.curItem.width()/this.$imgWrap.width(),
                    curItemOffset = this.curItem.offset(),
                    dim = 
                    {
                        x:curItemOffset.left - $window.scrollLeft(),
                        y:curItemOffset.top - $window.scrollTop()
                    };

                this.$imgWrap.find("img:nth-child(2)").hide();
                this.$ui.css("opacity","");
                this.$bg.css("opacity","");
                if(this.settings.dof) $body.find(".chrg-dof-body").contents().unwrap();
                setTimeout(function()
                {
                    if(Modernizr.csstransforms3d)
                        self.$imgWrap.css(vendorPrefix+"transform", "translate3d("+dim.x+"px,"+dim.y+"px,0) scale("+scale+")");
                    else
                        self.$imgWrap.css(vendorPrefix+"transform", "translate("+dim.x+"px,"+dim.y+"px) scale("+scale+")");

                    setTimeout(function()
                    {
                        self.curItem.css("visibility","");
                        self.$imgWrap.attr("class","chrg-imgwrap");
                        self.curGallery.trigger("chrg.closeImg");
                        setTimeout(function()
                        {
                            self.curItem = null;
                            self.$screen.hide();
                            if(Modernizr.fullscreen && self.isFullscreen) self.toggleFullscreen();
                            self.curGallery = null;
                            self.isOpen = false;
                            self.lock = false;
                        },50);
                    },animationTime+50);                            
                },50);              
            }
        },

        //Go to next image
        next:function()
        {
            if(!this.lock)
            {
                this.lock = true;
                var self = this,
                    curIndex = this.curItem.index(),
                    nextIndex = (curIndex+1 == this.curGallery.find(".chrg-item").length) ? 0:curIndex+1,
                    nextItem = this.curGallery.find(".chrg-item:eq("+nextIndex+")");
                this.goTo(nextItem);
                self.curGallery.trigger("chrg.next");    
            }
        },

        swipeNext:function()
        {

        },

        //Go to previous image
        prev:function()
        {
            if(!this.lock)
            {
                this.lock = true;
                var self = this,
                    curIndex = this.curItem.index(),
                    nextIndex = (curIndex == 0) ? (this.curGallery.find(".chrg-item").length-1):curIndex-1,
                    prevItem = this.curGallery.find(".chrg-item:eq("+nextIndex+")");
                this.goTo(prevItem);
                self.curGallery.trigger("chrg.prev");   
            }
        },

        swipePrev:function()
        {

        },

        //Go to specific image
        goTo:function(newItem)
        {
            var self = this,
                imgClone = newItem.find("img").clone(),
                rgbColor = this.getRGBColor(newItem.find("img")),
                maxSize = {width:$window.width()*0.92,height:$window.height()*0.92},
                scale,dim,imgWrapCss;

            scale = Math.min(maxSize.width/newItem.width(),maxSize.height/newItem.height());
            dim = 
            {
                width:newItem.width()*scale,
                height:newItem.height()*scale,
                x:($window.width() - newItem.width()*scale)/2,
                y:($window.height() - newItem.height()*scale)/2
            };

            imgWrapCss = {width:dim.width,height:dim.height};
            if(Modernizr.csstransforms3d)
                imgWrapCss[vendorPrefix+"transform"] = "translate3d("+dim.x+"px,"+dim.y+"px,0) scale(1)";
            else
                imgWrapCss[vendorPrefix+"transform"] = "translate("+dim.x+"px,"+dim.y+"px) scale(1)";

            this.$imgWrap.removeClass("chrg-animation");
            self.curItem.css("visibility","visible");
            self.$imgWrap.css(imgWrapCss);
            self.$imgWrap.html("");
            self.$imgWrap.append(imgClone);
            self.$bg.css({"background-color":rgbColor});
            self.curItem = newItem;
            self.curItem.css("visibility","hidden");
            self.loadImg(imgClone);
            setTimeout(function()
            {
                self.$imgWrap.addClass("chrg-animation");
                self.lock = false;
            },50);
        },
    });

    //Jquery Plugin Initialization
    $.fn[pluginName] = function ( options ) 
    {
        var args = arguments;
        if (options === undefined || typeof options === 'object') 
        {
            return this.each(function () 
            {
                if (!$.data(this, 'plugin_' + pluginName)) 
                    $.data(this, 'plugin_' + pluginName, new Plugin( this, options ));
            });
        }
        else if (typeof options === 'string' && options[0] !== '_' && options !== 'init') 
        {
            var returns;
            this.each(function () 
            {
                var instance = $.data(this, 'plugin_' + pluginName);
                if (instance instanceof Plugin && typeof instance[options] === 'function')
                    returns = instance[options].apply( instance, Array.prototype.slice.call( args, 1 ) );
                if (options === 'destroy')
                  $.data(this, 'plugin_' + pluginName, null);
            });
            return returns !== undefined ? returns : this;
        }
    };

}(jQuery, window, document));