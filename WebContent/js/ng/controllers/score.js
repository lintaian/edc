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
			$parent.query.reportType.current = $parent.query.reportType.obj.CLASSAVG.value;
			if (reQuery || this.data == null) {
				var params = $parent.query.getParams();
				$parent.loader = {
					show: true,
					text: '统计中,请稍候...'
				}
				this.data = Count.classAvg(params, function(data) {
					$parent.loader.show = false;
					$timeout(function() {
//						$('#classAvg').fixedHeaderTable({ 
//							height: $parent.getTableHeight(),
//							altClass: 'odd', 
//							fixedColumn: true, 
//							fixedColumns: 1 
//						});
						Util.fixTable('classAvg', 1, {maxHeight: $parent.getTableHeight()});
					});
				});
			}
		}
	}
	$scope.detail = {
		data: null,
		init: function(reQuery) {
			$parent.query.reportType.current = $parent.query.reportType.obj.QUESTIONSCORE.value;
			if (reQuery || this.data == null) {
				var params = $parent.query.getParams();
				$parent.loader = {
					show: true,
					text: '统计中,请稍候...'
				}
				this.data = Count.questionScore(params, function(data) {
					$parent.loader.show = false;
					$scope.detail.more.hasMore = data.data.length < $scope.detail.more.per_page ? false : true;
					$timeout(function() {
//						$('#scoreDetail').fixedHeaderTable({ 
//							height: $parent.getTableHeight(),
//							altClass: 'odd', 
//							fixedColumn: true, 
//							fixedColumns: 3 
//						});
						Util.fixTable('scoreDetail', 3, {maxHeight: $parent.getTableHeight()});
					});
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
					Count.questionScorePage({page: this.page, per_page: this.per_page}, function(data) {
						$scope.detail.data.data = $scope.detail.data.data.concat(data);
						$scope.detail.more.hasMore = data.length < $scope.detail.more.per_page ? false : true;
						var ele = '';
						for (var i = 0; i < data.length; i++) {
							ele += '<tr>';
							for (var j = 0; j < data[i].length; j++) {
								if (j != 1 && j != 3) {
									ele += '<td>';
									ele += data[i][j];
									ele += '</td>';
								}
							}
							ele += '</tr>';
						}
						$timeout(function() {
							$('#scoreDetail_tableColumn table tbody tr:last').before(ele);
						});
					});
				}
			}
		}
	}
	$scope.num = {
			data: null,
			init: function(reQuery) {
				$parent.query.reportType.current = $parent.query.reportType.obj.ANSWERNUMBER.value;
				if (reQuery || this.data == null) {
					var params = $parent.query.getParams();
					$parent.loader = {
							show: true,
							text: '统计中,请稍候...'
					}
					this.data = Count.answerNum(params, function(data) {
						$parent.loader.show = false;
						$timeout(function() {
//							$('#answerNum').fixedHeaderTable({ 
//								height: $parent.getTableHeight(),
//								altClass: 'odd', 
//							});
							Util.fixTable('answerNum', 0, {maxHeight: $parent.getTableHeight()});
						});
					});
				}
			}
	}
	
	$scope.nav.change('avg', true);
}