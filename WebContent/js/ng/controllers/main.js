function Main($scope, $rootScope, BaseData, Question, Count) {
	$scope.dialog = function() {
		$scope.dialog = {show: true};
	};
	$scope.loading = function() {
		$scope.loader = {
			show: true,
			text: 'test...'
		};
	};
	$scope.query = {
		gradeId: '',
		examId: '',
		examNo: '',
		studentName: '',
		classList: [],
		questionList: [],
		type: 'score',
		action: function() {
			
		}
	};
	$scope.count = {
		type: false,
		action: function() {
			var type = $scope.count.type ? 1 : 0;
			Count.countScore({examId: $scope.query.examId, type: type}, function() {
				
			});
		}
	};
	
	$scope.school = {
		list: BaseData.getSchools(function(data) {
			if (data.length > 0) {
				var schoolId = data[0].id;
				$scope.school.change(schoolId);
			}
		}),
		value: '',
		change: function(schoolId) {
			$scope.grade.list = BaseData.getGrades({id: schoolId}, function(data) {
				if (data.length > 0) {
					$scope.grade.change(data[0].id);
				}
			});
			$scope.batch.list = BaseData.getBatches({id: schoolId}, function(data) {
				if (data.length > 0) {
					$scope.batch.change(data[0].id);
				}
			});
		}
	};
	$scope.grade = {
		list: null,
		value: '',
		change: function(gradeId) {
			$scope.query.greadeId = gradeId;
			BaseData.getClasses({id: gradeId}, function(data) {
				console.log(data);
				for (var i = 0; i < data.length; i++) {
					data[i].checked = true;
				}
				$scope.classes.list = data;
				$scope.classes.checkAll = true;
			});
		}
	};
	$scope.batch = {
		list: null,
		value: '',
		change: function(batchId) {
			$scope.subject.list = BaseData.getSubjects({id: batchId}, function(data) {
				if (data.length > 0) {
					$scope.subject.change(data[0].id);
				}
			});
		}
	};
	$scope.subject = {
		list: null,
		value: '',
		state: '',
		change: function(subjectId) {
			$scope.query.examId = subjectId;
			 Question.query({id: subjectId}, function(data) {
				 for (var i = 0; i < data.length; i++) {
					 data[i].checked = true;
					 for (var j = 0; j < data[i].child.length; j++) {
						data[i].child[j].checked = true;
					}
				 }
				 $scope.question.list = data;
				 $scope.question.checkAll = true;
			});
			$scope.subject.setState(subjectId);
		},
		setState: function(subjectId) {
			var data = $scope.subject.list;
			for (var i = 0; i < data.length; i++) {
				if (data[i].id == subjectId) {
					$scope.subject.state = data[i].state;
					break;
				}
			}
		},
		setStateReal: function() {
			var examId = $scope.query.examId;
			var state = $scope.subject.state;
			BaseData.updateSubjectState({examId: examId, state: state}, function() {
				var data = $scope.subject.list;
				for (var i = 0; i < data.length; i++) {
					if (data[i].id == examId) {
						data[i].state = state;
						break;
					}
				}
			});
		},
		getState: function() {
			var examId = $scope.query.examId;
			var data = $scope.subject.list;
			if (angular.isDefined(data) && data != null) {
				for (var i = 0; i < data.length; i++) {
					if (data[i].id == examId) {
						return data[i].state;
					}
				}
			}
			return '';
		}
	};
	$scope.classes = {
		list: null,
		checkAllFn: function(checked) {
			var data = $scope.classes.list;
			for (var i = 0; i < data.length; i++) {
				data[i].checked = checked;
			}
		}
	};
	$scope.question = {
		list: null,
		checkAllFn: function(checked) {
			var data = $scope.question.list;
			 for (var i = 0; i < data.length; i++) {
				 data[i].checked = checked;
				 for (var j = 0; j < data[i].child.length; j++) {
					data[i].child[j].checked = checked;
				}
			 }
		}
	};
	
	
	$(function() {
		resize();
		$(window).resize(function() {
			resize();
		});
	});
	function resize() {
		var height = Util.getWinHeight();
		if (height < 600) {
			height = 600;
		}
		$('#report').height(height - 122);
		$('#question').height(height - 324);
		var width = Util.getWinWidth();
		if (width < 1024) {
			width = 1024;
		}
		$('body').width(width);
	} 
}
