package com.yuanlrc.campus_market.controller.admin;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.yuanlrc.campus_market.entity.admin.OperaterLog;
import com.yuanlrc.campus_market.entity.admin.Role;
import com.yuanlrc.campus_market.entity.admin.User;
import com.yuanlrc.campus_market.listener.SessionListener;
import com.yuanlrc.campus_market.service.admin.DatabaseBakService;
import com.yuanlrc.campus_market.service.admin.OperaterLogService;
import com.yuanlrc.campus_market.service.admin.UserService;
import com.yuanlrc.campus_market.service.common.CommentService;
import com.yuanlrc.campus_market.service.common.GoodsService;
import com.yuanlrc.campus_market.service.common.StudentService;
import com.yuanlrc.campus_market.service.common.WantedGoodsService;
import com.yuanlrc.campus_market.util.SessionUtil;
import com.yuanlrc.campus_market.util.StringUtil;
import com.yuanlrc.campus_market.util.ValidateEntityUtil;

/**
 * 系统控制器
 * @author Administrator
 *
 */
@RequestMapping("/system")
@Controller
public class SystemController {

	
	
	@Autowired
	private OperaterLogService operaterLogService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DatabaseBakService databaseBakService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private WantedGoodsService wantedGoodsService;
	
	@Autowired
	private CommentService commentService;
	
	
	private Logger log = LoggerFactory.getLogger(SystemController.class);
	
	/**
	 * 登录页面
	 * @param name
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(Model model){
		return "admin/system/login";
	}
	
	/**
	 * 用户登录提交表单处理方法
	 * @param request
	 * @param user
	 * @param cpacha
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> login(HttpServletRequest request,User user,String cpacha){
		if(user == null){
			return Result.error(CodeMsg.DATA_ERROR);
		}
		//用统一验证实体方法验证是否合法
		CodeMsg validate = ValidateEntityUtil.validate(user);
		if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
			return Result.error(validate);
		}
		//表示实体信息合法，开始验证验证码是否为空
		if(StringUtils.isEmpty(cpacha)){
			return Result.error(CodeMsg.CPACHA_EMPTY);
		}
		//说明验证码不为空，从session里获取验证码
		Object attribute = request.getSession().getAttribute("admin_login");
		if(attribute == null){
			return Result.error(CodeMsg.SESSION_EXPIRED);
		}
		//表示session未失效，进一步判断用户填写的验证码是否正确
		if(!cpacha.equalsIgnoreCase(attribute.toString())){
			return Result.error(CodeMsg.CPACHA_ERROR);
		}
		//表示验证码正确，开始查询数据库，检验密码是否正确
		User findByUsername = userService.findByUsername(user.getUsername());
		//判断是否为空
		if(findByUsername == null){
			return Result.error(CodeMsg.ADMIN_USERNAME_NO_EXIST);
		}
		//表示用户存在，进一步对比密码是否正确
		if(!findByUsername.getPassword().equals(user.getPassword())){
			return Result.error(CodeMsg.ADMIN_PASSWORD_ERROR);
		}
		//表示密码正确，接下来判断用户状态是否可用
		if(findByUsername.getStatus() == User.ADMIN_USER_STATUS_UNABLE){
			return Result.error(CodeMsg.ADMIN_USER_UNABLE);
		}
		//检查用户所属角色状态是否可用
		if(findByUsername.getRole() == null || findByUsername.getRole().getStatus() == Role.ADMIN_ROLE_STATUS_UNABLE){
			return Result.error(CodeMsg.ADMIN_USER_ROLE_UNABLE);
		}
		//检查用户所属角色的权限是否存在
		if(findByUsername.getRole().getAuthorities() == null || findByUsername.getRole().getAuthorities().size() == 0){
			return Result.error(CodeMsg.ADMIN_USER_ROLE_AUTHORITES_EMPTY);
		}
		//检查一切符合，可以登录，将用户信息存放至session
		request.getSession().setAttribute(SessionConstant.SESSION_USER_LOGIN_KEY, findByUsername);
		//销毁session中的验证码
		request.getSession().setAttribute("admin_login", null);
		//将登陆记录写入日志库
		operaterLogService.add("用户【"+user.getUsername()+"】于【" + StringUtil.getFormatterDate(new Date(), "yyyy-MM-dd HH:mm:ss") + "】登录系统！");
		log.info("用户成功登录，user = " + findByUsername);
		return Result.success(true);
	}
	
	/**
	 * 登录成功后的系统主页
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/index")
	public String index(Model model){
		model.addAttribute("operatorLogs", operaterLogService.findLastestLog(10));
		model.addAttribute("userTotal", userService.total());
		model.addAttribute("operatorLogTotal", operaterLogService.total());
		model.addAttribute("databaseBackupTotal", databaseBakService.total());
		model.addAttribute("onlineUserTotal", SessionListener.onlineUserCount);
		model.addAttribute("studentTotal", studentService.total());
		model.addAttribute("goodsTotal", goodsService.total());
		model.addAttribute("commentTotal", commentService.total());
		model.addAttribute("wantGoodsTotal", wantedGoodsService.total());
		return "admin/system/index";
	}
	
	/**
	 * 注销登录
	 * @return
	 */
	@RequestMapping(value="/logout")
	public String logout(){
		User loginedUser = SessionUtil.getLoginedUser();
		if(loginedUser != null){
			SessionUtil.set(SessionConstant.SESSION_USER_LOGIN_KEY, null);
		}
		return "redirect:login";
	}
	
