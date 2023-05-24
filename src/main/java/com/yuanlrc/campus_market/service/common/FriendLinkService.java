package com.yuanlrc.campus_market.service.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.yuanlrc.campus_market.bean.PageBean;
import com.yuanlrc.campus_market.dao.common.FriendLinkDao;
import com.yuanlrc.campus_market.entity.common.FriendLink;

/**
 * 友情链接service
 * @author Administrator
 *
 */
@Service
public class FriendLinkService {

	@Autowired
	private FriendLinkDao friendLinkDao;
	
	/**
	 * 新闻信息添加/修改（id不为空则为修改）
	 * @param friendLink
	 * @return
	 */
	public FriendLink save(FriendLink friendLink){
		return friendLinkDao.save(friendLink);
	}
	
	/**
	 * 分页搜索友情链接
	 * @param pageBean
	 * @param friendLink
	 * @return
	 */
	public PageBean<FriendLink> findList(PageBean<FriendLink> pageBean,FriendLink friendLink){
		ExampleMatcher exampleMatcher = ExampleMatcher.matching();
		exampleMatcher = exampleMatcher.withMatcher("name", GenericPropertyMatchers.contains());
		exampleMatcher = exampleMatcher.withIgnorePaths("sort");
		Example<FriendLink> example = Example.of(friendLink, exampleMatcher);
		Sort sort = Sort.by(Direction.ASC, "sort");
		PageRequest pageable = PageRequest.of(pageBean.getCurrentPage()-1, pageBean.getPageSize(), sort);
		Page<FriendLink> findAll = friendLinkDao.findAll(example, pageable);
		pageBean.setContent(findAll.getContent());
		pageBean.setTotal(findAll.getTotalElements());
		pageBean.setTotalPage(findAll.getTotalPages());
		return pageBean;
	}
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public FriendLink find(Long id){
		return friendLinkDao.find(id);
	}
	
	/**
	 * 友情链接删除
	 * @param id
	 */
	public void delete(Long id){
		friendLinkDao.deleteById(id);
	}

	/**
	 * 获取制定数量的新闻列表
	 * @param size
	 * @return
	 */
	public List<FriendLink> findList(int size){
		FriendLink friendLink = new FriendLink();
		PageBean<FriendLink> pageBean = new PageBean<FriendLink>();
		pageBean.setPageSize(size);
		return findList(pageBean, friendLink).getContent();
	}
}
