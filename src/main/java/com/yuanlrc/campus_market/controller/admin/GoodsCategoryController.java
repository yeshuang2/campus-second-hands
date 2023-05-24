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
import com.yuanlrc.campus_market.entity.common.GoodsCategory;
import com.yuanlrc.campus_market.service.common.GoodsCategoryService;
import com.yuanlrc.campus_market.util.ValidateEntityUtil;

/**
 * 后台物品分类管理控制器
 * @author Administrator
 *
 */
@RequestMapping("/admin/goods_category")
@Controller
public class GoodsCategoryController {

	@Autowired
	private GoodsCategoryService goodsCategoryService;
	
	/**
	 * 物品分类管理列表页面
	 * @param name
	 * @param pageBean
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list")
	public String list(GoodsCategory goodsCategory,PageBean<GoodsCategory> pageBean,Model model){
		model.addAttribute("title", "物品分类列表");
		model.addAttribute("name", goodsCategory.getName());
		model.addAttribute("pageBean", goodsCategoryService.findlist(pageBean, goodsCategory));
		return "admin/goods_category/list";
	}
	
	/**
	 * 物品分类添加页面
	 * @param goodsCategory
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model){
		model.addAttribute("title", "添加物品分类");
		model.addAttribute("goodsCategorys", goodsCategoryService.findTopCategorys());
		return "admin/goods_category/add";
	}
	
	/**
	 * 商品分类添加表单提交
	 * @param goodsCategory
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> add(GoodsCategory goodsCategory){
		if(goodsCategory == null){
			return Result.error(CodeMsg.DATA_ERROR);
		}
		//用统一验证实体方法验证是否合法
		CodeMsg validate = ValidateEntityUtil.validate(goodsCategory);
		if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
			return Result.error(validate);
		}
		if(goodsCategory.getParent() != null && goodsCategory.getParent().getId() == null){
			goodsCategory.setParent(null);
		}
		//表示所有数据符合，可以添加到数据库
		if(goodsCategoryService.save(goodsCategory) ==null){
			return Result.error(CodeMsg.ADMIN_GOODSCATEGORY_ADD_ERROR);
		}
		return Result.success(true);
	}
	
	/**
	 * 编辑物品分类页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public String edit(@RequestParam(name="id",required=true)Long id,Model model){
		model.addAttribute("title", "编辑物品分类");
		model.addAttribute("goodsCategorys", goodsCategoryService.findTopCategorys());
		model.addAttribute("goodsCategory", goodsCategoryService.findById(id));
		return "admin/goods_category/edit";
	}
	
	/**
	 * 分类编辑表单提交
	 * @param goodsCategory
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> edit(GoodsCategory goodsCategory){
		if(goodsCategory == null){
			return Result.error(CodeMsg.DATA_ERROR);
		}
		//用统一验证实体方法验证是否合法
		CodeMsg validate = ValidateEntityUtil.validate(goodsCategory);
		if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
			return Result.error(validate);
		}
		if(goodsCategory.getParent() != null && goodsCategory.getParent().getId() == null){
			goodsCategory.setParent(null);
		}
		if(goodsCategory.getId() == null){
			return Result.error(CodeMsg.DATA_ERROR);
		}
		GoodsCategory existGoodsCategory = goodsCategoryService.findById(goodsCategory.getId());
		if(existGoodsCategory == null){
			return Result.error(CodeMsg.DATA_ERROR);
		}
		existGoodsCategory.setIcon(goodsCategory.getIcon());
		existGoodsCategory.setName(goodsCategory.getName());
		existGoodsCategory.setParent(goodsCategory.getParent());
		existGoodsCategory.setSort(goodsCategory.getSort());
		//表示所有数据符合，可以添加到数据库
		if(goodsCategoryService.save(existGoodsCategory) ==null){
			return Result.error(CodeMsg.ADMIN_GOODSCATEGORY_EDIT_ERROR);
		}
		return Result.success(true);
	}
	
	/**
	 * 物品分类删除操作
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> delete(@RequestParam(name="id",required=true)Long id){
		try {
			goodsCategoryService.delete(id);
		} catch (Exception e) {
			return Result.error(CodeMsg.ADMIN_GOODSCATEGORY_DELETE_ERROR);
		}
		return Result.success(true);
	}
}
