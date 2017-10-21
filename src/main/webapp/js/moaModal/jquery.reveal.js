$(function () {

    /*---------------------------
     Defaults for Reveal
    ----------------------------*/

    /*---------------------------
     Listener for data-reveal-id attributes
    ----------------------------*/

    $('a[data-reveal-id]').on('click', function (e) {
        e.preventDefault();
        var modalLocation = $(this).attr('data-reveal-id');
        $('#' + modalLocation).reveal($(this).data());
    });

    /*---------------------------
     Extend and Execute
    ----------------------------*/

    $.fn.reveal = function (options) {


        var defaults = {
            animation: 'fadeAndPop', //fade, fadeAndPop, none
            animationspeed: 300, //how fast animtions are
            closeonbackgroundclick: true, //if you click background will modal close?
            dismissmodalclass: 'close-reveal-modal' //the class of a button or element that will close an open modal
        };

        //Extend dem' options
        var options = $.extend({}, defaults, options);

        return this.each(function () {

            /*---------------------------
             Global Variables
            ----------------------------*/
            var modal = $(this),
                topMeasure = parseInt(modal.css('top')),
                topOffset = modal.height() + topMeasure,
                locked = false,
                modalBG = $('.reveal-modal-bg');

            /*---------------------------
             Create Modal BG
            ----------------------------*/
            if (modalBG.length == 0) {
                modalBG = $('<div class="reveal-modal-bg">').insertAfter(modal);
            }

            /*---------------------------
             Open & Close Animations
            ----------------------------*/
            //Entrance Animations
            modal.on('reveal:open', function () {
                modalBG.off('click.modalEvent');
                $('.' + options.dismissmodalclass).off('click.modalEvent');
                if (!locked) {
                    lockModal();
                    if (options.animation == "fadeAndPop") {
                        modal.css({'top': $(document).scrollTop() - topOffset, 'opacity': 0, 'visibility': 'visible'});
                        modalBG.fadeIn(options.animationspeed / 2);
                        modal.delay(options.animationspeed / 2).animate({
                            "top": $(document).scrollTop() + topMeasure + 'px',
                            "opacity": 1
                        }, options.animationspeed, unlockModal());
                    }
                    if (options.animation == "fade") {
                        modal.css({'opacity': 0, 'visibility': 'visible', 'top': $(document).scrollTop() + topMeasure});
                        modalBG.fadeIn(options.animationspeed / 2);
                        modal.delay(options.animationspeed / 2).animate({
                            "opacity": 1
                        }, options.animationspeed, unlockModal());
                    }
                    if (options.animation == "none") {
                        modal.css({'visibility': 'visible', 'top': $(document).scrollTop() + topMeasure});
                        modalBG.css({"display": "block"});
                        unlockModal()
                    }
                }
                modal.off('reveal:open');
            });

            //Closing Animation
            modal.on('reveal:close', function () {
                if (!locked) {
                    lockModal();
                    if (options.animation == "fadeAndPop") {
                        modalBG.delay(options.animationspeed).fadeOut(options.animationspeed);
                        modal.animate({
                            "top": $(document).scrollTop() - topOffset + 'px',
                            "opacity": 0
                        }, options.animationspeed / 2, function () {
                            modal.css({'top': topMeasure, 'opacity': 1, 'visibility': 'hidden'});
                            unlockModal();
                        });
                    }
                    if (options.animation == "fade") {
                        modalBG.delay(options.animationspeed).fadeOut(options.animationspeed);
                        modal.animate({
                            "opacity": 0
                        }, options.animationspeed, function () {
                            modal.css({'opacity': 1, 'visibility': 'hidden', 'top': topMeasure});
                            unlockModal();
                        });
                    }
                    if (options.animation == "none") {
                        modal.css({'visibility': 'hidden', 'top': topMeasure});
                        modalBG.css({'display': 'none'});
                    }
                }
                modal.off('reveal:close');
            });

            /*---------------------------
             Open and add Closing Listeners
            ----------------------------*/
            //Open Modal Immediately
            modal.trigger('reveal:open')

            //Close Modal Listeners
            var closeButton = $('.' + options.dismissmodalclass).on('click.modalEvent', function () {
                modal.trigger('reveal:close')
            });

            if (options.closeonbackgroundclick) {
                modalBG.css({"cursor": "pointer"})
                modalBG.on('click.modalEvent', function () {
                    modal.trigger('reveal:close')
                });
            }
            $('body').keyup(function (e) {
                if (e.which === 27) {
                    modal.trigger('reveal:close');
                } // 27 is the keycode for the Escape key
            });


            /*---------------------------
             Animations Locks
            ----------------------------*/
            function unlockModal() {
                locked = false;
            }

            function lockModal() {
                locked = true;
            }

        });//each call
    }//orbit plugin call
});