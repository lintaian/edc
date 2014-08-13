define(['angular', 'jquery'], function(angular, $) {
	return angular.module('scrollLoading', []).directive('scrollLoading', ['Count', function(Count) {
		return {
			restrict: 'AE',
	        priority: 1,
	        link: function (scope, element, attrs) {
	        	var config = scope.$eval(attrs.scrollLoadingConfig);
	        	var option = {
        			page: 1,
        			per_page: 50,
        			method: 'get'
	        	};
	        	angular.extend(option, config);
	        	scope[option.data] = [];
	        	var loading = false;
	        	addData(true);
	        	function addData(first, callback) {
	        		if(option.service == 'count') {
	        			loading = true;
	        			Count[option.method]({page: option.page, per_page: option.per_page}, function(data) {
	        				scope[option.data].concat(data);
	        				option.page++;
	        				loading = false;
	        				if(data.length < option.per_page) {
	        					$(element).unbind('scroll');
	        				}
	        				if(callback) callback();
	        				if(first) {
	        					$(element).bind('scroll', function(e) {
	        						var $this = $(this);
	        						if(e.target.scrollTop + e.target.clientHeight == e.target.scrollHeight) {
	        							if(!loading) {
	        								addData(false, function() {
	        									$this.animate({scrollTop: $this.scrollTop() + 50}, 500);
	        								});
	        							}
	        						}
	        					});
	        				}
	        			});
	        		}
	        	};
	        }
		};
	}]);
});