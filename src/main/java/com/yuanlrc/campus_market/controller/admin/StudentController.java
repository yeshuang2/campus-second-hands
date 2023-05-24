package com.yuanlrc.campus_market.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuanlrc.campus_market.bean.CodeMsg;
import com.yuanlrc.campus_market.bean.PageBean;
import com.yuanlrc.campus_market.bean.Result;
import com.yuanlrc.campus_market.entity.common.Student;
import com.yuanlrc.campus_market.service.common.StudentService;

/**
 * 后台学生管理控制器
 * @author Administrator
 *
 */
@RequestMapping("/admin/student")
@Controller
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	/**
	 * 学生管理列表页面
	 * @param name
	 * @param pageBean
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list")
	public String list(Student student,PageBean<Student> pageBean,Model model){
		model.addAttribute("title", "学生列表");
		model.addAttribute("sn", student.getSn());
		model.addAttribute("pageBean", studentService.findlist(pageBean, student));
		return "admin/student/list";
	}
	
	

	/**
	 * 学生状态编辑
	 * @param id,status
	 * @return
	 */
	@RequestMapping(value="/update_status",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> upDown(@RequestParam(name="id",required=true)Long id ,@RequestParam(name="status",required=true)Integer status){
		Student student = studentService.findById(id);
		if(student == null){
			return Result.error(CodeMsg.ADMIN_GOODS_NO_EXIST);
		}
		if(student.getStatus() == status){
			return Result.error(CodeMsg.ADMIN_STUDENT_STATUS_NO_CHANGE);
		}
		if(status != Student.STUDENT_STATUS_ENABLE && status != Student.STUDENT_STATUS_UNABLE){
			return Result.error(CodeMsg.ADMIN_STUDENT_STATUS_ERROR);
		}
		student.setStatus(status);
		//进行更新数据库
		if(studentService.save(student) ==null){
			return Result.error(CodeMsg.ADMIN_STUDENT_EDIT_ERROR);
		}
		return Result.success(true);
	}
	
	
	/**
	 * 学生删除操作
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> delete(@RequestParam(name="id",required=true)Long id){
		try {
			studentService.delete(id);
		} catch (Exception e) {
			return Result.error(CodeMsg.ADMIN_STUDENT_DELETE_ERROR);
		}
		return Result.success(true);
	}
}
