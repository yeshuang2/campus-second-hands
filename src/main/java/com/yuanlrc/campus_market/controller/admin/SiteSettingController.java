package com.yuanlrc.campus_market.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuanlrc.campus_market.bean.CodeMsg;
import com.yuanlrc.campus_market.bean.Result;
import com.yuanlrc.campus_market.entity.common.SiteSetting;
import com.yuanlrc.campus_market.service.admin.OperaterLogService;
import com.yuanlrc.campus_market.service.common.SiteSettingService;
import com.yuanlrc.campus_market.util.ValidateEntityUtil;

/**
 * 后台网站设置管理控制器
 * @author Administrator
 *
 */
@RequestMapping("/admin/site_setting")
@Controller
public class SiteSettingController {

	@Autowired
	private SiteSettingService siteSettingService;
	@Autowired
	private OperaterLogService operaterLogService;
	
	
	/**
	 * 网站设置页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/setting",method=RequestMethod.GET)
	public String setting(Model model){
		model.addAttribute("siteSetting", siteSettingService.find());
		return "admin/site_setting/setting";
	}
	
	/**
	 * 网站设置表单提交处理
	 * @param siteSetting
	 * @return
	 */
	@RequestMapping(value="/save_setting",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> setting(SiteSetting siteSetting){
		//用统一验证实体方法验证是否合法
		CodeMsg validate = ValidateEntityUtil.validate(siteSetting);
		if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
			return Result.error(validate);
		}
		//到这说明一切符合条件，进行数据库新增
		//判断是否是首次提交
		if(siteSetting.getId() != null){
			//表示不是首次提交
			SiteSetting find = siteSettingService.find();
			siteSetting.setCreateTime(find.getCreateTime());
		}
		if(siteSettingService.save(siteSetting) == null){
			return Result.error(CodeMsg.ADMIN_SITESETTING_EDIT_ERROR);
		}
		operaterLogService.add("修改网站设置:" + siteSetting);
		return Result.success(true);
	}
	
	
}
