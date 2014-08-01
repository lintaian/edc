function OriginalAnswer($scope, Count, $timeout) {
	var $parent = $scope.$parent;
	$scope.answer = {
		data: null,
		init: function() {
			var params = $parent.query.getParams();
			$parent.loader = {
				show: true,
				text: '统计中...'
			}
			this.data = Count.originalAnswer(params, function(data) {
				$parent.loader.show = false;
				$timeout(function() {
					Util.fixTable('originalAnswer', 3, {maxHeight: $parent.getTableHeight()+50});
				});
			});
		}
	}
	$scope.answer.init();
}