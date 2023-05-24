package com.yuanlrc.campus_market.service.common;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
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
 * 举报物品管理service
 */
import org.springframework.stereotype.Service;

import com.yuanlrc.campus_market.bean.PageBean;
import com.yuanlrc.campus_market.dao.common.ReportGoodsDao;
import com.yuanlrc.campus_market.entity.common.Goods;
import com.yuanlrc.campus_market.entity.common.ReportGoods;
import com.yuanlrc.campus_market.entity.common.Student;

@Service
public class ReportGoodsService {

	@Autowired
	private ReportGoodsDao reportGoodsDao;
	
	/**
	 * 物品添加/编辑，当id不为空时，则编辑
	 * @param goods
	 * @return
	 */
	public ReportGoods save(ReportGoods reportGoods){
		return reportGoodsDao.save(reportGoods);
	}
	
	
	
	
	/**
	 * 物品举报信息删除
	 * @param id
	 */
	public void delete(Long id){
		reportGoodsDao.deleteById(id);
	}
	
	
	
	/**
	 * 根据学生查找物品
	 * @param student
	 * @return
	 */
	public List<ReportGoods> findByStudent(Student student){
		return reportGoodsDao.findByStudent(student);
	}
	
	/**
	 * 根据学生id和物品id查询
	 * @param id
	 * @param studentId
	 * @return
	 */
	public ReportGoods find(Long goodsId,Long studentId){
		return reportGoodsDao.find(goodsId, studentId);
	}
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public ReportGoods find(Long id){
		return reportGoodsDao.find(id);
	}
	
	/**
	 * 物品举报信息搜索
	 * @param pageBean
	 * @param reportGoods
	 * @param goodsList
	 * @return
	 */
	public PageBean<ReportGoods> findlist(PageBean<ReportGoods> pageBean,ReportGoods reportGoods,List<Goods> goodsList){
		
		Specification<ReportGoods> specification = new Specification<ReportGoods>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<ReportGoods> root,
					CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				Predicate predicate = criteriaBuilder.like(root.get("content"), "%" + (reportGoods.getContent() == null ? "" : reportGoods.getContent()) + "%");
				if(reportGoods.getStudent() != null && reportGoods.getStudent().getId() != null){
					Predicate eqal1 = criteriaBuilder.equal(root.get("student"), reportGoods.getStudent().getId());
					predicate = criteriaBuilder.and(predicate,eqal1);
				}
				if(goodsList != null && goodsList.size() >0 ){
					In<Object> in = criteriaBuilder.in(root.get("goods"));
					in.value(goodsList);
					predicate = criteriaBuilder.and(predicate,in);
				}
				return predicate;
			}
		};
		Sort sort = Sort.by(Direction.DESC, "createTime");
		PageRequest pageable = PageRequest.of(pageBean.getCurrentPage()-1, pageBean.getPageSize(), sort);
		Page<ReportGoods> findAll = reportGoodsDao.findAll(specification, pageable);
		pageBean.setContent(findAll.getContent());
		pageBean.setTotal(findAll.getTotalElements());
		pageBean.setTotalPage(findAll.getTotalPages());
		return pageBean;
	}
}
