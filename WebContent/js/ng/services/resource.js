/*global define*/
'use strict';

define(['angular'], function(angular) {
	return angular.module('resource', ['ngResource']).constant('cfg', {
		baseUrl: ''
	}).factory('User', ['$resource', 'cfg', function($resource, cfg) {
		return $resource(cfg.baseUrl + 'login',{},{
			'login' : {method: 'POST'},
			'report': {method: 'POST', url: cfg.baseUrl + 'report'},
			'reportStatus': {method: 'GET', url: cfg.baseUrl + 'report/status'},
		});
	}]).factory('BaseData', ['$resource', 'cfg', function($resource, cfg) {
		return $resource(cfg.baseUrl + 'batch/:id',{},{
			'getSchools' : {method: 'GET', url: cfg.baseUrl + 'school', isArray: true},
			'getGrades' : {method: 'GET', url: cfg.baseUrl + 'grade/school/:id', isArray: true},
			'getBatches' : {method: 'GET', url: cfg.baseUrl + 'batch/school/:id', isArray: true},
			'getSubjects' : {method: 'GET', url: cfg.baseUrl + 'subject/batch/:id', isArray: true},
			'getClasses' : {method: 'GET', url: cfg.baseUrl + 'class/grade/:id', isArray: true},
			'updateSubjectState':  {method: 'PUT', url: cfg.baseUrl + "subject"}
		});
	}]).factory('Question', ['$resource', 'cfg', function($resource, cfg) {
		return $resource(cfg.baseUrl + 'question/:id',{},{
			'update' : {method: 'PUT'},
			'updateAnswer' : {method: 'PUT', url: cfg.baseUrl + 'question/answer'},
			'query' : {method: 'GET', url: cfg.baseUrl + 'question/exam/:id', isArray: true}
		});
	}]).factory('Student', ['$resource', 'cfg', function($resource, cfg) {
		return $resource(cfg.baseUrl + 'student/:id',{},{
			'query' : {method: 'GET', url: cfg.baseUrl + 'student/exam/:id',isArray: true},
			'queryAllExceptAbs' : {method: 'GET', url: cfg.baseUrl + 'student/queryAllExceptAbs/:gId/:eId',isArray: true}
		});
	}]).factory('Standard', ['$resource', 'cfg', function($resource, cfg) {
		return $resource(cfg.baseUrl + 'standard/:id',{},{
			'update' : {method: 'PUT'},
			'del' : {method: 'DELETE', url: cfg.baseUrl + 'standard/:ids'},
			'query' : {method: 'GET', url: cfg.baseUrl + 'standard/:id', isArray: true},
			'querySubjects' : {method: 'GET', url: cfg.baseUrl + 'standard/subjects', isArray: true},
			'querySubjectTypes' : {method: 'GET', url: cfg.baseUrl + 'standard/subjectTypes', isArray: true},
			'queryStandardTypes' : {method: 'GET', url: cfg.baseUrl + 'standard/standardTypes', isArray: true}
		});
	}]).factory('Count', ['$resource', 'cfg', function($resource, cfg) {
		return $resource(cfg.baseUrl + 'count/:id',{},{
			'countScore' : {method: 'POST', url: cfg.baseUrl + 'count/countScore'},
			'answerNum' : {method: 'POST', url: cfg.baseUrl + 'count/answerNum'},
			'classAvg' : {method: 'POST', url: cfg.baseUrl + 'count/classAvg'},
			'originalAnswer' : {method: 'POST', url: cfg.baseUrl + 'count/originalAnswer'},
			'originalAnswerPage' : {method: 'GET', url: cfg.baseUrl + 'count/originalAnswer', isArray: true},
			'questionScore' : {method: 'POST', url: cfg.baseUrl + 'count/questionScore'},
			'questionScorePage' : {method: 'GET', url: cfg.baseUrl + 'count/questionScore', isArray: true},
			'classKnowledge' : {method: 'POST', url: cfg.baseUrl + 'count/classKnowledge/:id'},
			'classPower' : {method: 'POST', url: cfg.baseUrl + 'count/classPower/:id'},
			'studentKnowledge' : {method: 'POST', url: cfg.baseUrl + 'count/studentKnowledge/:id'},
			'studentKnowledgePage' : {method: 'GET', url: cfg.baseUrl + 'count/studentKnowledge/:id', isArray: true},
			'studentPower' : {method: 'POST', url: cfg.baseUrl + 'count/studentPower/:id'},
			'studentPowerPage' : {method: 'GET', url: cfg.baseUrl + 'count/studentPower/:id', isArray: true}
		});
	}]).factory('StatisticalCategory', ['$resource', 'cfg', function($resource, cfg) {
		return $resource(cfg.baseUrl + 'statisticalCategory/:id',{},{});
	}]);
});