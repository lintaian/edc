/*global require*/
'use strict';

require.config({
	paths: {
		jquery: '../lib/jquery-1.8.0.min',
		angular: '../lib/angular',
		bootstrap: '../lib/bootstrap',
		angularResource: '../lib/angular-resource',
		angularRoute: '../lib/angular-route',
		ngFileUpload: '../lib/angular-file-upload',
		fixed: '../lib/jquery.fixedheadertable.min',
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
		index: 'controllers/index',
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
		bootstrap: {deps: ['jquery']},
		util: {deps: ['jquery']},
		ngFileUpload: {deps: ['angular']},
		fixed: {deps: ['jquery']}
	}
});

require(['angular', 'angularResource', 'angularRoute', 'jquery', 'bootstrap',
         'filter', 'resource', 'util', 'dialog', 'loading', 'ngFileUpload', 'app', 
         'appCtrl', 'kp', 'score', 'capability', 'oa', 'index', 'fixed'], function (angular) {
	angular.bootstrap(document, ['mainApp']);
});
