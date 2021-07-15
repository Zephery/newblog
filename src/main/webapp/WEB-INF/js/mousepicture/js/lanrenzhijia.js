/* 代码整理：懒人之家 www.lanrenzhijia.com */
$(function(){
		$(".item1 li").hover(
			function(){
				var that=this;	
				item1Timer=setTimeout(function(){
					$(that).find("div").animate({"top":0,"height":200},300,function(){
						$(that).find("p").fadeIn(200);
					});
				},100);
			},
			function(){
				var that=this;	
				clearTimeout(item1Timer);
				$(that).find("p").fadeOut(200);
				$(that).find("div").animate({"top":0,"height":0},0);
			}
		)
});

/* 代码整理：懒人之家 www.lanrenzhijia.com */