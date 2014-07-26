define(['angular', 'jquery'], function(angular, $) {
	return angular.module('scrollLoading', []).directive('scrollLoading', ['Source', function(Source) {
		return {
			restrict: 'AE',
	        priority: 1,
	        link: function (scope, element, attrs) {
	        	var config = scope.$eval(attrs.scrollLoadingConfig);
	        	var option = {
        			page: 1,
        			per_page: 15,
        			method: 'get'
	        	};
	        	angular.extend(option, config);
	        	scope[option.data] = [];
	        	var loading = false;
	        	addData(true);
	        	function addData(first, callback) {
	        		if(option.service == 'source') {
	        			loading = true;
	        			scope[option.data].push(Source[option.method]({page: option.page, per_page: option.per_page}, function(data) {
	        				option.page++;
	        				loading = false;
	        				if(data.content.length < option.per_page) {
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
	        			}));
	        		}
	        	};
	        }
		};
	}]);
});