package com.yuanlrc.campus_market.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuanlrc.campus_market.bean.CodeMsg;
import com.yuanlrc.campus_market.bean.Result;
import com.yuanlrc.campus_market.entity.admin.Menu;
import com.yuanlrc.campus_market.service.admin.MenuService;
import com.yuanlrc.campus_market.service.admin.OperaterLogService;
import com.yuanlrc.campus_market.util.MenuUtil;
import com.yuanlrc.campus_market.util.ValidateEntityUtil;

/**
 * 后台菜单管理控制器
 * @author Administrator
 *
 */
@RequestMapping("/admin/menu")
@Controller
public class MenuController {

	@Autowired
	private MenuService menuService;
	
	@Autowired
	private OperaterLogService operaterLogService;
	
	/**
	 * 菜单列表展示页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list")
	public String list(Model model){
		List<Menu> findAll = menuService.findAll();
		model.addAttribute("title","菜单列表");
		model.addAttribute("topMenus",MenuUtil.getTopMenus(findAll));
		model.addAttribute("secondMenus",MenuUtil.getSecondMenus(findAll));
		model.addAttribute("thirdMenus",MenuUtil.getThirdMenus(findAll));
		return "admin/menu/list";
	}
	
	/**
	 * 菜单添加页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model){
		List<Menu> findAll = menuService.findAll();
		model.addAttribute("title","菜单列表");
		model.addAttribute("topMenus",MenuUtil.getTopMenus(findAll));
		model.addAttribute("secondMenus",MenuUtil.getSecondMenus(findAll));
		return "admin/menu/add";
	}
	
	/**
	 * 菜单添加提交表单处理
	 * @param menu
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> add(Menu menu){
		if(menu == null){
			Result.error(CodeMsg.DATA_ERROR);
		}
		//用统一验证实体方法验证是否合法
		CodeMsg validate = ValidateEntityUtil.validate(menu);
		if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
			return Result.error(validate);
		}
		if(menu.getParent() != null){
			if(menu.getParent().getId() == null){
				menu.setParent(null);
			}
		}
		//表示验证都通过，开始添加数据库
		if(menuService.save(menu) == null){
			Result.error(CodeMsg.ADMIN_MENU_ADD_ERROR);
		}
		//数据库添加操作成功,记录日志
		operaterLogService.add("添加菜单信息【" + menu + "】");
		return Result.success(true);
	}
	
	/**
	 * 菜单编辑页面
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public String eidt(Model model,@RequestParam(name="id",required=true)Long id){
		List<Menu> findAll = menuService.findAll();
		model.addAttribute("title","菜单列表");
		model.addAttribute("topMenus",MenuUtil.getTopMenus(findAll));
		model.addAttribute("secondMenus",MenuUtil.getSecondMenus(findAll));
		model.addAttribute("menu",menuService.find(id));
		return "admin/menu/edit";
	}
	
	/**
	 * 菜单编辑页面表单提交处理
	 * @param request
	 * @param menu
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> edit(Menu menu){
		if(menu == null){
			Result.error(CodeMsg.DATA_ERROR);
		}
		if(menu.getId() == null){
			Result.error(CodeMsg.ADMIN_MENU_ID_EMPTY);
		}
		//用统一验证实体方法验证是否合法
		CodeMsg validate = ValidateEntityUtil.validate(menu);
		if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
			return Result.error(validate);
		}
		if(menu.getParent() != null){
			if(menu.getParent().getId() == null){
				menu.setParent(null);
			}
		}
		Menu existMenu = menuService.find(menu.getId());
		if(existMenu == null){
			Result.error(CodeMsg.ADMIN_MENU_ID_ERROR);
		}
		//表示验证都通过，开始添加数据库
		existMenu.setIcon(menu.getIcon());
		existMenu.setName(menu.getName());
		existMenu.setParent(menu.getParent());
		existMenu.setSort(menu.getSort());
		existMenu.setUrl(menu.getUrl());
		existMenu.setButton(menu.isButton());
		existMenu.setShow(menu.isShow());
		if(menuService.save(existMenu) == null){
			Result.error(CodeMsg.ADMIN_MENU_ADD_ERROR);
		}
		//数据库添加操作成功,记录日志
		operaterLogService.add("编辑菜单信息【" + existMenu + "】");
		return Result.success(true);
	}
	
	/**
	 * 删除菜单信息
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> delete(@RequestParam(name="id",required=true)Long id){
		try {
			menuService.delete(id);
		} catch (Exception e) {
			return Result.error(CodeMsg.ADMIN_MENU_DELETE_ERROR);
		}
		//数据库添加操作成功,记录日志
		operaterLogService.add("删除菜单信息，菜单ID【" + id + "】");
		return Result.success(true);
	}
}
