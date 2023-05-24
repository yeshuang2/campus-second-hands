package com.yuanlrc.campus_market.controller.home;

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
import com.yuanlrc.campus_market.constant.SessionConstant;
import com.yuanlrc.campus_market.entity.common.Goods;
import com.yuanlrc.campus_market.entity.common.News;
import com.yuanlrc.campus_market.entity.common.Student;
import com.yuanlrc.campus_market.service.common.GoodsCategoryService;
import com.yuanlrc.campus_market.service.common.GoodsService;
import com.yuanlrc.campus_market.service.common.NewsService;
import com.yuanlrc.campus_market.service.common.StudentService;
import com.yuanlrc.campus_market.util.SessionUtil;
import com.yuanlrc.campus_market.util.ValidateEntityUtil;

/**
 * 前台首页控制器
 * @author Administrator
 *
 */
@RequestMapping("/home/index")
@Controller
public class IndexController {
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private GoodsCategoryService goodsCategoryService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private NewsService newsService;
	/**
	 * 前台首页
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/index")
	public String index(Model model,PageBean<Goods> pageBean,Goods goods){
		pageBean.setPageSize(12);
		goods.setStatus(Goods.GOODS_STATUS_UP);
		model.addAttribute("pageBean", goodsService.findlist(pageBean, goods));
		model.addAttribute("name",goods.getName());
		model.addAttribute("newsList",newsService.findList(3));
		return "home/index/index";
	}
	
	/**
	 * 新闻详情页面
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/news_detail")
	public String index(Model model,@RequestParam(name="id",required=true)Long id){
		News news = newsService.find(id);
		model.addAttribute("news",news);
		news.setViewNumber(news.getViewNumber()+1);
		newsService.save(news);
		return "home/index/news_detail";
	}
	
	/**
	 * 用户登录页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(Model model){
		return "home/index/login";
	}
	
	/**
	 * 退出登录
	 * @return
	 */
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(){
		SessionUtil.set(SessionConstant.SESSION_STUDENT_LOGIN_KEY, null);
		return "redirect:login";
	}
	
	/**
	 * 检查学号是否存在
	 * @param sn
	 * @return
	 */
	@RequestMapping(value="/check_sn",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> checkSn(@RequestParam(name="sn",required=true)String sn){
		Student student = studentService.findBySn(sn);
		return Result.success(student == null);
	}
	
	/**
	 * 用户注册表单提交
	 * @param student
	 * @return
	 */
	@RequestMapping(value="/register",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> register(Student student){
		CodeMsg validate = ValidateEntityUtil.validate(student);
		if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
			return Result.error(validate);
		}
		//基本验证通过
		if(studentService.findBySn(student.getSn()) != null){
			return Result.error(CodeMsg.HOME_STUDENT_REGISTER_SN_EXIST);
		}
		student = studentService.save(student);
		if(student == null){
			return Result.error(CodeMsg.HOME_STUDENT_REGISTER_ERROR);
		}
		//表示注册成功，此时将用户信息放入session
		SessionUtil.set(SessionConstant.SESSION_STUDENT_LOGIN_KEY, student);
		return Result.success(true);
	}
	
	/**
	 * 用户登录表单提交
	 * @param sn
	 * @param password
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> login(@RequestParam(name="sn",required=true)String sn,
			@RequestParam(name="password",required=true)String password){
		Student student = studentService.findBySn(sn);
		if(student == null){
			return Result.error(CodeMsg.HOME_STUDENT_REGISTER_SN_EXIST);
		}
		student = studentService.save(student);
		if(student == null){
			return Result.error(CodeMsg.HOME_STUDENT_SN_NO_EXIST);
		}
		//表示学号存在，验证密码是否正确
		if(!student.getPassword().equals(password)){
			return Result.error(CodeMsg.HOME_STUDENT_PASSWORD_ERROR);
		}
		//验证用户状态是否被冻结
		if(student.getStatus() != Student.STUDENT_STATUS_ENABLE){
			return Result.error(CodeMsg.HOME_STUDENT_UNABLE);
		}
		//表示一切都符合，此时将用户信息放入session
		SessionUtil.set(SessionConstant.SESSION_STUDENT_LOGIN_KEY, student);
		return Result.success(true);
	}
}
