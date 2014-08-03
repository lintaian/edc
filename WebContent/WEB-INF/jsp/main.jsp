<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%><!DOCTYPE html>
<html xmlns:ng="http://angularjs.org" id="ng-app">
<head>
  <base href="<%=basePath%>">
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>考试数据检查</title>
  <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
  <link rel="stylesheet" type="text/css" href="css/bootstrap-responsive.min.css">
  <link rel="stylesheet" type="text/css" href="css/main.css">
  <link rel="stylesheet" type="text/css" href="css/dialog.css">
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
	  			<button class="btn btn-info" style="width: 60px;padding: 10px;height: 60px;margin-top: 26px;" >统计报表</button>
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
			<input class="btn btn-info" value="确定" type="button" style="margin: 0 10px;" data-ng-click="question.update.exec()"/>
			<input class="btn" value="取消" type="button" style="margin: 0 10px;" data-ng-click="question.update.cancel()"/>
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
	<div data-loading data-ng-model="loader" data-config="{type:bool, width: 140}"></div>
	<div data-confirm data-ng-model="confirm"></div>
  	<div data-alert data-ng-model="alert"></div>
	<script data-main="js/ng/main" src="js/lib/require.js"></script>
</body>
</html>