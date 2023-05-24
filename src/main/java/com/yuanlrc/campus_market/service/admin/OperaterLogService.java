package com.yuanlrc.campus_market.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.stereotype.Service;

import com.yuanlrc.campus_market.bean.PageBean;
import com.yuanlrc.campus_market.dao.admin.OperaterLogDao;
import com.yuanlrc.campus_market.entity.admin.OperaterLog;
import com.yuanlrc.campus_market.entity.admin.User;
import com.yuanlrc.campus_market.util.SessionUtil;

/**
 * 后台操作类 数据库操作service
 * @author Administrator
 *
 */
@Service
public class OperaterLogService {
	
	@Autowired
	private OperaterLogDao operaterLogDao;
	
	/**
	 * 添加/修改操作日志，当id不为空时，修改，id为空时自动新增一条记录
	 * @param operaterLog
	 * @return
	 */
	public OperaterLog save(OperaterLog operaterLog){
		return operaterLogDao.save(operaterLog);
	}
	
	/**
	 * 获取指定条数的操作日志列表
	 * @param size
	 * @return
	 */
	public List<OperaterLog> findLastestLog(int size){
		return operaterLogDao.findLastestLog(size);
	}
	
	/**
	 * 根据id查询单条数据
	 * @param id
	 * @return
	 */
	public OperaterLog findById(Long id){
		return operaterLogDao.find(id);
	}
	
	/**
	 * 返回所有的记录
	 * @return
	 */
	public List<OperaterLog> findAll(){
		return operaterLogDao.findAll();
	}
	
	/**
	 * 删除单条记录
	 * @param id
	 */
	public void delete(Long id){
		operaterLogDao.deleteById(id);
	}
	
	/**
	 * 清空整张表
	 */
	public void deleteAll(){
		operaterLogDao.deleteAll();
	}
	
	/**
	 * 操作日志添加
	 * @param operater
	 * @param content
	 */
	public void add(String operater,String content){
		OperaterLog operaterLog = new OperaterLog();
		operaterLog.setOperator(operater);
		operaterLog.setContent(content);
		save(operaterLog);
	}
	
	/**
	 * 操作日志添加
	 * @param content
	 */
	public void add(String content){
		User loginedUser = SessionUtil.getLoginedUser();
		add(loginedUser == null ? "未知(获取登录用户失败)" : loginedUser.getUsername(), content);
	}
	
	/**
	 * 分页查找日志
	 * @param operaterLog
	 * @param pageBean
	 * @return
	 */
	public PageBean<OperaterLog> findList(OperaterLog operaterLog,PageBean<OperaterLog> pageBean){
		ExampleMatcher withMatcher = ExampleMatcher.matching().withMatcher("operator", GenericPropertyMatchers.contains());
		Example<OperaterLog> example = Example.of(operaterLog, withMatcher);
		Pageable pageable = PageRequest.of(pageBean.getCurrentPage()-1, pageBean.getPageSize());
		Page<OperaterLog> findAll = operaterLogDao.findAll(example, pageable);
		pageBean.setContent(findAll.getContent());
		pageBean.setTotal(findAll.getTotalElements());
		pageBean.setTotalPage(findAll.getTotalPages());
		return pageBean;
	}
	
	/**
	 * 操作日志总数
	 * @return
	 */
	public long total(){
		return operaterLogDao.count();
	}
}
