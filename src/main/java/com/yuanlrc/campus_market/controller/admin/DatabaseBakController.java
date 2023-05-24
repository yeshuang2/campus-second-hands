package com.yuanlrc.campus_market.controller.admin;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuanlrc.campus_market.bean.PageBean;
import com.yuanlrc.campus_market.bean.Result;
import com.yuanlrc.campus_market.entity.admin.DatabaseBak;
import com.yuanlrc.campus_market.service.admin.DatabaseBakService;
import com.yuanlrc.campus_market.service.admin.OperaterLogService;

/**
 * 数据库备份管理控制器
 * @author Administrator
 *
 */
@RequestMapping("/admin/database_bak")
@Controller
public class DatabaseBakController {

	@Autowired
	private OperaterLogService operaterLogService;
	
	@Autowired
	private DatabaseBakService databaseBakService;
	
	@Value("${ylrc.database.backup.dir}")
	private String backUpDir;
	
	private Logger log = LoggerFactory.getLogger(DatabaseBakController.class);
	
	/**
	 * 数据库备份文件管理列表页面
	 * @param model
	 * @param pageBean
	 * @return
	 */
	@RequestMapping(value="/list")
	public String list(Model model,PageBean<DatabaseBak> pageBean){
		model.addAttribute("title", "备份列表");
		model.addAttribute("pageBean", databaseBakService.findList(pageBean));
		return "admin/database_bak/list";
	}
	
	/**
	 * 数据库备份操作
	 * @return
	 */
	@RequestMapping(value="add",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> add(){
		databaseBakService.backup();
		return Result.success(true);
	}
	
	/**
	 * 删除备份的记录及文件
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="delete",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> delete(String ids){
		if(!StringUtils.isEmpty(ids)){
			String[] splitIds = ids.split(",");
			for(String id : splitIds){
				DatabaseBak databaseBak = databaseBakService.find(Long.valueOf(id));
				if(databaseBak != null){
					databaseBakService.delete(Long.valueOf(id));
					File file = new File(databaseBak.getFilepath() + databaseBak.getFilename());
					if(!file.exists()){
						//此时说明文件不存在，按照配置文件的路径重新寻找文件
						file = new File(backUpDir + databaseBak.getFilename());
					}
					file.delete();
					log.info("删除数据库备份文件，备份ID="+id);
				}
			}
		}
		return Result.success(true);
	}
	
	/**
	 * 还原数据库文件
	 * @param id
	 * @return
	 */
	@RequestMapping(value="restore",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> restore(@RequestParam(name="id",required=true)Long id){
		databaseBakService.restore(id);
		return Result.success(true);
	}
}
