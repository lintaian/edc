function Main($scope, $rootScope, BaseData, Question, Count, Student, $timeout, 
		FileUploader, $location, Standard, StatisticalCategory, User) {
	if (angular.isUndefined($scope.query) && 
			($location.path() != '' || $location.path() != '/')) {
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
		reportType: {
			obj: {
				ANSWERNUMBER: {
					value: 'ANSWERNUMBER',
					name: '答题数目表'
				},
				CLASSAVG: {
					value: 'CLASSAVG',
					name: '班级均分表'
				},
				QUESTIONSCORE: {
					value: 'QUESTIONSCORE',
					name: '小题得分表'
				},
				ORIGIALANSWER: {
					value: 'ORIGIALANSWER',
					name: '原始答案表'
				},
				CLASSPOWER: {
					value: 'CLASSPOWER',
					name: '能力值均分表'
				},
				STUDENTPOWER: {
					value: 'STUDENTPOWER',
					name: '学生能力值表'
				},
				CLASSKNOWLEDGE: {
					value: 'CLASSKNOWLEDGE',
					name: '知识点均分表'
				},
				STUDENTKNOWLEDGE: {
					value: 'STUDENTKNOWLEDGE',
					name: '学生知识点表'
				}
			},
			current: ''
		},
		exec: function() {
			window.location.href = 'main#/type/' + this.type;
		},
		download: function() {
			if (this.reportType.current != '' && this.reportType.obj[this.reportType.current] != null) {
				window.location.href = 'count/download/' + this.reportType.current + '/' + this.getFileName();
			}
		},
		getFileName: function() {
			var examName = $scope.subject.getName();
			var reportName = this.reportType.obj[this.reportType.current].name;
			return examName + '--' + reportName;
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
				text: '统计中,请稍候...'
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
		},
		getName: function() {
			for (var i = 0; i < this.list.length; i++) {
				if (this.list[i].id == $scope.query.examId) {
					return this.list[i].name;
				}
			}
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
						$scope.abs.msg.set('上传中,请稍候...');
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
	
	$scope.countReport = {
		open: function() {
			$scope.report = {
				show: true,
				title: '统计报表'
			}
			this.batch.change($scope.batch.list[0].id);
			this.grade.change($scope.grade.list[0].id);
			this.standard.init();
		},
		batch: {
			value: '',
			change: function(value) {
				BaseData.getSubjects({id: value}, function(data) {
					for (var i = 0; i < data.length; i++) {
						data[i].checked = true;
					}
					$scope.countReport.exam.list = data;
				});
			}
		},
		grade: {
			value: '',
			change: function(value) {
				BaseData.getClasses({id: value}, function(data) {
					for (var i = 0; i < data.length; i++) {
						data[i].checked = true;
					}
					$scope.countReport.classes.list = data;
				});
			}
		},
		exam: {
			checkAll: true,
			list: [],
			checkAllFn: function(checked) {
				for (var i = 0; i < this.list.length; i++) {
					this.list[i].checked = checked;
				}
			}
		},
		classes: {
			checkAll: true,
			list: [],
			checkAllFn: function(checked) {
				for (var i = 0; i < this.list.length; i++) {
					this.list[i].checked = checked;
				}
			}
		},
		project: {
			checkAll: true,
			list: StatisticalCategory.query(function(data) {
				for (var i = 0; i < data.length; i++) {
					data[i].checked = true;
				}
				$scope.countReport.project.list = data;
			}),
			checkAllFn: function(checked) {
				for (var i = 0; i < this.list.length; i++) {
					this.list[i].checked = checked;
				}
			}
		},
		standard: {
			list: [],
			subjects: Standard.querySubjects(),
			subjectTypes: Standard.querySubjectTypes(),
			standardTypes: Standard.queryStandardTypes(),
			init: function() {
				var schoolId = $scope.school.value || $scope.school.list[0].id;
				Standard.query({id: schoolId}, function(data) {
					$scope.countReport.standard.list = data;
				});
			},
			add: {
				subjectId: '',
				subjectTypeId: '',
				standardTypeId: '',
				score: '',
				exec: function() {
					var schoolId= $scope.school.value || $scope.school.list[0].id,
					subjectId = this.subjectId || $scope.countReport.standard.subjects[0].id,
					subjectTypeId = this.subjectTypeId || $scope.countReport.standard.subjectTypes[0].id,
					standardTypeId = this.standardTypeId || $scope.countReport.standard.standardTypes[0].id,
					score = this.score;
					if (subjectId == '' || subjectTypeId == '' || standardTypeId == '' || score == '') {
						
					} else {
						Standard.save({
							schoolId: schoolId,
							subjectId: subjectId,
							subjectTypeId: subjectTypeId,
							standardTypeId: standardTypeId,
							score: this.score
						}, function(data) {
							$scope.countReport.standard.init();
						});
					}
				}
			},
			update: {
				open: function(index) {
					$scope.countReport.standard.list[index].edit = true;
				},
				exec: function(index) {
					var obj = $scope.countReport.standard.list[index];
					var params = [];
					if (obj.standardId1 != 0) {
						params.push({
							id: obj.standardId1,
							score: obj.score1,
						});
					}
					if (obj.standardId2 != 0) {
						params.push({
							id: obj.standardId2,
							score: obj.score2,
						});
					}
					Standard.update({
						objs: params
					}, function() {
						obj.edit = false;
					}, function() {
						$scope.alert = {
							show: true,
							text: '修改失败!'
						}
					})
				}
			},
			del: function(index) {
				var ids =  '';
				if (this.list[index].standardId1 != '') {
					ids += this.list[index].standardId1;
				}
				if (this.list[index].standardId2 != '') {
					ids += ',' + this.list[index].standardId2;
				}
				$scope.confirm = {
					show: true,
					title: '请注意',
					text: '你确定要删除该记录?',
					yes: function() {
						Standard.del({ids: ids}, function() {
							$scope.countReport.standard.init();
						}, function() {
							$scope.alert = {
								show: true,
								text: '添加失败!'
							}
						})
					}
				}
			}
		},
		exec: function() {
			$scope.loader = {
				show: true,
				text: '统计中,请稍候...'
			}
			var examIds = [],
				classesIds = [],
				projectIds = [],
				standardIds = [];
			var exams = this.exam.list,
				classess = this.classes.list,
				projects = this.project.list,
				standards = this.standard.list;
			for (var i = 0; i < exams.length; i++) {
				if (exams[i].checked) {
					examIds.push(exams[i].id);
				}
			}
			for (var i = 0; i < classess.length; i++) {
				if (classess[i].checked) {
					classesIds.push(classess[i].id);
				}
			}
			for (var i = 0; i < projects.length; i++) {
				if (projects[i].checked) {
					projectIds.push({
						proclass: projects[i].code,
						proname: projects[i].name
					});
				}
			}
			for (var i = 0; i < standards.length; i++) {
				if (standards[i].standardId1 != 0) {
					standardIds.push({
						subjectid: standards[i].subjectId,
						WLtype: standards[i].subjectTypeName,
						StandardType: standards[i].standardTypeName1,
						StandardScore: standards[i].score1
					});
				}
				if (standards[i].standardId2 != 0) {
					standardIds.push({
						subjectid: standards[i].subjectId,
						WLtype: standards[i].subjectTypeName,
						StandardType: standards[i].standardTypeName2,
						StandardScore: standards[i].score2
					});
				}
			}
			User.report({
				exams: examIds,
				classes: classesIds,
				projects: projectIds,
				standards: standardIds,
				schoolId: $scope.school.value || $scope.school.list[0].id,
				gradeId: $scope.countReport.grade.value || $scope.grade.list[0].id,
				batchId: $scope.countReport.batch.value || $scope.batch.list[0].id
				
			}, function(data) {
				$scope.loader.show = false;
				window.location.href = data.url;
			}, function() {
				$scope.loader.show = false;
				$scope.alert = {
					show: true,
					text: '导出报表失败!'
				}
			});
		}
	}
	
	
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
