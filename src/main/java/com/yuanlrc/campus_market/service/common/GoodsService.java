package com.yuanlrc.campus_market.service.common;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
/**
 * 物品管理service
 */
import org.springframework.stereotype.Service;

import com.yuanlrc.campus_market.bean.PageBean;
import com.yuanlrc.campus_market.dao.common.GoodsDao;
import com.yuanlrc.campus_market.entity.common.Goods;
import com.yuanlrc.campus_market.entity.common.Student;

@Service
public class GoodsService {

	@Autowired
	private GoodsDao goodsDao;
	
	/**
	 * 物品添加/编辑，当id不为空时，则编辑
	 * @param goods
	 * @return
	 */
	public Goods save(Goods goods){
		return goodsDao.save(goods);
	}
	
	
	/**
	 * 搜索分类列表
	 * @param pageBean
	 * @param goods
	 * @return
	 */
	public PageBean<Goods> findlist(PageBean<Goods> pageBean,Goods goods){
		
		Specification<Goods> specification = new Specification<Goods>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Goods> root,
					CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				Predicate predicate = criteriaBuilder.like(root.get("name"), "%" + (goods.getName() == null ? "" : goods.getName()) + "%");
				if(goods.getStudent() != null && goods.getStudent().getId() != null){
					Predicate equal1 = criteriaBuilder.equal(root.get("student"), goods.getStudent().getId());
					predicate = criteriaBuilder.and(predicate,equal1);
				}
				if(goods.getStatus() != -1){
					Predicate equal2 = criteriaBuilder.equal(root.get("status"), goods.getStatus());
					predicate = criteriaBuilder.and(predicate,equal2);
				}
				if(goods.getGoodsCategory() != null && goods.getGoodsCategory().getId() != null){
					Predicate equal2 = criteriaBuilder.equal(root.get("goodsCategory"), goods.getGoodsCategory().getId());
					predicate = criteriaBuilder.and(predicate,equal2);
				}
				return predicate;
			}
		};
		Sort sort = Sort.by(Direction.DESC, "createTime","recommend","flag","viewNumber");
		PageRequest pageable = PageRequest.of(pageBean.getCurrentPage()-1, pageBean.getPageSize(), sort);
		Page<Goods> findAll = goodsDao.findAll(specification, pageable);
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
	public Goods findById(Long id){
		return goodsDao.find(id);
	}
	
	/**
	 * 物品分类删除
	 * @param id
	 */
	public void delete(Long id){
		goodsDao.deleteById(id);
	}
	
	/**
	 * 获取所有的物品
	 * @return
	 */
	public List<Goods> findAll(){
		return goodsDao.findAll();
	}
	
	/**
	 * 根据学生查找物品
	 * @param student
	 * @return
	 */
	public List<Goods> findByStudent(Student student){
		return goodsDao.findByStudent(student);
	}
	
	/**
	 * 根据学生id和物品id查询
	 * @param id
	 * @param studentId
	 * @return
	 */
	public Goods find(Long id,Long studentId){
		return goodsDao.find(id, studentId);
	}
	
	/**
	 * 根据物品分类查询列表
	 * @param cids
	 * @param pageBean
	 * @return
	 */
	public PageBean<Goods> findlist(List<Long> cids,PageBean<Goods> pageBean){
		List<Goods> findList = goodsDao.findList(cids,pageBean.getOffset(), pageBean.getPageSize());
		pageBean.setContent(findList);
		pageBean.setTotal(goodsDao.getTotalCount(cids));
		pageBean.setTotalPage(Integer.valueOf(pageBean.getTotal() / pageBean.getPageSize()+""));
		long totalPage = pageBean.getTotal() % pageBean.getPageSize();
		if(totalPage != 0){
			pageBean.setTotalPage(pageBean.getTotalPage() + 1);
		}
		return pageBean;
	}
	
	/**
	 * 获取指定状态的物品总数
	 * @param status
	 * @return
	 */
	public Long getTotalCount(Integer status){
		return goodsDao.getTotalCount(status);
	}
	
	/**
	 * 获取已出售的商品总数
	 * @return
	 */
	public Long getSoldTotalCount(){
		return getTotalCount(Goods.GOODS_STATUS_SOLD);
	}
	
	/**
	 * 根据物品名称模糊搜索
	 * @param name
	 * @return
	 */
	public List<Goods> findListByName(String name){
		return goodsDao.findListByName(name);
	}
	
	/**
	 * 获取物品总数
	 * @return
	 */
	public long total(){
		return goodsDao.count();
	}
}
