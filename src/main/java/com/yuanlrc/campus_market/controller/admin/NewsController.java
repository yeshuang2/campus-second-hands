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
import com.yuanlrc.campus_market.entity.common.News;
import com.yuanlrc.campus_market.service.admin.OperaterLogService;
import com.yuanlrc.campus_market.service.common.NewsService;
import com.yuanlrc.campus_market.util.ValidateEntityUtil;

/**
 * 后台新闻公告管理控制器
 * @author Administrator
 *
 */
@RequestMapping("/admin/news")
@Controller
public class NewsController {

	@Autowired
	private NewsService newsService;
	@Autowired
	private OperaterLogService operaterLogService;
	/**
	 * 新闻公告列表页面
	 * @param model
	 * @param user
	 * @param pageBean
	 * @return
	 */
	@RequestMapping(value="/list")
	public String list(Model model,News news,PageBean<News> pageBean){
		model.addAttribute("title", "新闻公告列表");
		model.addAttribute("newsTitle", news.getTitle());
		model.addAttribute("pageBean", newsService.findList(pageBean,news));
		return "admin/news/list";
	}
	
	/**
	 * 新增新闻公告页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model){
		return "admin/news/add";
	}
	
	/**
	 * 新闻公告添加表单提交处理
	 * @param news
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> add(News news){
		//用统一验证实体方法验证是否合法
		CodeMsg validate = ValidateEntityUtil.validate(news);
		if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
			return Result.error(validate);
		}
		//到这说明一切符合条件，进行数据库新增
		if(newsService.save(news) == null){
			return Result.error(CodeMsg.ADMIN_NEWS_ADD_ERROR);
		}
		operaterLogService.add("添加新闻公告:" + news);
		return Result.success(true);
	}
	
	/**
	 * 新闻公告编辑页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public String edit(Model model,@RequestParam(name="id",required=true)Long id){
		model.addAttribute("news", newsService.find(id));
		return "admin/news/edit";
	}
	
	/**
	 * 编辑新闻公告信息表单提交处理
	 * @param news
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> edit(News news){
		//用统一验证实体方法验证是否合法
		CodeMsg validate = ValidateEntityUtil.validate(news);
		if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
			return Result.error(validate);
		}
		//到这说明一切符合条件，进行数据库保存
		News findById = newsService.find(news.getId());
		//讲提交的新闻公告信息指定字段复制到已存在的user对象中,该方法会覆盖新字段内容
		BeanUtils.copyProperties(news, findById, "id","createTime","updateTime");
		if(newsService.save(findById) == null){
			return Result.error(CodeMsg.ADMIN_NEWS_EDIT_ERROR);
		}
		operaterLogService.add("编辑新闻公告：" + findById);
		return Result.success(true);
	}
	
	/**
	 * 删除新闻公告
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> delete(@RequestParam(name="id",required=true)Long id){
		newsService.delete(id);
		operaterLogService.add("删除新闻公告，新闻公告ID：" + id);
		return Result.success(true);
	}
}
