define(['jquery', 'angular'], function($, angular) {
	return angular.module('dialog', []).directive('confirm', [function() {
		return {
			restrict: 'AE',
	        require: 'ngModel',
	        template: '<div data-ng-show="confirm.show">' +
	        	      '<div class="modal modal-confirm" data-ng-show="confirm.modal"></div>' +
	        	      '<div id="confirm" class="dialog" data-ng-style="style">' +
	        	          '<div class="title">' +
	        	              '<span class="text" data-ng-bind="confirm.title"></span>' +
	        	              '<i class="icon-remove close-btn" data-ng-click="confirm.close()"></i>' +
	        	          '</div>' +
	        	          '<div class="content">' +
	        	              '<span data-ng-bind="confirm.text"></span>' +
	        	          '</div>' +
	        	          '<div class="bottom">' +
	        	              '<button class="btn btn-info" data-ng-click="confirm.ok()">确定</button>' +
	        	              '<button class="btn" data-ng-click="confirm.cancel()">取消</button>' +
	        	          '</div>' +
	        	      '</div>' +
	        	  '</div>',
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
	        			var width = $('#confirm').width();
	        			var height = $('#confirm').height();
	        			scope.style = {
	        				left: (Util.getWinWidth() - width) / 2,
	        				top: (Util.getWinHeight() - height) / 2 - 50
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
	        template:'<div data-ng-show="alert.show">' +
	        	      '<div class="modal modal-alert" data-ng-show="alert.modal"></div>' +
	        	      '<div id="alert" class="dialog" data-ng-style="style">' +
	        	          '<div class="title">' +
	        	              '<span class="text" data-ng-bind="alert.title"></span>' +
	        	              '<i class="icon-remove close-btn" data-ng-click="alert.close()"></i>' +
	        	          '</div>' +
	        	          '<div class="content">' +
	        	              '<span data-ng-bind="alert.text"></span>' +
	        	          '</div>' +
	        	          '<div class="bottom">' +
	        	              '<button class="btn btn-info" data-ng-click="alert.ok()">确定</button>' +
	        	          '</div>' +
	        	      '</div>' +
	        	  '</div>',
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
	        			var width = $('#alert').width();
	        			var height = $('#alert').height();
	        			scope.style = {
	        				left: (Util.getWinWidth() - width) / 2,
	        				top: (Util.getWinHeight() - height) / 2 - 50
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
	        template: '<div data-ng-show="dialog.show">' +
	        	      '<div class="modal" data-ng-show="dialog.modal"></div>' +
	        	      '<div id="dialog" class="dialog" data-ng-style="style">' +
	        	          '<div class="title">' +
	        	              '<span class="text" data-ng-bind="dialog.title"></span>' +
	        	              '<i class="icon-remove close-btn" data-ng-click="dialog.close()"></i>' +
	        	          '</div>' +
	        	          '<div class="content-dialog">' +
	        	              '<div data-ng-transclude></div>' +
	        	          '</div>' +
	        	      '</div>' +
	        	  '</div>',
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
//	        	if(!config.width) {
//	        		config.width = 500;
//	        		throw 'you need config the width';
//	        	}
//	        	if(!config.height) {
//	        		config.height = 300;
//	        		throw 'you need config the height';
//	        	}
	        	scope.style = {};
	        	angular.extend(scope.style, config);
	        	scope.$watch('ngModel', function() {
	        		angular.extend(scope.dialog, scope.ngModel);
	        		if(scope.dialog.show) {
	        			var width = config.width ? config.width : $('#dialog').width();
	        			var height = config.height ? config.height : $('#dialog').height();
	        			angular.extend(scope.style, {
	        				left: (Util.getWinWidth() - width) / 2,
	        				top: (Util.getWinHeight() - height) / 2 - 50
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