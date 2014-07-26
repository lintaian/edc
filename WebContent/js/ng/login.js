/*global require*/
'use strict';

require.config({
	paths: {
		jquery: '../lib/jquery',
		angular: '../lib/angular',
		bootstrap: '../lib/bootstrap',
		angularResource: '../lib/angular-resource',
		angularRoute: '../lib/angular-route',
		util: '../plugin/util',
		resource: 'services/resource',
		filter: 'filters/filters',
		app: 'modules/login',
		appCtrl: 'controllers/login'
	},
	shim: {
		angular: {
			exports: 'angular'
		},
		angularResource: {deps: ['angular']},
		angularRoute: {deps: ['angular']},
		bootstrap: {deps: ['jquery']}
	}
});

require(['angular', 'angularResource', 'angularRoute', 'jquery', 'bootstrap',
         'util', 'resource', 'filter', 
         'app', 'appCtrl'], function (angular) {
	angular.bootstrap(document, ['loginApp']);
});
