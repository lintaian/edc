<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html xmlns:ng="http://angularjs.org" id="ng-app">
<head>
  <base href="<%=basePath%>">
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>考试数据检查</title>
  <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
  <link rel="stylesheet" type="text/css" href="css/bootstrap-responsive.min.css">
  <link rel="stylesheet" type="text/css" href="css/defaultTheme.css">
  <link rel="stylesheet" type="text/css" href="css/main.css">
  <link rel="stylesheet" type="text/css" href="css/dialog.css">
  <script src="js/lib/es5-shim.js"></script>
  <script src="js/lib/es5-sham.js"></script>
  <!--[if lte IE 8]>
    <script src="js/lib/html5shiv.js"></script>
    <script src="js/lib/json3.js"></script>
    <script src="js/lib/ie-fix.js"></script>
  <![endif]-->
</head>
<body data-ng-controller="Main" data-nv-file-drop="" data-uploader="abs.upload.uploader" data-filters="queueLimit, customFilter">
	<div class="container-fluid main">
	  <div class="row-fluid" style="border-bottom: gray solid 2px;">
	  	<div class="span5" style="text-align: center;">
	  		<form class="form-inline">
	  			学校:&nbsp;<select class="input-small width-select" data-ng-model="school.value" data-ng-change="school.change(school.value)">
					  <option data-ng-repeat="s in school.list" data-ng-value="s.id" data-ng-bind="s.name"
					  data-ng-selected="s.id == school.value"></option>
					</select>&nbsp;&nbsp;
				年级:&nbsp;<select class="input-small width-select" data-ng-model="grade.value" data-ng-change="grade.change(grade.value)">
					  <option data-ng-repeat="g in grade.list" data-ng-value="g.id" data-ng-bind="g.name"
					  data-ng-selected="g.id == grade.value"></option>
					</select>
	  		</form>
			<form class="form-inline middleForm">
	  			批次:&nbsp;<select class="input-small width-select" data-ng-model="batch.value" data-ng-change="batch.change(batch.value)">
					  <option data-ng-repeat="b in batch.list" data-ng-value="b.id" data-ng-bind="b.name"
					  data-ng-selected="b.id == batch.value"></option>
					</select>&nbsp;&nbsp;
				考试:&nbsp;<select class="input-small width-select" data-ng-model="subject.value" data-ng-change="subject.change(subject.value)">
					  <option data-ng-repeat="s in subject.list" data-ng-value="s.id" data-ng-bind="s.name"
					  data-ng-selected="s.id == subject.value"></option>
					</select>
	  		</form>
			<form class="form-inline last">
			  姓名:&nbsp;<input type="text" class="input-small width-input" data-ng-model="query.studentName" placeholder="姓名">&nbsp;&nbsp;
			  考号:&nbsp;<input type="text" class="input-small width-input" data-ng-model="query.examNo" placeholder="考号">
			</form>
	  	</div>
	  	<div class="span3" style="text-align: center;width: 20%;">
	  		<form class="form-inline float-left">
    			  <select class="input-small" style="width: 100px;" data-ng-model="subject.state">
    					  <option value="考试准备">考试准备</option>
    					  <option value="开始批阅">开始批阅</option>
    					  <option value="可以统计">可以统计</option>
    					</select>&nbsp;&nbsp;
                <button class="btn btn-info" data-ng-click="subject.setStateReal()">设置</button>
			</form>
	  		<form class="form-inline float-left middleForm">
			    <label class="checkbox" style="padding-right: 40px;">
    			  <input type="checkbox" data-ng-model="count.type"> A卷50%
    			</label>
                <button class="btn btn-info" data-ng-disabled="subject.getState()!='可以统计'" 
                  data-ng-click="count.exec()">统计得分</button>
			</form>
	  		<form class="form-inline float-left last">
    			 <button class="btn btn-info" style="width: 200px;" data-ng-click="abs.open()">设置缺考</button>
			</form>
	  	</div>
	  	<div class="span3" style="width: 20%;text-align: center;">
	  		<form class="form-inline">
	  			<label class="radio" style="padding-right: 50px;">
				  <input type="radio" name="countType" value="score" data-ng-model="query.type"> 题目分
				</label>
	  			<label class="radio">
				  <input type="radio" name="countType" value="knowledgePoint" data-ng-model="query.type"> 知识点&nbsp;&nbsp;&nbsp;&nbsp;
				</label>
	  		</form>
	  		<form class="form-inline middleForm">
	  			<label class="radio" style="padding-right: 50px;">
				  <input type="radio" name="countType" value="capability" data-ng-model="query.type"> 能力值
				</label>
	  			<label class="radio">
				  <input type="radio" name="countType" value="originalAnswer" data-ng-model="query.type"> 原始答案
				</label>
	  		</form>
	  		<form class="form-inline last">
	  			<button class="btn btn-info" data-ng-click="query.exec()" style="width: 186px;">查询</button>
	  		</form>
	  	</div>
        <div class="span1" style="width: 10%;text-align: center;">
  			<button class="btn btn-info" style="width: 60px;padding: 10px;height: 60px;margin-top: 26px;" 
  				data-ng-click="countReport.open()">统计报表</button>
        </div>
	  </div>
	  <div class="row-fluid">
	    <div class="span2" style="border-right: gray solid 2px;width: 19.52991452991453%">
	    	<div id="classes">
		    	<div class="form-inline" style="padding: 5px 0;border-bottom: gray solid 1px;text-align: center;">
		      		<span>选择班级: </span>&nbsp;&nbsp;
		      		<label class="checkbox">
				      <input type="checkbox" data-ng-model="classes.checkAll" 
				      	data-ng-change="classes.checkAllFn(classes.checkAll)"> 全选
				    </label>
		    	</div>
		    	<div style="height: 137px;overflow-y: auto;" >
		    		<ul class="unstyled ng-hide" data-ng-show="classes.list != null">
		    			<li data-ng-repeat="c in classes.list" class="form-inline"
                          data-ng-class-odd="'info'" data-ng-class-even="'success'" data-ng-class="{last: $last}">
		    				<label class="checkbox">
    							<input type="checkbox" data-ng-model="c.checked">
   							</label>&nbsp;&nbsp;
	    					<span data-ng-bind="c.name"></span> 
                            (<span data-ng-bind="c.wlType"></span>)
		    			</li>
		    		</ul>
		    	</div>
	    	</div>
	    	<div id="questions">
	    		<div class="form-inline" style="padding: 5px 0;border-bottom: gray solid 1px;border-top: gray solid 1px;text-align: center;">
		      		<span>选择试题: </span>&nbsp;&nbsp;
		      		<label class="checkbox">
				      <input type="checkbox" data-ng-model="question.checkAll"
				      	data-ng-change="question.checkAllFn(question.checkAll)"> 全选
				    </label>
		    	</div>
		    	<div id="question" style="height: 450px;overflow: auto;">
		    		<ul class="unstyled sidebar ng-hide" data-ng-show="question.list != null">
		    			<li data-ng-repeat="q in question.list" class="form-inline inline-block" style="white-space:nowrap;" 
                            data-ng-class-odd="'info'" data-ng-class-even="'success'" data-ng-class="{last: $last}"
                            data-ng-mouseenter="q.edit = true" data-ng-mouseleave="q.edit = false">
                            <span class="edit" data-ng-show="q.edit"></span>
                            <i class="icon-pencil" data-ng-show="q.edit" data-ng-click="question.update.to(q)"></i>
  		    				<span class="for-icon" data-ng-class="{icon: q.child.length>0, more: !q.open, less: q.open}"
  		    					data-ng-click="q.open = !q.open"></span>
  		    				<label class="checkbox">
      							<input type="checkbox" data-ng-model="q.checked">
     						</label>&nbsp;&nbsp;
  	    					<span data-ng-bind="q.show"></span>
	    					<ul class="unstyled sidebar2" data-ng-show="q.open">
	    						<li data-ng-repeat="c in q.child" class="form-inline inline-clock" style="white-space: nowrap;"
                                  data-ng-class-odd="'info'" data-ng-class-even="'success'" data-ng-class="{last: $last}"
                                  data-ng-mouseenter="c.edit=true" data-ng-mouseleave="c.edit = false">
                                    <span class="edit2" data-ng-show="c.edit"></span>
        		    				<i class="icon-pencil child" data-ng-show="c.edit" data-ng-click="question.update.to(c)"></i>
	    							<label class="checkbox">
	    								<input type="checkbox" data-ng-model="c.checked">
	    							</label>&nbsp;&nbsp;
	    							<span data-ng-bind="c.show"></span>
	    						</li>
	    					</ul>
		    			</li>
		    		</ul>
		    	</div>
	    	</div>
	    </div>
	    <div id="report" class="span10" style="margin-left: 0.1%;width: 80.2%;overflow: auto;">
	      <div data-ng-view style="margin: 5px 0 0 10px;background: #fff;"></div>
	    </div>
	  </div>
	</div>
	<div data-dialog data-ng-model="updateQuestion" data-config="{width:327,height:175}" class="ng-hide">
		<form class="form-horizontal" style="margin-left: -88px;">
			<div class="control-group info">
				<label class="control-label">设置名称:</label>
				<div class="controls">
					<input type="text" data-ng-model="question.update.obj.name" placeholder="名称"/>
				</div>
			</div>
			<div class="control-group info">
				<label class="control-label">设置序号:</label>
				<div class="controls">
					<input type="text" data-ng-model="question.update.obj.order" placeholder="序号"
                      onkeyup="this.value=this.value.replace(/\D/g,'')" />
				</div>
			</div>
		</form>
		<div style="text-align: center;">
			<button class="btn btn-info" type="button" style="margin: 0 10px;" data-ng-click="question.update.exec()">确定</button>
			<button class="btn" type="button" style="margin: 0 10px;" data-ng-click="question.update.cancel()">取消</button>
		</div>
	</div>
    <div data-dialog data-ng-model="setAbs" data-config="{width:500,height:300}" class="ng-hide">
        <div class="abs">
            <div class="alert ng-hide" data-ng-show="abs.msg.show" style="position: absolute;top: 0px;left: 190px;">
                <button type="button" class="close" data-ng-click="abs.msg.close()">&times;</button>
                <span data-ng-bind="abs.msg.text" style=""></span>
            </div>
            <form class="form-inline" 
                style="margin-bottom: 6px;" data-file-upload="abs.upload.options">
                                          学号: <input type="text" class="input-small" data-ng-model="abs.examNo" style="width: 100px;" placeholder="学号">
                <button type="button" class="btn btn-info" data-ng-click="abs.add()" style="margin-right: 10px;"
                  data-ng-disabled="abs.examNo == null || abs.examNo == ''">添加</button>
                <input type="file" class="input-small" style="width: 113px;"
                  data-nv-file-select="" data-uploader="abs.upload.uploader">
                <button type="button" class="btn btn-info" data-ng-click="abs.upload.exec()" 
                  data-ng-disabled="abs.upload.uploader.queue.length == 0 || abs.upload.uploader.queue[0].isUploading || 
                  abs.typeError">上传</button>
            </form>
            <table id="absFix" class="table table-bordered table-hover" style="margin-bottom: 0px;height: 220px;">
                <thead>
                    <tr>
                        <th>序号</th>
                        <th>考号</th>
                        <th>姓名</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                    <tr data-ng-repeat="a in abs.list">
                        <td data-ng-bind="a.id"></td>
                        <td data-ng-bind="a.examNo"></td>
                        <td data-ng-bind="a.studentName"></td>
                        <td><a href="javascript:void(0)" data-ng-click="abs.del(a.id)">删除</a></td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div data-dialog data-ng-model="report" data-config="{width:900,height:600}" class="ng-hide">
        <div class="report">
	      <table class="table">
	      	<tr>
	      		<td>
	      			<form class="form-inline">
	      				批次:&nbsp;&nbsp;<select class="input-small width-select" data-ng-model="countReport.batch.value" 
	      					data-ng-change="countReport.batch.change(countReport.batch.value)">
						  <option data-ng-repeat="b in batch.list" data-ng-value="b.id" data-ng-bind="b.name"
						  data-ng-selected="b.id == batch.value"></option>
						</select>
	      			</form>
	      		</td>
	      		<td>
	      			<form class="form-inline">
		      			年级:&nbsp;&nbsp;<select class="input-small width-select" data-ng-model="countReport.grade.value" 
		      				data-ng-change="countReport.grade.change(countReport.grade.value)">
						  <option data-ng-repeat="g in grade.list" data-ng-value="g.id" data-ng-bind="g.name"
						  data-ng-selected="g.id == grade.value"></option>
						</select>
	      			</form>
	      		</td>
	      	</tr>
	      	<tr>
	      		<td>
	      			<form class="form-inline">
	      				选择考试:&nbsp;&nbsp;
	      				<label class="checkbox">
			      			<input type="checkbox" data-ng-model="countReport.exam.checkAll"
			      				data-ng-change="countReport.exam.checkAllFn(countReport.exam.checkAll)">&nbsp;全选
	      				</label>
	      			</form>
	      			<div class="list">
	      				<table class="table table-bordered table-hover">
							<thead>
								<tr>
									<th>选择</th>
									<th>考试</th>
								</tr>
							</thead>
							<tbody>
								<tr data-ng-repeat="e in countReport.exam.list">
									<td>
										<input type="checkbox" data-ng-model="e.checked">
									</td>
									<td>
										<span data-ng-bind="e.name"></span>
									</td>
								</tr>
							</tbody>
	      				</table>
	      			</div>
	      		</td>
	      		<td>
	      			<form class="form-inline">
	      				选择班级:&nbsp;&nbsp;
	      				<label class="checkbox">
			      			<input type="checkbox" data-ng-model="countReport.classes.checkAll"
			      				data-ng-change="countReport.classes.checkAllFn(countReport.classes.checkAll)">&nbsp;全选
	      				</label>
	      			</form>
	      			<div class="list">
	      				<table class="table table-bordered table-hover">
							<thead>
								<tr>
									<th>选择</th>
									<th>班级</th>
									<th>文理</th>
								</tr>
							</thead>
							<tbody>
								<tr data-ng-repeat="c in countReport.classes.list">
									<td>
										<input type="checkbox" data-ng-model="c.checked">
									</td>
									<td>
										<span data-ng-bind="c.name"></span>
									</td>
									<td>
										<span data-ng-bind="c.wlType"></span>
									</td>
								</tr>
							</tbody>
	      				</table>
	      			</div>
	      		</td>
	      	</tr>
	      	<tr>
	      		<td>
	      			<form class="form-inline">
	      				统计项目:&nbsp;&nbsp;
	      				<label class="checkbox">
			      			<input type="checkbox" data-ng-model="countReport.project.checkAll"
			      				data-ng-change="countReport.project.checkAllFn(countReport.project.checkAll)">&nbsp;全选
	      				</label>
	      			</form>
	      			<div class="list">
	      				<table class="table table-bordered table-hover">
							<thead>
								<tr>
									<th>选择</th>
									<th>统计项目</th>
								</tr>
							</thead>
							<tbody>
								<tr data-ng-repeat="p in countReport.project.list">
									<td>
										<input type="checkbox" data-ng-model="p.checked">
									</td>
									<td>
										<span data-ng-bind="p.name"></span>
									</td>
								</tr>
							</tbody>
	      				</table>
	      			</div>
	      		</td>
	      		<td>
	      			<form class="form-inline">
		      			达标线:&nbsp;<select class="small" data-ng-model="countReport.standard.add.subjectId">
						  <option data-ng-repeat="s in countReport.standard.subjects" data-ng-value="s.id" data-ng-bind="s.name"
						  data-ng-selected="s.id == countReport.standard.add.subjectId"></option>
						</select>
						<select class="small" data-ng-model="countReport.standard.add.subjectTypeId">
						  <option data-ng-repeat="s in countReport.standard.subjectTypes" data-ng-value="s.id" data-ng-bind="s.name"
						  data-ng-selected="s.id == countReport.standard.add.subjectTypeId"></option>
						</select>
						<select class="small" data-ng-model="countReport.standard.add.standardTypeId">
						  <option data-ng-repeat="s in countReport.standard.standardTypes" data-ng-value="s.id" data-ng-bind="s.name"
						  data-ng-selected="s.id == countReport.standard.add.standardTypeId"></option>
						</select>
						<input type="text" style="width: 30px;" placeholder="分数" data-ng-model="countReport.standard.add.score"/>
						<button class="btn btn-info" style="width: auto;" data-ng-click="countReport.standard.add.exec()">添加</button>
	      			</form>
	      			<div class="list">
	      				<table class="table table-bordered table-hover">
							<thead>
								<tr>
									<th>科目</th>
									<th>文理</th>
									<th>一本</th>
									<th>本科</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<tr data-ng-repeat="s in countReport.standard.list">
									<td>
										<span data-ng-bind="s.subjectName"></span>
									</td>
									<td>
										<span data-ng-bind="s.subjectTypeName"></span>
									</td>
									<td>
										<span data-ng-bind="s.score1" data-ng-show="s.standard1 != 0 && !s.edit"></span>
										<input data-ng-model="s.score1" data-ng-show="s.standard1 != 0 && s.edit"
											style="width: 30px;">
									</td>
									<td>
										<span data-ng-bind="s.score2" data-ng-show="s.standard2 != 0 && !s.edit"></span>
										<input data-ng-model="s.score2" data-ng-show="s.standard2 != 0 && s.edit"
											style="width: 30px;">
									</td>
									<td>
										<a href="javascript:void(0)" data-ng-click="countReport.standard.update.open($index)"
											data-ng-show="!s.edit">修改</a>
										<a href="javascript:void(0)" data-ng-click="countReport.standard.update.exec($index)"
											data-ng-show="s.edit">保存</a><br/>
										<a href="javascript:void(0)" data-ng-click="countReport.standard.del($index)">删除</a>
									</td>
								</tr>
							</tbody>
	      				</table>
	      			</div>
	      		</td>
	      	</tr>
	      	<tr>
	      		<td colspan="2">
					<button class="btn btn-info" data-ng-click="countReport.exec()">开始统计</button>
	      		</td>
	      	</tr>
	      </table>
	  </div>
    </div>
	<div data-loading data-ng-model="loader" data-config="{type:bool, width: 200}"></div>
	<div data-loading data-ng-model="loaderReport" data-config="{type:bool, width: 400}"></div>
	<div data-confirm data-ng-model="confirm"></div>
  	<div data-alert data-ng-model="alert"></div>
	<script data-main="js/ng/main" src="js/lib/require.js"></script>
</body>
</html>