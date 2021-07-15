/**
 * jquery waterfall
 * @author richard chen
 * @update 2014-10-07
 * @version 1.0
 * @parameters
 *     itemClass: the brick element class
 *     spacingWidth: the brick element horizontal spacing
 *     spacingHeight: the brick element vertical spacing
 *     minColCount: min columns
 *     resizeable: trigger positionAll() when browser window is resized
 *     itemAlign: the alignment of the brick element ( e.q. [center|left] )
 *     isFadeIn: fadeIn effect on loading
 *     ajaxCallback: callback when ajax loaded, two parameters ( success, end )
 */
(function($) {
    var $window = $(window),
        pluginName = 'waterfall',
        defaults = {
            itemClass: "waterfall-item", // the brick element class
            spacingWidth: 10, // the brick element horizontal spacing
            spacingHeight: 10, // the brick element vertical spacing
            minColCount: 2, // min columns
            resizeable: false, // trigger positionAll() when browser window is resized
            itemAlign: "center", // the alignment of the brick element ( e.q. [center|left] )
            isFadeIn: true, // fadeIn effect on loading
            ajaxCallback: null // callback when ajax loaded, two parameters ( success, end )
        };

    function Waterfall(element, options) {
        this.$element = $(element);
        this.options = $.extend(true, {}, defaults, options);
        this.ajaxLoading = false; // is ajax loading
        this.colHeightArray = []; // height of each columns

        this._init();
    }

    Waterfall.prototype = {
        constructor: Waterfall,

        _init: function () {
            var $this = this;

            $window.on("load", function() {
                $this._positionAll();
            });

            if(this.options.resizeable) {
                $window.on("resize", function() {
                    $this._positionAll();
                });
            }

            this._doScroll();
        },
        _getColumnCount: function () {
            var parentWidth = this.$element.width(),
                $item = $(this.options.itemClass),
                itemWidth = $item.eq(0).outerWidth(),
                iCol = Math.floor(parentWidth / (itemWidth + this.options.spacingWidth)),
                realWidth = 0,
                leftOffset = 0;

            iCol = iCol > this.options.minColCount ? iCol : this.options.minColCount;
            realWidth = iCol * itemWidth;

            if(parentWidth > realWidth) {
                leftOffset = Math.floor((parentWidth - realWidth - iCol * this.options.spacingWidth) / 2);
            }
            this.itemWidth = itemWidth;
            this.cols = iCol;
            this.leftOffset = this.options.itemAlign == "center" ? leftOffset : 0;
        },
        _positionAll: function () {
            var $this = this,
                $item = $(this.options.itemClass),
                minHeight,
                minIndex;

            this._getColumnCount();
            this.colHeightArray = [];

            $item.each(function(index) {
                $(this).css("position", "absolute");
                if(index < $this.cols) {
                    $(this).css("top", 0);
                    $(this).css("left", $this.leftOffset + index * $this.itemWidth + index * $this.options.spacingWidth);
                    $this.colHeightArray.push($(this).outerHeight());
                } else {
                    minHeight = Math.min.apply(null, $this.colHeightArray);
                    minIndex = $.inArray(minHeight, $this.colHeightArray);

                    $(this).css("top", minHeight + $this.options.spacingHeight);
                    $(this).css("left", $item.eq(minIndex).offset().left);
                    $this.colHeightArray[minIndex] += $(this).outerHeight() + $this.options.spacingHeight;
                }

                if($this.options.isFadeIn) {
                    $(this).animate({"opacity": 1}, 300);
                }
                
            });

            this.$element.css("height", Math.max.apply(null, $this.colHeightArray));
        },
        _doScroll: function () {
            var $this = this,
                scrollTimer;

            $window.on("scroll", function() {
                if(scrollTimer) {
                    clearTimeout(scrollTimer);
                }

                scrollTimer = setTimeout(function() {
                    var $last = $($this.options.itemClass).last(),
                        scrollTop = $window.scrollTop() + $window.height();

                    if(!$this.ajaxLoading && scrollTop > $last.offset().top + $last.outerHeight() / 2) {
                        $this.ajaxLoading = true;
                        $this.options.ajaxCallback && $this.options.ajaxCallback(
                            // reposition all the brick element after successful load ajax data
                            function() {
                                $this._positionAll();
                            },
                            function() {
                                $this.ajaxLoading = false;
                            }
                        );
                    }
                }, 100);
            });
        }
    }

    $.fn[pluginName] = function (options) {
        this.each(function() {
            if(!$.data(this, "plugin_" + pluginName)) {
                $.data(this, "plugin_" + pluginName, new Waterfall(this, options));
            }
        });

        return this;
    }
})(jQuery);