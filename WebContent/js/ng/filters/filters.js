define(['angular'],function(angular){
	return angular.module('myFilters', []).filter('true_or_false', function() {
	    return function(input,trueValue,falseValue) {
	        if(arguments.length == 1) {
	            trueValue = '';
	            falseValue = '';
	        }
	        if(arguments.length == 2) {
	            falseValue = '';
	        }
	        return input ? trueValue : falseValue;
	    };
	}).filter('startFrom', function() {
	    return function(input, start) {
	        if(input) {
	            start = +start; //parse to int
	            return input.slice(start);
	        }
	    };
	}).filter('str_sub',function() {
        return function(str,len) {
            var strlen = 0;
            var s = "";
            for(var i = 0;i < str.length;i++)
            {
                if(str.charCodeAt(i) > 128){
                    strlen += 2;
                }else{
                    strlen++;
                }
                s += str.charAt(i);
                if(strlen >= len){
                    return s + '...' ;
                }
            }
            return s;
        };
	}).filter('parentShow',function() {
        return function(edit, child) {
        	if (!edit) {
				return false;
			}
			var flag = true;
			for ( var i = 0; i < child.length; i++) {
				if (child[i].edit = true) {
					flag = false;
					break;
				}
			}
			console.log(edit);
			console.log(flag);
			return edit && flag;
        };
	});
});
