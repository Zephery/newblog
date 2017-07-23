/*
@说明： jQuery 1.10 开始不支持 $.browser 故而对此进行兼容处理
*/

if( !$.browser ){
	$.browser = {};
	(function (){
		var ua = navigator.userAgent.toLowerCase(),
			// Useragent RegExp
			rwebkit = /(webkit)[ \/]([\w.]+)/,
			ropera = /(opera)(?:.*version)?[ \/]([\w.]+)/,
			rmsie = /(msie) ([\w.]+)/,
			rmozilla = /(mozilla)(?:.*? rv:([\w.]+))?/,
			match = rwebkit.exec( ua ) ||
					ropera.exec( ua ) ||
					rmsie.exec( ua ) ||
					ua.indexOf("compatible") < 0 && rmozilla.exec( ua ) ||
					[];

			browserMatch = { browser: match[1] || "", version: match[2] || "0" };

		if( browserMatch.browser ){
			$.browser[browserMatch.browser] = true;
			$.browser.version = browserMatch.version;
		}
	})()
}