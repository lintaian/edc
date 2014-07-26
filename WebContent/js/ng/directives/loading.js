define(['angular'], function(angular) {
	return angular.module('loading', []).directive('loading', [function() {
		return {
			restrict: 'AE',
	        require: 'ngModel',
	        priority: 0,
	        template: "<div data-ng-show=\"loading.show\"> " +
	        		"<div class=\"Pane_shade\" data-ng-show=\"loading.modal\"></div>" + 
	        		"<div class=\"Pane_layer dialogBody row-fluid\" data-ng-style=\"style\">" +
	        		"<div class='span3'>" +
	        		"<img src=\"img/loader.gif\" class=\"load_icon\"/>" +
	        		"</div><div class='span8'>" +
	        		"<h4 data-ng-bind='loading.text' style='margin-left: 50px;'></h4>" +
	        		"</div></div></div>",
	        scope: {
	            ngModel: "=ngModel"
	        },
	        replace: true,
	        link: function (scope, element, attrs) {
	        	scope.loading = {
        			show: false,
        			modal: false,
        			type: 'resource',
        			text: '加载中...'
	        	};
	        	var config = scope.$eval(attrs.config);
	        	angular.extend(scope.loading, config);
	        	scope.style = {
	        		width: 300,
	        		height: 30
	        	};
	        	var width = 300;
    			var height = 50;
    			angular.extend(scope.style, {
    				left: (Util.getWinWidth() - width) / 2,
    				top: (Util.getWinHeight() - height) / 2
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
        			});
        		}
	        }
		};
	}]);
});