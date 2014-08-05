function Capability($scope, Count, $timeout) {
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
			$parent.query.reportType.current = $parent.query.reportType.obj.CLASSPOWER.value;
			if (reQuery || this.data == null) {
				var params = {id: $parent.query.examId};
				$parent.loader = {
					show: true,
					text: '统计中,请稍候...'
				}
				this.data = Count.classPower(params, function(data) {
					$parent.loader.show = false;
					$timeout(function() {
						Util.fixTable('classAvgPower', 1, {maxHeight: $parent.getTableHeight()});
					});
				});
			}
		}
	}
	$scope.detail = {
		data: null,
		init: function(reQuery) {
			$parent.query.reportType.current = $parent.query.reportType.obj.STUDENTPOWER.value;
			if (reQuery || this.data == null) {
				var params = {id: $parent.query.examId};
				$parent.loader = {
					show: true,
					text: '统计中,请稍候...'
				}
				this.data = Count.studentPower(params, function(data) {
					$parent.loader.show = false;
					$timeout(function() {
						Util.fixTable('scoreDetailPower', 3, {maxHeight: $parent.getTableHeight()});
					});
				});
			}
		}
	}
	
	$scope.nav.change('avg', true);
}