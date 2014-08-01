function Score($scope, Count, $timeout) {
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
			if (reQuery || this.data == null) {
				var params = $parent.query.getParams();
				$parent.loader = {
					show: true,
					text: '统计中...'
				}
				this.data = Count.classAvg(params, function(data) {
					$parent.loader.show = false;
					$timeout(function() {
						Util.fixTable('classAvg', 1, {maxHeight: $parent.getTableHeight()});
					});
				});
			}
		}
	}
	$scope.detail = {
		data: null,
		init: function(reQuery) {
			if (reQuery || this.data == null) {
				var params = $parent.query.getParams();
				$parent.loader = {
					show: true,
					text: '统计中...'
				}
				this.data = Count.questionScore(params, function(data) {
					$parent.loader.show = false;
					$timeout(function() {
						Util.fixTable('scoreDetail', 3, {maxHeight: $parent.getTableHeight()});
					});
				});
			}
		}
	}
	$scope.num = {
			data: null,
			init: function(reQuery) {
				if (reQuery || this.data == null) {
					var params = $parent.query.getParams();
					$parent.loader = {
							show: true,
							text: '统计中...'
					}
					this.data = Count.answerNum(params, function(data) {
						$parent.loader.show = false;
						$timeout(function() {
							Util.fixTable('answerNum', 0, {maxHeight: $parent.getTableHeight()});
						});
					});
				}
			}
	}
	
	$scope.nav.change('avg', true);
}