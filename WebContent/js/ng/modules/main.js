/*global define*/
'use strict';

define(['angular'], function (angular) {
	return angular.module('mainApp', ['myFilters', 'resource', 'ngRoute', 'dialog', 'loading', 'angularFileUpload']).
	  config(['$routeProvider', function($routeProvider) {
		  $routeProvider.
		      when('/', {templateUrl: 'tpl/index.html'}).
		      when('/type/:type', {templateUrl: 'tpl/index.html', controller: Index}).
		      when('/score', {templateUrl: 'tpl/score.html', controller: Score}).
		      when('/knowledgePoint', {templateUrl: 'tpl/knowledgePoint.html', controller: KnowledgePoint}).
		      when('/capability', {templateUrl: 'tpl/capability.html', controller: Capability}).
		      when('/originalAnswer', {templateUrl: 'tpl/originalAnswer.html', controller: OriginalAnswer}).
		      otherwise({redirectTo: '/'});
		}]);
});