	/**
	 * 无权限提示页面
	 * @return
	 */
	@RequestMapping(value="/no_right")
	public String noRight(){
		return "admin/system/no_right";
	}
	
	/**
	 * 修改用户个人信息
	 * @return
	 */
	@RequestMapping(value="/update_userinfo",method=RequestMethod.GET)
	public String updateUserInfo(){
		return "admin/system/update_userinfo";
	}
	
	/**
	 * 修改个人信息保存
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/update_userinfo",method=RequestMethod.POST)
	public String updateUserInfo(User user){
		User loginedUser = SessionUtil.getLoginedUser();
		loginedUser.setEmail(user.getEmail());
		loginedUser.setMobile(user.getMobile());
		loginedUser.setHeadPic(user.getHeadPic());
		//首先保存到数据库
		userService.save(loginedUser);
		//更新session里的值
		SessionUtil.set(SessionConstant.SESSION_USER_LOGIN_KEY, loginedUser);
		return "redirect:update_userinfo";
	}
	
	/**
	 * 修改密码页面
	 * @return
	 */
	@RequestMapping(value="/update_pwd",method=RequestMethod.GET)
	public String updatePwd(){
		return "admin/system/update_pwd";
	}
	
	/**
	 * 修改密码表单提交
	 * @param oldPwd
	 * @param newPwd
	 * @return
	 */
	@RequestMapping(value="/update_pwd",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> updatePwd(@RequestParam(name="oldPwd",required=true)String oldPwd,
			@RequestParam(name="newPwd",required=true)String newPwd
			){
		User loginedUser = SessionUtil.getLoginedUser();
		if(!loginedUser.getPassword().equals(oldPwd)){
			return Result.error(CodeMsg.ADMIN_USER_UPDATE_PWD_ERROR);
		}
		if(StringUtils.isEmpty(newPwd)){
			return Result.error(CodeMsg.ADMIN_USER_UPDATE_PWD_EMPTY);
		}
		loginedUser.setPassword(newPwd);
		//保存数据库
		userService.save(loginedUser);
		//更新session
		SessionUtil.set(SessionConstant.SESSION_USER_LOGIN_KEY, loginedUser);
		return Result.success(true);
	}
	
	/**
	 * 日志管理列表
	 * @param model
	 * @param operaterLog
	 * @param pageBean
	 * @return
	 */
	@RequestMapping(value="/operator_log_list")
	public String operatorLogList(Model model,OperaterLog operaterLog,PageBean<OperaterLog> pageBean){
		model.addAttribute("pageBean", operaterLogService.findList(operaterLog, pageBean));
		model.addAttribute("operator", operaterLog.getOperator());
		model.addAttribute("title", "日志列表");
		return "admin/system/operator_log_list";
	}
	
	/**
	 * 删除操作日志，可删除多个
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/delete_operator_log",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> delete(String ids){
		if(!StringUtils.isEmpty(ids)){
			String[] splitIds = ids.split(",");
			for(String id : splitIds){
				operaterLogService.delete(Long.valueOf(id));
			}
		}
		return Result.success(true);
	}
	
	/**
	 * 清空整个日志
	 * @return
	 */
	@RequestMapping(value="/delete_all_operator_log",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> deleteAll(){
		operaterLogService.deleteAll();
		return Result.success(true);
	}
}
