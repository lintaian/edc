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
				Count.classPower(params, {}, function(data) {
					$scope.avg.data = data;
					$parent.loader.show = false;
					$scope.downloadDisable = false;
					$timeout(function() {
						Util.fixTable('classAvgPower', 1, {maxHeight: $parent.getTableHeight()});
					});
				}, function() {
					$parent.loader.show = false;
					$scope.downloadDisable = true;
					$parent.alert = {
						show: true,
						text: '获取数据失败!',
						title: '提示'
					}
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
				Count.studentPower(params, {}, function(data) {
					$scope.detail.data = data;
					$parent.loader.show = false;
					$scope.downloadDisable = false;
					$scope.detail.more.hasMore = data.data.length < $scope.detail.more.per_page ? false : true;
					$timeout(function() {
						Util.fixTable('scoreDetailPower', 3, {maxHeight: $parent.getTableHeight()});
					});
				}, function() {
					$parent.loader.show = false;
					$scope.downloadDisable = true;
					$parent.alert = {
						show: true,
						text: '获取数据失败!',
						title: '提示'
					}
				});
			}
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
					Count.studentPowerPage({id: $parent.query.examId, page: this.page, per_page: this.per_page}, function(data) {
						$scope.detail.data.data = $scope.detail.data.data.concat(data);
						$scope.detail.more.hasMore = data.length < $scope.detail.more.per_page ? false : true;
						var ele = '';
						for (var i = 0; i < data.length; i++) {
							ele += '<tr>';
							for (var j = 0; j < data[i].length; j++) {
								ele += '<td>';
								ele += data[i][j];
								ele += '</td>';
							}
							ele += '</tr>';
						}
						$timeout(function() {
							$('#scoreDetailPower_tableColumn table tbody tr:last').before(ele);
						});
						$parent.loader.show = false;
					}, function() {
						$parent.loader.show = false;
						$parent.alert = {
							show: true,
							text: '获取数据失败!',
							title: '提示'
						}
					});
				}
			}
		}
	}
	
	$scope.nav.change('avg', true);
}