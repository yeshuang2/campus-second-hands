package com.yuanlrc.campus_market.dao.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yuanlrc.campus_market.entity.common.News;

/**
 * 新闻公告dao层
 */
@Repository
public interface NewsDao extends JpaRepository<News, Long> {
	
	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	@Query("select n from News n where id = :id")
	News find(@Param("id")Long id);
}
