/**
 * 入口文件
 * */
require.config({
	//baseUrl: "js/", //基于的路径,如果和require.js同一目录下，则不用
	paths: {
		jquery: 'jquery1.9.min',
		/*外部引用时的名称：相应的文件名（也可以在此添加路径）*/
	},
	shim: {
		/*文件名*/
		'jquery.my-modal.1.1': {
			/*该模块的依赖*/
			//deps: ['jquery1.9.min'],/*因为在paths{}里面已经引用，所以不用再引进依赖项*/
			exports: 'MyModal' /*该模块对外提供的接口*/
		},
	}
});

//require(['jquery', 'AMD-jquery.my-message.1.1'], function($, m) {
require(['jquery', 'jquery.my-modal.1.1'], function($, m) {
	var m1 = new m.modal(function() {
		alert("你点击了确定");
	});
	$('.btn1').on("click", function() {
		m1.show();
	});
});