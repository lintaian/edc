function KnowledgePoint($scope, Count, $timeout) {
	var $parent = $scope.$parent;
	$scope.nav = {
		current: '',
		change: function(str, reQuery) {
			this.current = str;
			$scope[str].init(reQuery);
		}
	}
	$scope.avg = {
		data: null,
		init: function(reQuery) {
			$parent.query.reportType.current = $parent.query.reportType.obj.CLASSKNOWLEDGE.value;
			if (reQuery || this.data == null) {
				var params = {id: $parent.query.examId};
				$parent.loader = {
					show: true,
					text: '统计中...'
				}
				this.data = Count.classKnowledge(params, function(data) {
					$parent.loader.show = false;
					$timeout(function() {
						Util.fixTable('classAvgKnowledge', 1, {maxHeight: $parent.getTableHeight()});
					});
				});
			}
		}
	}
	$scope.detail = {
		data: null,
		init: function(reQuery) {
			$parent.query.reportType.current = $parent.query.reportType.obj.STUDENTKNOWLEDGE.value;
			if (reQuery || this.data == null) {
				var params = {id: $parent.query.examId};
				$parent.loader = {
					show: true,
					text: '统计中...'
				}
				this.data = Count.studentKnowledge(params, function(data) {
					$parent.loader.show = false;
					$timeout(function() {
						Util.fixTable('scoreDetailKnowledge', 3, {maxHeight: $parent.getTableHeight()});
					});
				});
			}
		}
	}
	
	$scope.nav.change('avg', true);
}