jQuery.fn.wait = function (func, times, interval) { 
var _times = times || -1, //100次 
_interval = interval || 20, //20毫秒每次 
_self = this, 
_selector = this.selector, //选择器 
_iIntervalID; //定时器id 
if( this.length ){ //如果已经获取到了，就直接执行函数 
func && func.call(this); 
} else { 
_iIntervalID = setInterval(function() { 
if(!_times) { //是0就退出 
clearInterval(_iIntervalID); 
} 
_times <= 0 || _times--; //如果是正数就 -- 

_self = $(_selector); //再次选择 
if( _self.length ) { //判断是否取到 
func && func.call(_self); 
clearInterval(_iIntervalID); 
} 
}, _interval); 
} 
return this; 
}

$(document).ready(function(){

	$('#SOHU_MAIN .head-img-gw img').wait(function(){
		var imgs = new Array();
		imgs[0] = 'http://qiniu.cuiqingcai.com/wp-content/uploads/2015/05/20150525111154.jpg';
		imgs[1] = 'http://qiniu.cuiqingcai.com/wp-content/uploads/2015/05/20150525111447.jpg';
		imgs[2] = 'http://qiniu.cuiqingcai.com/wp-content/uploads/2015/05/20150525112058.jpg';
		imgs[3] = 'http://qiniu.cuiqingcai.com/wp-content/uploads/2015/05/20150525112112.jpg';
		imgs[4] = 'http://qiniu.cuiqingcai.com/wp-content/uploads/2015/05/20150525112129.jpg';
		imgs[5] = 'http://qiniu.cuiqingcai.com/wp-content/uploads/2015/05/20150525112155.jpg';
		
			
		$('.head-img-gw img[src*="avatar"]').each(function(){
			var rand = Math.floor(Math.random()*imgs.length);
			$(this).attr("src",imgs[rand]);
		});
	});
	$('#SOHUCS #SOHU_MAIN .section-cbox-w .post-default-b').wait(function(){
		setTimeout(function() {
			$('#SOHU_MAIN .section-cbox-w .post-default-b').css('border', '1px solid #ccd4d9');
		}, 200);
	});
	
});

$(function(){
	// $('a').on('click', function(){
	// 	console.log($("#iframeu2027169_0").contents().html());
	// 	console.log($("#iframeu1959939_0").contents().find('.container a:first-child').attr('href'));
	// 	$('#iframeu1959939_0').contents().find('.container a:first-child').click();
	// });
	
});

