package com.lps.edc.controller;

import java.io.IOException;
import java.io.OutputStream;
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

import com.lps.edc.dao.interfaces.CountDAOIF;
import com.lps.edc.entity.SysTeacher;
import com.lps.edc.entity.SysUser;
import com.lps.edc.service.interfaces.ImageServiceIF;
import com.lps.edc.service.interfaces.InfExamQuestionServiceIF;
import com.lps.edc.service.interfaces.SysTeacherServiceIF;
import com.lps.edc.service.interfaces.SysUserServiceIF;

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
	private CountDAOIF countDao;
	
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
		String questionId = questionService.get(examId, questionName).getQuestionId();
		byte[] temp = imageService.getOneExamQuestion(id, questionId);
		resp.setContentType("image/png");
		OutputStream os = resp.getOutputStream();
		os.write(temp);
		os.close();
	}
	@RequestMapping("test")
	public void test() throws Exception {
//		JSONArray a = JSONArray.fromObject(countDao.getStudentKnowledge("86E4C0D1-7377-4863-B2FD-6A8E6417FD27"));
		String examId = "54578b79-e80b-426b-a27e-f0eb7852156d";
		String gradeId = "2cc22950-52ec-4d95-8077-4453956b008f";
		JSONArray classList = new JSONArray();
		JSONObject cl1 = new JSONObject();
		cl1.put("id", "cdb8cc81-2f76-40dc-ad57-9dbdf371ee45");
		cl1.put("name", "4班");
		classList.add(cl1);
		JSONObject cl2 = new JSONObject();
		cl2.put("id", "7a2bf0dc-0d80-4207-b645-16757aeb0495");
		cl2.put("name", "5班");
		classList.add(cl2);
		JSONArray questionList = new JSONArray();
		JSONObject ql1 = new JSONObject();
		ql1.put("id", "4562ADB3-D43E-4907-9E27-F727A3521E2A");
		ql1.put("name", "1");
		questionList.add(ql1);
		JSONObject ql2 = new JSONObject();
		ql2.put("id", "C559D614-0E53-4E5C-8C25-A8A54CB97243");
		ql2.put("name", "2");
		questionList.add(ql2);
		JSONArray a = JSONArray.fromObject(countDao.getQuestionScore(gradeId, examId, classList, questionList, "", ""));
		System.out.println(a);
	}
	@RequestMapping(value="logout")
	public void logout(HttpServletRequest req) {
		req.getSession().invalidate();
	}
	
}
