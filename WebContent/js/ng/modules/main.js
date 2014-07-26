/*global define*/
'use strict';

define(['angular'], function (angular) {
	return angular.module('mainApp', ['myFilters', 'resource', 'ngRoute', 'dialog', 'loading']).
	  config(['$routeProvider', function($routeProvider) {
		  $routeProvider.
		      when('/', {templateUrl: 'tpl/index.html'}).
		      when('/score', {templateUrl: 'tpl/score.html', controller: Score}).
		      when('/knowledgePoint', {templateUrl: 'tpl/knowledgePoint.html', controller: KnowledgePoint}).
		      when('/capability', {templateUrl: 'tpl/capability.html', controller: Capability}).
		      when('/originalAnswer', {templateUrl: 'tpl/originalAnswer.html', controller: OriginalAnswer}).
		      otherwise({redirectTo: '/'});
		}]);
});
