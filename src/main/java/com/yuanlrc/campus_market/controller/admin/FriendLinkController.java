package com.yuanlrc.campus_market.controller.admin;

import org.springframework.beans.BeanUtils;
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
import com.yuanlrc.campus_market.entity.common.FriendLink;
import com.yuanlrc.campus_market.service.admin.OperaterLogService;
import com.yuanlrc.campus_market.service.common.FriendLinkService;
import com.yuanlrc.campus_market.util.ValidateEntityUtil;

/**
 * 后台友情链接管理控制器
 * @author Administrator
 *
 */
@RequestMapping("/admin/friend_link")
@Controller
public class FriendLinkController {

	@Autowired
	private FriendLinkService friendLinkService;
	@Autowired
	private OperaterLogService operaterLogService;
	/**
	 * 友情链接列表页面
	 * @param model
	 * @param user
	 * @param pageBean
	 * @return
	 */
	@RequestMapping(value="/list")
	public String list(Model model,FriendLink friendLink,PageBean<FriendLink> pageBean){
		model.addAttribute("title", "友情链接列表");
		model.addAttribute("name", friendLink.getName());
		model.addAttribute("pageBean", friendLinkService.findList(pageBean,friendLink));
		return "admin/friend_link/list";
	}
	
	/**
	 * 新增友情链接页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model){
		return "admin/friend_link/add";
	}
	
	/**
	 * 友情链接添加表单提交处理
	 * @param friendLink
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> add(FriendLink friendLink){
		//用统一验证实体方法验证是否合法
		CodeMsg validate = ValidateEntityUtil.validate(friendLink);
		if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
			return Result.error(validate);
		}
		//到这说明一切符合条件，进行数据库新增
		if(friendLinkService.save(friendLink) == null){
			return Result.error(CodeMsg.ADMIN_FRIENDLINK_ADD_ERROR);
		}
		operaterLogService.add("添加友情链接:" + friendLink);
		return Result.success(true);
	}
	
	/**
	 * 友情链接编辑页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public String edit(Model model,@RequestParam(name="id",required=true)Long id){
		model.addAttribute("friendLink", friendLinkService.find(id));
		return "admin/friend_link/edit";
	}
	
	/**
	 * 编辑友情链接信息表单提交处理
	 * @param friendLink
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> edit(FriendLink friendLink){
		//用统一验证实体方法验证是否合法
		CodeMsg validate = ValidateEntityUtil.validate(friendLink);
		if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
			return Result.error(validate);
		}
		//到这说明一切符合条件，进行数据库保存
		FriendLink findById = friendLinkService.find(friendLink.getId());
		//讲提交的友情链接信息指定字段复制到已存在的user对象中,该方法会覆盖新字段内容
		BeanUtils.copyProperties(friendLink, findById, "id","createTime","updateTime");
		if(friendLinkService.save(findById) == null){
			return Result.error(CodeMsg.ADMIN_FRIENDLINK_EDIT_ERROR);
		}
		operaterLogService.add("编辑友情链接：" + findById);
		return Result.success(true);
	}
	
	/**
	 * 删除友情链接
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> delete(@RequestParam(name="ids",required=true)String ids){
		String[] split = ids.split(",");
		for(String id : split){
			friendLinkService.delete(Long.valueOf(id));
		}
		operaterLogService.add("批量删除友情链接，友情链接IDS：" + ids);
		return Result.success(true);
	}
}
