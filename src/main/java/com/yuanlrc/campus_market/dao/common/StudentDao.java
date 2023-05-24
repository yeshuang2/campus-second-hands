package com.yuanlrc.campus_market.dao.common;
/**
 * 学生信息操作dao类
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yuanlrc.campus_market.entity.common.Student;
@Repository
public interface StudentDao extends JpaRepository<Student, Long> {
	
	/**
	 * 根据学号查询
	 * @param sn
	 * @return
	 */
	Student findBySn(String sn);
	
	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	@Query("select s from Student s where id = :id")
	Student find(@Param("id")Long id);
}
