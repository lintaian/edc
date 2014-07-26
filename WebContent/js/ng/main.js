/*global require*/
'use strict';

require.config({
	paths: {
		jquery: '../lib/jquery',
		angular: '../lib/angular',
		bootstrap: '../lib/bootstrap',
		angularResource: '../lib/angular-resource',
		angularRoute: '../lib/angular-route',
		filter: 'filters/filters',
		resource: 'services/resource',
		dialog: 'directives/dialog',
		loading: 'directives/loading',
		util: '../plugin/util',
		app: 'modules/main',
		appCtrl: 'controllers/main',
		kp: 'controllers/knowledgePoint',
		score: 'controllers/score',
		capability: 'controllers/capability',
		oa: 'controllers/originalAnswer'
	},
	shim: {
		angular: {
			exports: 'angular'
		},
		angularResource: {deps: ['angular']},
		angularRoute: {deps: ['angular']},
		dialog: {deps: ['jquery', 'angular']},
		loading: {deps: ['angular']},
		bootstrap: {deps: ['jquery']}
	}
});

require(['angular', 'angularResource', 'angularRoute', 'jquery', 'bootstrap',
         'filter', 'resource', 'util', 'dialog', 'loading', 'app', 
         'appCtrl', 'kp', 'score', 'capability', 'oa'], function (angular) {
	angular.bootstrap(document, ['mainApp']);
});
