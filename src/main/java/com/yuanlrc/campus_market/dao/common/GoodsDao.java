package com.yuanlrc.campus_market.dao.common;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * 物品数据库操作dao
 */
import com.yuanlrc.campus_market.entity.common.Goods;
import com.yuanlrc.campus_market.entity.common.Student;
@Repository
public interface GoodsDao extends JpaRepository<Goods, Long>,JpaSpecificationExecutor<Goods> {
	/**
	 * 根据id获取
	 * @return
	 */
	@Query("select g from Goods g where id = :id")
	Goods find(@Param("id")Long id);
	
	/**
	 * 根据学生查询
	 * @param student
	 * @return
	 */
	List<Goods> findByStudent(Student student);
	
	/**
	 * 根据学生id和商品id查询
	 * @param id
	 * @param userId
	 * @return
	 */
	@Query("select g from Goods g where id = :id and g.student.id = :studentId")
	Goods find(@Param("id")Long id,@Param("studentId")Long studentId);
	
	/**
	 * 根据物品分类查询物品列表
	 * @param cids
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	@Query(value="SELECT * from ylrc_goods where goods_category_id IN :cids and `status` = 1 ORDER BY create_time desc,flag desc,recommend desc limit :offset,:pageSize",nativeQuery=true)
	List<Goods> findList(@Param("cids")List<Long> cids,@Param("offset")Integer offset,@Param("pageSize")Integer pageSize);
	
	/**
	 * 获取根据分类搜索的总条数
	 * @param cids
	 * @return
	 */
	@Query(value="SELECT count(*) from ylrc_goods where goods_category_id IN :cids and `status` = 1 ",nativeQuery=true)
	Long getTotalCount(@Param("cids")List<Long> cids);
	
	/**
	 * 获取指定状态的物品总数
	 * @param status
	 * @return
	 */
	@Query("SELECT count(g) from Goods g where g.status = :status ")
	Long getTotalCount(@Param("status")Integer status);
	
	/**
	 * 根据物品名称模糊搜索
	 * @param name
	 * @return
	 */
	@Query(value="select * from ylrc_goods where name like %:name%",nativeQuery=true)
	List<Goods> findListByName(@Param("name")String name);
}
