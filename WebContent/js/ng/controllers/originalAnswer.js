function OriginalAnswer($scope, Count, $timeout, Question) {
	var $parent = $scope.$parent;
	$scope.answer = {
		data: null,
		init: function() {
			$parent.query.reportType.current = $parent.query.reportType.obj.ORIGIALANSWER.value;
			var params = $parent.query.getParams();
			$parent.loader = {
				show: true,
				text: '统计中,请稍候...'
			}
			this.data = Count.originalAnswer(params, function(data) {
				$parent.loader.show = false;
				$scope.answer.more.hasMore = data.data.length < $scope.answer.more.per_page ? false : true;
				$timeout(function() {
					Util.fixTable('originalAnswer', 3, {maxHeight: $parent.getTableHeight()});
				});
			});
		},
		more: {
			page: 1,
			per_page: 100,
			hasMore: false,
			exec: function() {
				if (this.hasMore) {
					this.page++;
					$parent.loader = {
						show: true,
						text: '加载中,请稍候...'
					}
					Count.originalAnswerPage({page: this.page, per_page: this.per_page}, function(data) {
						$scope.answer.data.data = $scope.answer.data.data.concat(data);
						$scope.answer.more.hasMore = data.length < $scope.answer.more.per_page ? false : true;
						var ele = '';
						for (var i = 0; i < data.length; i++) {
							ele += '<tr>';
							for (var j = 0; j < data[i].length; j++) {
								if (j != 1 && j != 3 && j != 5) {
									ele += '<td>';
									ele += data[i][j].value;
									ele += '</td>';
								}
							}
							ele += '</tr>';
						}
						$timeout(function() {
							$('#originalAnswer_tableColumn table tbody tr:last').before(ele);
						});
						$parent.loader.show = false;
					});
				}
			}
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