define(['angular'], function(angular) {
	return angular.module('loading', []).directive('loading', [function() {
		return {
			restrict: 'AE',
	        require: 'ngModel',
	        priority: 0,
	        template: '<div data-ng-show="loading.show">' +
	        	      '<div class="modal" data-ng-show="loading.modal"></div>' +
	        	      '<div class="loader" data-ng-style="style">' +
	        	          '<img src="img/loader.gif" class="load_icon"/>' +
	        	          '<span class="text" data-ng-bind="loading.text"></span>' +
	        	      '</div>' +
	        	  '</div>',
	        scope: {
	            ngModel: "=ngModel"
	        },
	        replace: true,
	        link: function (scope, element, attrs) {
	        	scope.loading = {
        			show: false,
        			modal: true,
        			type: 'resource',
        			text: '加载中...'
	        	};
	        	var config = scope.$eval(attrs.config);
	        	angular.extend(scope.loading, config);
	        	scope.style = angular.extend({
	        		width: 300,
	        		height: 30
	        	}, config);
    			angular.extend(scope.style, {
    				left: (Util.getWinWidth() - scope.style.width) / 2,
    				top: (Util.getWinHeight() - scope.style.height) / 2 - 50
    			});
	        	var type = scope.loading.type;
        		if(type == 'resource') {
        			scope.$watch('ngModel.resource.$resolved', function() {
        				if(angular.isDefined(scope.ngModel)) {
        					if(scope.ngModel.$resolved == false) {
        						scope.loading.show = true;
        						scope.loading.text = scope.ngModel.text;
        					} else {
        						scope.loading.show = false;
        					}
        				};
        			});
        		} else if(type = 'bool') {
        			scope.$watch('ngModel', function() {
        				if (angular.isDefined(scope.ngModel)) {
        					scope.loading.show = scope.ngModel.show;
        					scope.loading.text = scope.ngModel.text;
						}
        			}, true);
        		}
	        }
		};
	}]);
});