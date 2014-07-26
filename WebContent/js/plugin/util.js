define(['jquery'], function(jquery) {
	(function($) {
		var Util = function() {
			
		};
		/**
		 * post访问后台
		 * @param url
		 * @param params 参数，一对象形式
		 * @returns
		 */
		Util.post = function(url, params) {      
		    var temp = document.createElement("form");      
		    temp.action = url;      
		    temp.method = "post";      
		    temp.style.display = "none";      
		    for (var x in params) {      
		        var opt = document.createElement("textarea");      
		        opt.name = x;      
		        opt.value = params[x];      
		        temp.appendChild(opt);      
		    }      
		    document.body.appendChild(temp);      
		    temp.submit();      
		    return temp;      
		};
		/**
		 * 获取窗口宽度
		 * @returns {number}
		 */
		Util.getWinWidth = function() {
		    var winWidth = 0;
		    // 获取窗口宽度
		    if (window.innerWidth)
		        winWidth = window.innerWidth;
		    else if ((document.body) && (document.body.clientWidth))
		        winWidth = document.body.clientWidth;
		    if (document.documentElement && document.documentElement.clientHeight && document.documentElement.clientWidth)
		    {
		        winWidth = document.documentElement.clientWidth;
		    }
		    return winWidth;
		};
		/**
		 * 获取窗口高度
		 * @returns {number}
		 */
		Util.getWinHeight = function() {
		    var winHeight = 0;
		    // 获取窗口高度
		    if (window.innerHeight)
		        winHeight = window.innerHeight;
		    else if ((document.body) && (document.body.clientHeight))
		        winHeight = document.body.clientHeight;
		    // 通过深入 Document 内部对 body 进行检测，获取窗口大小
		    if (document.documentElement && document.documentElement.clientHeight && document.documentElement.clientWidth)
		    {
		        winHeight = document.documentElement.clientHeight;
		    }
		    return winHeight;
		};
		/**
		 * 获取窗口上边隐藏高度
		 * @returns {number}
		 */
		Util.getWinTop = function() {
		    var winTop = 0;
		    // 获取窗口高度
		    if ((document.body) && (document.body.scrollTop))
		        winTop = document.body.scrollTop;
		    // 通过深入 Document 内部对 body 进行检测，获取窗口大小
		    if (document.documentElement && document.documentElement.scrollTop)
		    {
		        winTop = document.documentElement.scrollTop;
		    }
		    return winTop;
		};
		/**
		 * 深度打印一个js对象,主要用户在ie下调试
		 * @param obj
		 */
		Util.consoleObject = function(obj) {
			for ( var i in obj) {
				if($.isPlainObject(obj[i]) || $.isArray(obj[i])) {
					this.consoleObject(obj[i]);
				} else {
					console.log(i + '--' + obj[i]);
				}
			}
		};
		/**
		 * 获取已知名字的cookie值
		 * @param name
		 * @returns
		 */
		Util.getCookie = function(name) {
			var strCookie = document.cookie; 
			var arrCookie = strCookie.split("; "); 
			for(var i = 0; i < arrCookie.length; i++){ 
				var arr = arrCookie[i].split("="); 
				if(arr[0]==name) 
					return unescape(arr[1]); 
			}
		};
		/**
		 * 添加一个cookie
		 * @param name
		 * @param value
		 */
		Util.addCookie = function(name, value) {
			document.cookie = name + '=' + value;
		};
		if (!window.Util) {
			window['Util'] = Util;
		}
	})(jquery);
});