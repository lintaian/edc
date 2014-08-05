package com.lps.edc.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lps.edc.dto.ClassStructDto;
import com.lps.edc.dto.ExamDto;
import com.lps.edc.dto.RespDto;
import com.lps.edc.entity.InfExamQuestion;
import com.lps.edc.entity.SysTeacher;
import com.lps.edc.entity.SysUser;
import com.lps.edc.service.interfaces.ImageServiceIF;
import com.lps.edc.service.interfaces.InfExamQuestionServiceIF;
import com.lps.edc.service.interfaces.ReportServiceIF;
import com.lps.edc.service.interfaces.SysTeacherServiceIF;
import com.lps.edc.service.interfaces.SysUserServiceIF;
import com.lps.edc.util.Helper;

@Controller
@RequestMapping("")
public class MainController {
	@Resource
	private SysUserServiceIF userService;
	@Resource
	private SysTeacherServiceIF teacherService;
	@Resource
	private ImageServiceIF imageService;
	@Resource
	private InfExamQuestionServiceIF questionService;
	@Resource
	private ReportServiceIF reportService;
	
	@RequestMapping(value="login", method=RequestMethod.GET)
	public String loginGet(HttpServletRequest req, HttpServletResponse resp) {
		if (req.getSession().getAttribute("user") != null) {
			try {
				resp.sendRedirect(req.getScheme()+"://"+req.getServerName()+":"
						+req.getServerPort()+req.getContextPath()+"/main");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "login";
	}
	
	@ResponseBody
	@RequestMapping(value="login", method=RequestMethod.POST)
	public JSONObject loginPost(HttpServletRequest req, HttpServletResponse resp, @RequestBody JSONObject reqBody) throws Exception {
		JSONObject re = new JSONObject();
		re.put("status", false);
		String role = reqBody.has("role") ? reqBody.getString("role") : "admin";
		String name = reqBody.has("username") ? reqBody.getString("username") : "";
		String password = reqBody.has("password") ? reqBody.getString("password") : "";
		if (name != null && !"".equals(name) && password != null && !"".equals(password)) {
			if ("admin".equals(role)) {
				SysUser user = userService.get(name);
				if (user != null) {
					if (password.equals(user.getPassword())) {
						re.put("status", true);
						req.getSession().setAttribute("user", user);
						req.getSession().setAttribute("role", role);
					} else {
						re.put("msg", "密码错误!");
					}
				} else {
					re.put("msg", "用户名不存在!");
				}
			} else {
				SysTeacher teacher = teacherService.get(name);
				if (teacher != null) {
					if (password.equals(teacher.getPassword())) {
						re.put("status", true);
						req.getSession().setAttribute("user", teacher);
						req.getSession().setAttribute("role", role);
					} else {
						re.put("msg", "密码错误!");
					}
				} else {
					re.put("msg", "用户名不存在!");
				}
			}
		} else {
			re.put("msg", "用户名和密码不能为空!");
		}
		return re;
	}
	
	@RequestMapping("main")
	public String main(HttpServletRequest req, HttpServletResponse resp) {
		return "main";
	}
	
	@RequestMapping("image/{id}")
	public void image(HttpServletResponse resp,@PathVariable String id, String examId, String questionName) throws Exception {
		InfExamQuestion q = questionService.get(examId, questionName);
		String questionId = q.getQuestionId();
		byte[] temp = imageService.getOneExamQuestion(id, questionId);
		resp.setContentType("image/png");
		OutputStream os = resp.getOutputStream();
		os.write(temp);
		os.close();
	}
	@RequestMapping(value="logout")
	public void logout(HttpServletRequest req) {
		req.getSession().invalidate();
	}
	
	@ResponseBody
	@RequestMapping(value="report", method=RequestMethod.POST)
	public JSONObject report(HttpServletRequest req, HttpServletResponse resp, @RequestBody JSONObject body) throws Exception {
		JSONArray examIds = body.getJSONArray("exams");
		JSONArray classIds = body.getJSONArray("classes");
		JSONArray projectIds = body.getJSONArray("projects");
		JSONArray standardIds = body.getJSONArray("standards");
		String schoolId = body.getString("schoolId");
		String gradeId = body.getString("gradeId");
		String batchId = body.getString("batchId");
		List<String> cIds = Helper.covertJsonArrayToList(classIds);
		List<ClassStructDto> classes = reportService.queryClass(cIds, schoolId, gradeId);
		String tId = "";
		if ("admin".equals(req.getSession().getAttribute("role").toString())) {
			SysUser user = (SysUser) req.getSession().getAttribute("user");
			tId = user.getUserId();
		} else {
			SysTeacher teacher = (SysTeacher) req.getSession().getAttribute("user");
			tId = teacher.getTeacherid();
		}
		List<String> eIds = Helper.covertJsonArrayToList(examIds);
		List<ExamDto> exams = reportService.queryExam(eIds, batchId, schoolId, tId);
		RespDto respDto = new RespDto(classes, exams, null, null);
		JSONObject dto = JSONObject.fromObject(respDto);
		dto.put("prolist", projectIds);
		dto.put("standlist", standardIds);
		String url = reportService.getUrl(dto.toString());
		JSONObject rs = JSONObject.fromObject(url);
		rs.put("url", "http://localhost:801/1.rar");
		Thread.sleep(5000);
		return rs;
	}
	
}
