define(['jquery', 'angular'], function($, angular) {
	return angular.module('dialog', []).directive('confirm', [function() {
		return {
			restrict: 'AE',
	        require: 'ngModel',
	        template: "<div data-ng-show=\"confirm.show\"> " +
	        		"<div class=\"Pane_shade\" data-ng-show=\"confirm.modal\"></div>"
	        		+ "<div class=\"Pane_layer confirmBody\" style=\"width:340px;height:230px;\"" +
	        				"data-ng-style=\"style\">"
	        		+ "<h2 data-ng-bind=\"confirm.title\"></h2>"
	        		+ "<div class=\"alert_con\">"	
	        		+ "<center>"
	        		+ "<p data-ng-bind=\"confirm.text\"></p>"
	        		+ "<p><input class=\"confirm Btn\" type=\"button\" value=\"确定\"" +
	        				"data-ng-click=\"confirm.ok()\">"
	        		+ "<input class=\"cancel Btn\"  type=\"button\" value=\"取消\"" +
	        				"data-ng-click=\"confirm.cancel()\"></p>"       
	        		+ "</center>"	
	        		+ "</div>" 	
	        		+ "<input type=\"button\" class=\"close_layer\" value=\"close\"" +
	        				"data-ng-click=\"confirm.close()\">"
	        		+ "</div></div>",
	        scope: {
	            ngModel: "=ngModel"
	        },
	        replace: true,
	        link: function (scope, element, attrs) {
	        	scope.confirm = {
        			show: false,
        			modal: true,
        			useEsc: true,
        			title: '请注意',
        			text: '',
        			ok: function() {
        				this.yes();
        				scope.ngModel.show = false;
        			},
        			cancel: function() {
        				this.no();
        				scope.ngModel.show = false;
        			},
        			close: function() {
        				scope.ngModel.show = false;
        			},
        			yes: function() {},
        			no: function() {}
	        	};
	        	scope.$watch('ngModel', function() {
	        		angular.extend(scope.confirm, scope.ngModel);
	        		if(scope.confirm.show) {
	        			var width = $('.confirmBody').width();
	        			var height = $('.confirmBody').height();
	        			scope.style = {
	        				left: (Util.getWinWidth() - width) / 2,
	        				top: (Util.getWinHeight() - height) / 2
	        			};
	        			if(scope.confirm.useEsc) {
	        				$(window).bind('keydown', function(event){
	        					if(event.keyCode == 27) {
	        						scope.ngModel.show = false;
	        						scope.$apply();
	        					}
	        				});
	        			}
	        		} else {
	        			if(scope.confirm.useEsc) {
	        				$(window).unbind('keydown');
	        			}
	        		}
	        	}, true);
	        	
	        }
		};
	}]).directive('alert', [function() {
		return {
			restrict: 'AE',
	        require: 'ngModel',
	        template: "<div data-ng-show=\"alert.show\"> " +
	        		"<div class=\"Pane_shade\" data-ng-show=\"alert.modal\"></div>"
	        		+ "<div class=\"Pane_layer alertBody\" data-ng-style=\"style\"" +
	        				"style=\"width:340px;height:230px;\">"
	        		+ "<h2 data-ng-bind=\"alert.title\"></h2>"
	        		+ "<div class=\"alert_con\">"	
	        		+ "<center>"
	        		+ "<p data-ng-bind=\"alert.text\"></p>"
	        		+ "<p><input class=\"confirm Btn\" type=\"button\" value=\"确定\"" +
	        				"data-ng-click=\"alert.ok()\"></p>"       
	        		+ "</center>"	
	        		+ "</div>" 	
	        		+ "<input type=\"button\" class=\"close_layer\" value=\"close\"" +
	        				"data-ng-click=\"alert.close()\">"
	        		+ "</div></div>",
	        scope: {
	            ngModel: "=ngModel"
	        },
	        replace: true,
	        link: function (scope, element, attrs) {
	        	scope.alert = {
        			show: false,
        			modal: true,
        			useEsc: true,
        			title: '请注意',
        			text: '',
        			ok: function() {
        				scope.ngModel.show = false;
        			},
        			close: function() {
        				scope.ngModel.show = false;
        			}
	        	};
	        	scope.$watch('ngModel', function() {
	        		angular.extend(scope.alert, scope.ngModel);
	        		if(scope.alert.show) {
	        			var width = $('.alertBody').width();
	        			var height = $('.alertBody').height();
	        			scope.style = {
	        				left: (Util.getWinWidth() - width) / 2,
	        				top: (Util.getWinHeight() - height) / 2
	        			};
	        			if(scope.alert.useEsc) {
	        				$(window).bind('keydown', function(event){
	        					if(event.keyCode == 27) {
	        						scope.ngModel.show = false;
	        						scope.$apply();
	        					}
	        				});
	        			}
	        		} else {
	        			if(scope.alert.useEsc) {
	        				$(window).unbind('keydown');
	        			}
	        		}
	        	}, true);
	        }
		};
	}]).directive('dialog', [function() {
		return {
			restrict: 'AE',
	        require: 'ngModel',
	        template: "<div data-ng-show=\"dialog.show\"> " +
	        		"<div class=\"Pane_shade\" data-ng-show=\"dialog.modal\"></div>"
	        		+ "<div class=\"Pane_layer dialogBody\" data-ng-style=\"style\">"
	        		+ "<h2 data-ng-bind=\"dialog.title\"></h2>"
	        		+ "<div data-ng-transclude></div>"
	        		+ "<input type=\"button\" class=\"close_layer\" value=\"close\"" +
	        				"data-ng-click=\"dialog.close()\">"
	        		+ "</div></div>",
	        scope: {
	            ngModel: "=ngModel"
	        },
	        transclude: true,
	        replace: true,
	        link: function (scope, element, attrs) {
	        	scope.dialog = {
        			show: false,
        			modal: true,
        			useEsc: true,
        			title: '',
        			close: function() {
        				scope.ngModel.show = false;
        			}
	        	};
	        	var config = scope.$eval(attrs.config);
	        	if(!config.width) {
	        		throw 'you need config the width';
	        	}
	        	if(!config.height) {
	        		throw 'you need config the height';
	        	}
	        	scope.style = {};
	        	angular.extend(scope.style, config);
	        	scope.$watch('ngModel', function() {
	        		angular.extend(scope.dialog, scope.ngModel);
	        		if(scope.dialog.show) {
	        			var width = config.width ? config.width : $('.dialogBody').width();
	        			var height = config.height ? config.height : $('.dialogBody').height();
	        			angular.extend(scope.style, {
	        				left: (Util.getWinWidth() - width) / 2,
	        				top: (Util.getWinHeight() - height) / 2
	        			});
	        			if(scope.dialog.useEsc) {
	        				$(window).bind('keydown', function(event){
	        					if(event.keyCode == 27) {
	        						scope.ngModel.show = false;
	        						scope.$apply();
	        					}
	        				});
	        			}
	        		} else {
	        			if(scope.dialog.useEsc) {
	        				$(window).unbind('keydown');
	        			}
	        		}
	        	}, true);
	        }
		};
	}]);
});