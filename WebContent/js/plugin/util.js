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
		Util.post = function(url, params, isMulti) {      
		    var temp = document.createElement("form");      
		    temp.action = url;      
		    temp.method = "post";      
		    temp.style.display = "none";  
		    if (isMulti) {
				temp.enctype = 'multipart/form-data';
			}
		    for ( var i = 0; i < params.length; i++) {
		    	var opt = document.createElement(params[i].element);      
		    	opt.name = params[i].name;      
		    	opt.value = params[i].value;      
		    	opt.type = params[i].type;
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
		/**
		 * 冻结table
		 * @param TableID 要锁定的Table的ID
		 * @param FixColumnNumber 要锁定列的个数
		 * @constructor
		 */
		Util.fixTable = function(TableID, FixColumnNumber, config) {
		    var width = 0,height = 0;
		    var oldTable = $("#" + TableID),
		        tableLayout = $("#" + TableID + "_tableLayout");
		    height = oldTable.height()+17;
		    
		    oldTable.data('config', config);
		    oldTable.data('col', FixColumnNumber);
		    
		    if (tableLayout.length != 0) {
		        tableLayout.css({height:height});
		        width = tableLayout.width();
		        height = tableLayout.height();
		        tableLayout.before($("#" + TableID));
		        tableLayout.empty();
		    } else {
		    	var maxHeight = config && config.maxHeight || 500;
		        $("#" + TableID).after("<div id='" + TableID + "_tableLayout' style='overflow:hidden;max-height:" + maxHeight + "px;height:"+height+"px; width:100%;'></div>");
		        tableLayout = $("#" + TableID + "_tableLayout");
		        width = tableLayout.width();
		        height = tableLayout.height();
		    }
		    $('<div id="' + TableID + '_tableFix"></div>'
		        + '<div id="' + TableID + '_tableHead"></div>'
		        + '<div id="' + TableID + '_tableColumn"></div>'
		        + '<div id="' + TableID + '_tableData"></div>').appendTo(tableLayout);
		    oldTable.width(width-17);
		    var tableFix = $("#" + TableID + "_tableFix"),
		        tableHead = $("#" + TableID + "_tableHead"),
		        tableColumn = $("#" + TableID + "_tableColumn"),
		        tableData = $("#" + TableID + "_tableData");
		    var tableFixClone = oldTable.clone(true);
		    tableFixClone.attr("id", TableID + "_tableFixClone");
		    tableFix.append(tableFixClone);
		    var tableHeadClone = oldTable.clone(true);
		    tableHeadClone.attr("id", TableID + "_tableHeadClone");
		    tableHead.append(tableHeadClone);
		    var tableColumnClone = oldTable.clone(true);
		    tableColumnClone.attr("id", TableID + "_tableColumnClone");
		    tableColumn.append(tableColumnClone);
		    tableData.append(oldTable);
		    $("#" + TableID + "_tableLayout table").each(function () {
		        $(this).css("margin", "0");
		    });
		    var HeadHeight = $("#" + TableID + "_tableHead thead").height();
		    HeadHeight += 2;
		    tableHead.css("height", HeadHeight);
		    tableFix.css("height", HeadHeight);
		    var ColumnsWidth = 0;
		    var ColumnsNumber = 0;
		    $("#" + TableID + "_tableColumn tr:last td:lt(" + FixColumnNumber + ")").each(function () {
		        ColumnsWidth += $(this).outerWidth(true);
		        ColumnsNumber++;
		    });
		    ColumnsWidth += 2;
		    if ($.browser.msie) {
		        switch ($.browser.version) {
		            case "7.0":
		                if (ColumnsNumber >= 3) ColumnsWidth--;
		                break;
		            case "8.0":
		                if (ColumnsNumber >= 2) ColumnsWidth--;
		                break;
		        }
		    }
		    tableColumn.css("width", ColumnsWidth);
		    tableFix.css("width", ColumnsWidth);
		    tableData.scroll(function () {
		        tableHead.scrollLeft(tableData.scrollLeft());
		        tableColumn.scrollTop(tableData.scrollTop());
		    });
		    tableFix.css({ "overflow": "hidden", "position": "relative", "z-index": "50", "background-color": "rgb(170,205,225" });
		    tableHead.css({ "overflow": "hidden", "width": width - 17, "position": "relative", "z-index": "45", "background-color": "rgb(170,205,225" });
		    tableColumn.css({ "overflow": "hidden", "height": height - 17, "position": "relative", "z-index": "40", "background-color": "rgb(170,205,225" });
		    tableData.css({ "overflow": "auto",overflowX:'auto',overflowY:'auto', "width": width, "height": height, "position": "relative", "z-index": "35" });
		    var tableFixTable = $("#" + TableID + "_tableFix table");
		    if (tableHead.width() > tableFixTable.width()) {
		        tableHead.css("width", tableFixTable.width());
		        tableData.css("width", tableFixTable.width() + 18);
		    }
		    var tableColumnTable = $("#" + TableID + "_tableColumn table");
		    if (tableColumn.height() > tableColumnTable.height()) {
		        tableColumn.css("height", tableColumnTable.height());
		        tableData.css("height", tableColumnTable.height() + 17);
		    }
		    tableFix.offset(tableLayout.offset());
		    tableHead.offset(tableLayout.offset());
		    tableColumn.offset(tableLayout.offset());
		    tableData.offset(tableLayout.offset());
		};

		if (!window.Util) {
			window['Util'] = Util;
		}
	})(jquery);
});