/*!
@说明： Tab Switch jQuery plugin
*/

;(function(){
  //main 初始化
  $.fn.tabswitch = function(fn,selector,op){
    var $t = this;
    //如果预计的第一个参数不是函数，则参数列左移一位
    if( typeof fn !== 'function' ){
      op = selector;
      selector = fn;
      fn = $.noop;
    }
    //如果预计的第二个参数不是字符串，则参数列左移一位
    if( typeof selector !== 'string' ){
      op = selector;
      selector = '';
    }

    //如果预计的第三个参数不是对象，extend一个空对象
    var opts = $.extend({},$.fn.tabswitch.defaults,op);
    //fn 记录到opt 中去
    opts.fn = fn;



    //仅用作内部判断
    var hasdelegate = !!selector;

    //内容容器
    var $conts = $(opts.cont),
      //获取触发按钮
      $trigs = hasdelegate ? $(selector,$t) : $t,
      //当前状态的className 默认加上 cur className
      fclass = opts.focus;



    //设置当前tab 默认为0，设置完毕后将自动运行 _autodo 方法
    _settab(opts.index)


    //事件绑定，注意防止重复装载事件
    if( hasdelegate ){
      this.delegate(selector,opts.event,_clickdo)
    }else{
      this.bind(opts.event,_clickdo);
    }

    
    //main 主事件执行
    function _clickdo(e){
      e.preventDefault();
      _settab($trigs.index(this),true);
    }
    // _clickdo 方法结束

    /*
    描述：通过序号设置当前状态
    参数：
    i        - (Num) 序号
    b        - (Bool) 如果是点击触发，或自动播放触发，则为 true，如果是初始化执行则为 false
    */
    function _settab(i,b){
      var $mtrigs = $trigs,
        $pre,
        prei = $trigs.index($pre),
        mi = -1,
        si = -1,
        ci=i; // ci 表示当前要被显示的cont cont永远只显示一个

      $pre = $mtrigs.filter('.'+fclass)
      prei = $trigs.index($pre)

      $mtrigs.add($conts).removeClass(fclass);
        
      //重新设置trigs 和 conts 当前项加上 fclass
      $conts.css('display','none').eq(ci).css('display','block').add($trigs.eq(i)).addClass(fclass);

      //每次都执行
      if( typeof opts.fn === 'function' ){
        opts.fn($trigs,$conts,ci,prei,b);
      }
    }

    return $t;
  }

  $.fn.tabswitch.defaults = { //默认配置
    "cont" : ".tabswitch-cont", //切换内容块的选择器
    "focus" : "cur", //聚焦状态的className 非选择器
    "index" : 0, //初始状态下聚焦项的序号，默认为0
    "event" : "click" //切换trigger 触发事件，默认是 click
  };
})();
