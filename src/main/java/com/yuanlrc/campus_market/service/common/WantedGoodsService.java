package com.yuanlrc.campus_market.service.common;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
/**
 * 求购物品service
 */
import org.springframework.stereotype.Service;

import com.yuanlrc.campus_market.bean.PageBean;
import com.yuanlrc.campus_market.dao.common.WantedGoodsDao;
import com.yuanlrc.campus_market.entity.common.Student;
import com.yuanlrc.campus_market.entity.common.WantedGoods;

@Service
public class WantedGoodsService {

	@Autowired
	private WantedGoodsDao wantedGoodsDao;
	
	/**
	 * 求购物品信息添加/编辑（传入id则为编辑，否则是添加）
	 * @param wantedGoods
	 * @return
	 */
	public WantedGoods save(WantedGoods wantedGoods){
		return wantedGoodsDao.save(wantedGoods);
	}
	
	/**
	 * 根据学生查询
	 * @param student
	 * @return
	 */
	public List<WantedGoods> findByStudent(Student student){
		return wantedGoodsDao.findByStudent(student);
	}
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public WantedGoods find(Long id){
		return wantedGoodsDao.find(id);
	}
	
	/**
	 * 根据id删除
	 * @param id
	 */
	public void delete(Long id){
		wantedGoodsDao.deleteById(id);
	}
	
	/**
	 * 分页展示求购物品列表
	 * @param pageBean
	 * @param WantedGoods
	 * @return
	 */
	public PageBean<WantedGoods> findlist(PageBean<WantedGoods> pageBean,WantedGoods WantedGoods){
		ExampleMatcher exampleMatcher = ExampleMatcher.matching();
		exampleMatcher = exampleMatcher.withMatcher("name", GenericPropertyMatchers.contains());
		exampleMatcher = exampleMatcher.withIgnorePaths("viewNumber");
		Example<WantedGoods> example = Example.of(WantedGoods, exampleMatcher);
		Sort sort = Sort.by(Direction.DESC, "createTime");
		PageRequest pageable = PageRequest.of(pageBean.getCurrentPage()-1, pageBean.getPageSize(), sort);
		Page<WantedGoods> findAll = wantedGoodsDao.findAll(example, pageable);
		pageBean.setContent(findAll.getContent());
		pageBean.setTotal(findAll.getTotalElements());
		pageBean.setTotalPage(findAll.getTotalPages());
		return pageBean;
	}
	
	public PageBean<WantedGoods> findWantedGoodslist(PageBean<WantedGoods> pageBean,WantedGoods wantedGoods){
		
		Specification<WantedGoods> specification = new Specification<WantedGoods>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<WantedGoods> root,
					CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				Predicate predicate = criteriaBuilder.like(root.get("name"), "%" + (wantedGoods.getName() == null ? "" : wantedGoods.getName()) + "%");
				if(wantedGoods.getStudent() != null && wantedGoods.getStudent().getId() != null){
					Predicate equal1 = criteriaBuilder.equal(root.get("student"), wantedGoods.getStudent().getId());
					predicate = criteriaBuilder.and(predicate,equal1);
				}
				return predicate;
			}
		};
		Sort sort = Sort.by(Direction.DESC, "createTime");
		PageRequest pageable = PageRequest.of(pageBean.getCurrentPage()-1, pageBean.getPageSize(), sort);
		Page<WantedGoods> findAll = wantedGoodsDao.findAll(specification, pageable);
		pageBean.setContent(findAll.getContent());
		pageBean.setTotal(findAll.getTotalElements());
		pageBean.setTotalPage(findAll.getTotalPages());
		return pageBean;
	}
	
	/**
	 * 求购物品总数
	 * @return
	 */
	public long total(){
		return wantedGoodsDao.count();
	}
}
