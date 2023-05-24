package com.yuanlrc.campus_market.dao.common;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;





/**
 * 物品分类管理dao层
 */
import com.yuanlrc.campus_market.entity.common.GoodsCategory;
@Repository
public interface GoodsCategoryDao extends JpaRepository<GoodsCategory, Long> {
	/**
	 * 获取所有一级分类
	 * @return
	 */
	List<GoodsCategory> findByParentIsNull();
	
	/**
	 * 获取所有的二级分类
	 * @return
	 */
	List<GoodsCategory> findByParentIsNotNull();
	
	/**
	 * 根据id获取
	 * @return
	 */
	@Query("select gc from GoodsCategory gc where id = :id")
	GoodsCategory find(@Param("id")Long id);
	
	/**
	 * 获取某个顶级分类下的子分类
	 * @param parent
	 * @return
	 */
	List<GoodsCategory> findByParent(GoodsCategory parent);
	
	/**
	 * 根据
	 * @param name
	 * @return
	 */
	List<GoodsCategory> findByName(String name);
}
