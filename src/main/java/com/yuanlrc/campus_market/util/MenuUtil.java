package com.yuanlrc.campus_market.util;

import java.util.ArrayList;
import java.util.List;

import com.yuanlrc.campus_market.entity.admin.Menu;

/**
 * 菜单工具类
 * @author Administrator
 *
 */
public class MenuUtil {

	/**
	 * 获取所有的顶级菜单类
	 * @param menus
	 * @return
	 */
	public static List<Menu> getTopMenus(List<Menu> menus){
		List<Menu> topMenus = new ArrayList<Menu>();
		for(Menu menu : menus){
			if(menu.getParent() == null){
				topMenus.add(menu);
			}
		}
		return topMenus;
	}
	
	/**
	 * 获取二级菜单分类
	 * @param menus
	 * @return
	 */
	public static List<Menu> getSecondMenus(List<Menu> menus){
		List<Menu> secondMenus = new ArrayList<Menu>();
		for(Menu menu : menus){
			if(menu.getParent() != null && menu.getParent().getParent() == null){
				secondMenus.add(menu);
			}
		}
		return secondMenus;
	}
	
	/**
	 * 获取三级菜单分类
	 * @param menus
	 * @return
	 */
	public static List<Menu> getThirdMenus(List<Menu> menus){
		List<Menu> thirdMenus = new ArrayList<Menu>();
		for(Menu menu : menus){
			if(menu.getParent() != null && menu.getParent().getParent() != null){
				thirdMenus.add(menu);
			}
		}
		return thirdMenus;
	}
	
	/**
	 * 根据菜单url获取菜单id
	 * @param url
	 * @param menus
	 * @return
	 */
	public static Long getMenuIdByUrl(String url,List<Menu> menus){
		if(url == null)return null;
		for(Menu menu : menus){
			if(url.equals(menu.getUrl())){
				return menu.getId();
			}
		}
		return null;
	}
	
	/**
	 * 获取某个菜单id的所有子分类
	 * @param parentId
	 * @param menus
	 * @return
	 */
	public static List<Menu> getChildren(Long parentId,List<Menu> menus){
		List<Menu> children = new ArrayList<Menu>();
		if(parentId != null){
			for(Menu menu : menus){
				if(menu.getParent() != null && menu.getParent().getId().longValue() == parentId.longValue()){
					children.add(menu);
				}
			}
		}
		return children;
	}
	
	/**
	 * 判断给定的url是否存在于指定的列表
	 * @param url
	 * @param menus
	 * @return
	 */
	public static boolean isExistUrl(String url,List<Menu> menus){
		if(url != null){
			for(Menu menu : menus){
				if(menu.getUrl() != null){
					if(menu.getUrl().contains(url)){
						return true;
					}
				}
			}
		}
		return false;
	}
}
