package com.yuanlrc.campus_market.service.admin;
/**
 * 后台角色操作service
 */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.yuanlrc.campus_market.bean.PageBean;
import com.yuanlrc.campus_market.dao.admin.RoleDao;
import com.yuanlrc.campus_market.entity.admin.Role;

@Service
public class RoleService {
	
	@Autowired
	private RoleDao roleDao;
	
	/**
	 * 角色添加/编辑
	 * @param role
	 * @return
	 */
	public Role save(Role role){
		return roleDao.save(role);
	}
	
	/**
	 * 获取所有的角色列表
	 * @return
	 */
	public List<Role> findAll(){
		return roleDao.findAll();
	}
	
	/**
	 * 分页按角色名称搜索角色列表
	 * @param role
	 * @param pageBean
	 * @return
	 */
	public PageBean<Role> findByName(Role role,PageBean<Role> pageBean){
		ExampleMatcher withMatcher = ExampleMatcher.matching().withMatcher("name", GenericPropertyMatchers.contains());
		withMatcher = withMatcher.withIgnorePaths("status");
		Example<Role> example = Example.of(role, withMatcher);
		Pageable pageable = PageRequest.of(pageBean.getCurrentPage()-1, pageBean.getPageSize());
		Page<Role> findAll = roleDao.findAll(example, pageable);
		pageBean.setContent(findAll.getContent());
		pageBean.setTotal(findAll.getTotalElements());
		pageBean.setTotalPage(findAll.getTotalPages());
		return pageBean;
	}
	
	/**
	 * 根据id查询角色
	 * @param id
	 * @return
	 */
	public Role find(Long id){
		return roleDao.find(id);
	}
	
	/**
	 * 根据id删除一条记录
	 * @param id
	 */
	public void delete(Long id){
		roleDao.deleteById(id);
	}
}
