package com.lps.edc.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.lps.edc.dto.AbsStudentDto;
import com.lps.edc.dto.SimpleDto;
import com.lps.edc.entity.InfExamAbsStudent;
import com.lps.edc.entity.SysStudent;
import com.lps.edc.service.interfaces.InfExamAbsStudentServiceIF;

@Controller
@RequestMapping("student")
public class StudentController {
	@Resource
	private InfExamAbsStudentServiceIF absStudentService;
	
	@ResponseBody
	@RequestMapping(value="/exam/{id}", method=RequestMethod.GET)
	public JSONArray query(@PathVariable String id) throws Exception {
		List<AbsStudentDto> data = absStudentService.query(id);
		return JSONArray.fromObject(data);
	}
	
	@ResponseBody
	@RequestMapping(value="", method=RequestMethod.POST)
	public JSONObject add(@RequestBody JSONObject reqBody) throws Exception {
		JSONObject json = new JSONObject();
		String examId = reqBody.getString("examId");
		String examNo = reqBody.getString("examNo");
		String gradeId = reqBody.getString("gradeId");
		AbsStudentDto dto = absStudentService.query(examId, examNo);
		SysStudent student = absStudentService.queryExceptAbs(gradeId, examNo);
		if (dto != null) {
			json.put("status", false);
			json.put("msg", "该学生已经添加");
		} else if (student == null) {
			json.put("status", false);
			json.put("msg", "该学号不存在");
		} else {
			InfExamAbsStudent as = new InfExamAbsStudent();
			as.setExamid(examId);
			as.setExamno(examNo);
			as.setInserttime(new Date());
			absStudentService.add(as);
			AbsStudentDto abs = absStudentService.query(examId, examNo);
			json.put("status", true);
			json.put("obj", abs);
		}
		return json;
	}
	
	@ResponseBody
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public JSONObject del(@PathVariable int id) throws Exception {
		JSONObject json = new JSONObject();
		absStudentService.del(id);
		json.put("status", true);
		return json;
	}
	@ResponseBody
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public JSONObject upload(String examId, String gradeId, 
			MultipartHttpServletRequest req) throws Exception {
		JSONObject json = new JSONObject();
		MultipartFile file = req.getFile("file");
		InputStreamReader read = new InputStreamReader(file.getInputStream(), "utf-8");//考虑到编码格式
        BufferedReader bufferedReader = new BufferedReader(read);
        List<String> examNos = new ArrayList<String>();
        String lineTxt = null;
        while((lineTxt = bufferedReader.readLine()) != null){
        	examNos.add(lineTxt);
        }
        read.close();
        List<InfExamAbsStudent> absStudents = new ArrayList<InfExamAbsStudent>();
        List<String> examNoDo = new ArrayList<String>();
        for (String examNo : examNos) {
    		if (absStudentService.queryExceptAbs(gradeId, examNo) != null 
    				&& absStudentService.query(examId, examNo) == null) {
    			InfExamAbsStudent as = new InfExamAbsStudent();
    			as.setExamid(examId);
    			as.setExamno(examNo);
    			as.setInserttime(new Date());
    			absStudents.add(as);
    			examNoDo.add(examNo);
    		}
		}
        absStudentService.add(absStudents);
        List<AbsStudentDto> absStudentDtos = new ArrayList<AbsStudentDto>();
        for (String examNo : examNoDo) {
        	AbsStudentDto abs = absStudentService.query(examId, examNo);
        	absStudentDtos.add(abs);
		}
        json.put("status", true);
        json.put("list", absStudentDtos);
        Thread.sleep(5000);
		return json;
	}
	
	@ResponseBody
	@RequestMapping(value="/queryAllExceptAbs/{gId}/{eId}", method=RequestMethod.GET)
	public JSONArray queryAllExceptAbs(@PathVariable String gId, @PathVariable String eId) throws Exception {
		List<SimpleDto> data = absStudentService.queryAllExceptAbs(gId, eId);
		return JSONArray.fromObject(data);
	}
	
}
