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
					text: '统计中,请稍候...'
				}
				this.data = Count.classKnowledge(params, {}, function(data) {
					$parent.loader.show = false;
					$timeout(function() {
//						$('#classAvgKnowledge').fixedHeaderTable({ 
//							height: $parent.getTableHeight(),
//							altClass: 'odd', 
//							fixedColumn: true, 
//							fixedColumns: 1 
//						});
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
					text: '统计中,请稍候...'
				}
				this.data = Count.studentKnowledge(params, {}, function(data) {
					$parent.loader.show = false;
					$scope.detail.more.hasMore = data.data.length < $scope.detail.more.per_page ? false : true;
					$timeout(function() {
//						$('#scoreDetailKnowledge').fixedHeaderTable({ 
//							height: $parent.getTableHeight(),
//							altClass: 'odd', 
//							fixedColumn: true, 
//							fixedColumns: 3 
//						});
						Util.fixTable('scoreDetailKnowledge', 3, {maxHeight: $parent.getTableHeight()});
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
					Count.studentKnowledgePage({id: $parent.query.examId, page: this.page, per_page: this.per_page}, function(data) {
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
							$('#scoreDetailKnowledge_tableColumn table tbody tr:last').before(ele);
						});
					});
				}
			}
		}
	}
	
	$scope.nav.change('avg', true);
}