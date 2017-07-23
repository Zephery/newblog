(function($){

    $(document).ready(function(){

        $.fn.center=function(){

            return this.each(function(){

                var $this=$(this),

                    parent=$this.parent(),

                    topPos,

                    topMargin,

                    leftMargin,

                    resizeTimeout;

                if(parent.is("body:not(.root-height-set)")){

                    $("html,body").css("height","100%").addClass("root-height-set");

                }

                if($this.css("position")==="absolute" || $this.css("position")==="fixed"){

                    topPos="50%";

                    topMargin="-"+Math.round($this.outerHeight()/2)+"px";

                    leftMargin="-"+Math.round($this.outerWidth()/2)+"px";

                    $this.css({"left":"50%","margin-left":leftMargin});

                }else{

                    topPos=Math.floor((parent.height()-$this.outerHeight())/2);

                    topMargin="auto";

                    $this.css({"position":"relative","margin-left":"auto","margin-right":"auto"});

                }

                $this.css({"top":topPos,"margin-top":topMargin});

                $(window).resize(function(){

                    if(resizeTimeout){

                        clearTimeout(resizeTimeout);

                    }

                    resizeTimeout=setTimeout(function(){

                        if($this.css("position")==="absolute"){

                            topMargin="-"+Math.round($this.outerHeight()/2)+"px";

                            leftMargin="-"+Math.round($this.outerWidth()/2)+"px";

                            $this.css({"margin-left":leftMargin,"margin-top":topMargin});

                        }else{

                            topPos=Math.floor((parent.height()-$this.outerHeight())/2);

                            $this.css("top",topPos);

                        }

                    },150);

                });

            });

        };

        $(".outer,.inner").center();

    });

})(jQuery);
