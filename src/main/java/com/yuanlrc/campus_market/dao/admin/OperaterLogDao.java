package com.yuanlrc.campus_market.dao.admin;
/**
 * 后台操作日志类数据库操作层
 */
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yuanlrc.campus_market.entity.admin.OperaterLog;

@Repository
public interface OperaterLogDao extends JpaRepository<OperaterLog, Long> {
	
	/**
	 * 根据id查找操作日志
	 * @param id
	 * @return
	 */
	@Query("select ol from OperaterLog ol where id = :id")
	OperaterLog find(@Param("id")Long id);
	
	/**
	 * 获取最近的指定条数的操作日志
	 * @param size
	 * @return
	 */
	@Query(value="select * from ylrc_operater_log order by create_time desc limit 0,:size",nativeQuery=true)
	List<OperaterLog> findLastestLog(@Param("size")int size);
}
