function Main($scope, $rootScope, BaseData, Question, Count, Student, $timeout, FileUploader, $location) {
	if (angular.isUndefined($scope.query) && ($location.path() != '' || $location.path() != '/')) {
		window.location.href = 'main#/';
	}
	$scope.query = {
		gradeId: '',
		examId: '',
		examNo: '',
		studentName: '',
		classList: [],
		questionList: [],
		type: 'score',
		exec: function() {
			window.location.href = 'main#/' + this.type;
		},
		getParams: function() {
			var params = {
				gradeId: this.gradeId,
				examId: this.examId,
				examNo: this.examNo,
				studentName: this.studentName
			};
			var classList = [];
			var list = $scope.classes.list;
			for (var i = 0; i < list.length; i++) {
				if (list[i].checked) {
					classList.push({
						id: list[i].id,
						name: list[i].name
					});
				}
			}
			var questionList = [];
			list = $scope.question.list;
			for (var i = 0; i < list.length; i++) {
				if (list[i].checked) {
					questionList.push({
						id: list[i].id,
						name: list[i].name
					});
				}
				for (var j = 0; j < list[i].child.length; j++) {
					if (list[i].child[j].checked) {
						questionList.push({
							id: list[i].child[j].id,
							name: list[i].child[j].name
						});
					}
				}
			}
			params.classList = classList;
			params.questionList = questionList;
			return params;
		}
	};
	$scope.count = {
		type: false,
		exec: function() {
			var type = $scope.count.type ? 1 : 0;
			$scope.loader = {
				show: true,
				text: '统计中...'
			};
			Count.countScore({examId: $scope.query.examId, type: type}, function() {
				$scope.loader.show = false;
				$scope.alert = {
					show: true,
					text: '统计成功!',
					title: '通知'
				};
			}, function() {
				$scope.loader.show = false;
				$scope.alert = {
					show: true,
					text: '统计失败!',
					title: '通知'
				};
			});
		}
	};
	$scope.school = {
		list: null,
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
		},
		init: function() {
			BaseData.getSchools(function(data) {
				if (data.length > 0) {
					var schoolId = data[0].id;
					$scope.school.change(schoolId);
				}
				$scope.school.list = data;
			});
		}
	};
	$scope.school.init();
	$scope.grade = {
		list: null,
		value: '',
		change: function(gradeId) {
			$scope.query.gradeId = gradeId;
			BaseData.getClasses({id: gradeId}, function(data) {
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
		},
		update: {
			obj: null,
			to: function(question) {
				$scope.updateQuestion = {
					show: true,
					title: '修改试题'
				};
				this.obj = question;
			},
			exec: function() {
				var param = {
					name: this.obj.name,
					order: this.obj.order
				};
				var $this = this;
				Question.update({id: this.obj.id}, param, function() {
					$scope.updateQuestion.show = false;
					var data = $scope.question.list;
					for (var i = 0; i < data.length; i++) {
						if (data[i].id == $this.obj.id) {
							data[i].name = $this.obj.name;
							data[i].order = $this.obj.order;
							data[i].show =  $this.replace(data[i].show, $this.obj);
							break;
						}
						for (var j = 0; j < data[i].child.length; j++) {
							data[i].child[j].show =  $this.replace(data[i].child[j].show, $this.obj);
							break;
						};
					};
				}, function() {
					$scope.updateQuestion.show = false;
					$scope.alert = {
						show: true,
						text: '更新失败!',
						title: '请注意'
					};
				});
			},
			cancel: function() {
				$scope.updateQuestion.show = false;
			},
			replace: function(str, q) {
				var s = str.split(',');
				var s1 = s[0].split(':');
				s1[1] = q.name;
				var s2 = s[1].split(':');
				s2[1] = q.order;
				var rs = s1[0] + ':' + s1[1];
				rs += ',' + s2[0] + ':' + s2[1];
				for ( var i = 2; i < s.length; i++) {
					rs += ',' + s[i][0] + ':' + s[i][1];
				}
				return rs;
			}
		}
	};
	
	$scope.abs = {
		list: null,
		examNo: '',
		typeError: false,
		open: function() {
			this.list = Student.query({id: $scope.query.examId}, function() {
				$timeout(function() {
					Util.fixTable('absFix', 0, {maxHeight: 220});
				});
			});
			$scope.setAbs = {
				show: true,
				title: '设置缺考学生'
			};
		},
		add: function() {
			var param = {
				examId: $scope.query.examId,
				examNo: this.examNo,
				gradeId: $scope.query.gradeId
			};
			Student.save(param, function(data) {
				if (data.status) {
					$scope.abs.list.push(data.obj);
				} else {
					$scope.abs.msg.set(data.msg);
				}
			}, function() {
				$scope.abs.msg.set('添加失败!');
			});
		},
		del: function(id) {
			Student.remove({id: id}, function() {
				var list = $scope.abs.list;
				for ( var i = 0; i < list.length; i++) {
					if (list[i].id == id) {
						list.splice(i,1);
						break;
					}
				}
			}, function() {
				$scope.abs.msg.set('删除失败!');
			});
		},
		msg: {
			show: false,
			text: '',
			timeout: null,
			close: function() {
				this.show = false;
				this.timeout = null;
			},
			set: function(text) {
				this.text = text;
				this.show = true;
				this.timeout = $timeout(function() {
					$scope.abs.msg.close();
				},5000);
			}
		},
		upload: {
			uploader: new FileUploader({
			    scope: $scope,
			    url: 'student/upload',
			    autoUpload: false,   // 自动开始上传
			    formData: [{ 
			    	  examId: $scope.query.examId,
			    	  gradeId: $scope.query.gradeId
			    }],
			    filters: [{
		            name: 'customFilter',
		            fn: function(item /*{File|FileLikeObject}*/, options) {
		            	if (item.type == 'text/plain') {
		            		$scope.abs.typeError = false;
						} else {
							$scope.abs.typeError = true;
							$scope.abs.msg.set('请选择txt文件!');
						}
		                return item.type == 'text/plain';
		            }
		        }]
			}),
			exec: function() {
				if (this.uploader.queue.length > 0) {
					var item = this.uploader.queue[this.uploader.queue.length-1];
					for ( var i = 0; i < this.uploader.queue.length-1; i++) {
						this.uploader.queue[i].remove();
					}
					item.formData = [{
						examId: $scope.query.examId,
						gradeId: $scope.query.gradeId	
					}];
					if (item.isUploading) {
						$scope.abs.msg.set('上传中...');
					} else {
						if ('text/plain' == item.file.type) {
							item.onSuccess = function(response, status, headers) {
								$scope.loader.show = false;
								if (response.status) {
//									item.remove();
									for (var i = 0; i < response.list.length; i++) {
										$scope.abs.list.push(response.list[i]);
									}
								}
							};
							item.upload();
							$scope.loader = {
								show: true,
								text: '上传中...'
							};
						} else {
							$scope.abs.msg.set('请选择txt文件!');
						}
					}
				} else {
					$scope.abs.msg.set('请选择文件!');
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
	$scope.getTableHeight = function() {
		var height = Util.getWinHeight();
		if (height < 600) {
			height = 600;
		}
		return height - 180;
	}

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

		/*var ids = ['classAvg','scoreDetail', 'answerNum','classAvgKnowledge',
		           'scoreDetailKnowledge', 'classAvgPower', 'scoreDetailPower', 
		           'originalAnswer'];
		for (var i = 0; i < ids.length; i++) {
			if ($('#' + ids[i] + ':visible').length > 0) {
				var id =  ids[i];
				var col = $('#'+id).data('col');
				var height = Util.getWinHeight();
				if (height < 600) {
					height = 600;
				}
				Util.fixTable(id, col, {maxHeight: height - 180});
			}
		}*/
	};
};
