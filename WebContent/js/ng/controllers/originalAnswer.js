function OriginalAnswer($scope, Count, $timeout, Question) {
	var $parent = $scope.$parent;
	$scope.answer = {
		data: null,
		init: function() {
			$parent.query.reportType.current = $parent.query.reportType.obj.ORIGIALANSWER.value;
			var params = $parent.query.getParams();
			$parent.loader = {
				show: true,
				text: '统计中...'
			}
			this.data = Count.originalAnswer(params, function(data) {
				$parent.loader.show = false;
				$timeout(function() {
					Util.fixTable('originalAnswer', 3, {maxHeight: $parent.getTableHeight()});
				});
			});
		},
		update: {
			value: '',
			imageId: '',
			questionName: '',
			examId: '',
			index: 0,
			index2: 0,
			open: function(value, index, index2) {
				this.imageId = $scope.answer.data.data[index][5].value;
				this.value = value;
				this.examId = $parent.query.examId;
				this.questionName = $scope.answer.data.title[index2];
				this.index = index;
				this.index2 = index2;
				$scope.updateQuestionAnswer = {
					show: true,
					title: '修改答案或分数'
				}
			},
			exec: function() {
				var $this = this;
				Question.updateAnswer(this.getParams(), function(data) {
					$scope.updateQuestionAnswer.show = false;
					$scope.answer.data.data[$this.index][$this.index2].value = $this.value;
				}, function() {
					$scope.updateQuestionAnswer.show = false;
				});
			},
			cancel: function() {
				$scope.updateQuestionAnswer.show = false;
			},
			getParams: function() {
				return {
					imageId: this.imageId,
					questionName: this.questionName,
					examId: this.examId,
					result: this.value
				};
			}
		},
		image: {
			id: '',
			examId: '',
			questionName: '',
			open: function(index, index2) {
				this.id = $scope.answer.data.data[index][5].value;
				this.examId = $parent.query.examId;
				this.questionName = $scope.answer.data.title[index2];
				$scope.imageFloat = {
					show: true
				}
			}
		}
	}
	$scope.answer.init();
}