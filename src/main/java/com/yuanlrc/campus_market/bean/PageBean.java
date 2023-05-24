package com.yuanlrc.campus_market.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * 分页信息封装类
 * @author Administrator
 *
 */
@Component
public class PageBean<T> {
	
	private int currentPage = 1;//当前页码
	
	private int pageSize = 10;//每页显示数量，默认十条
	
	private long total = 0;//总数量
	
	private int totalPage;//总页数
	
	private int offset = 0;//数据库游标
	
	private List<T> content;
	
	private int showPageSize = 5;//显示在页面可快速跳转的页码个数
	
	private List<Integer> currentShowPage = new ArrayList<Integer>();//当前显示在页面快速跳转的页码

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public int getShowPageSize() {
		return showPageSize;
	}

	public void setShowPageSize(int showPageSize) {
		this.showPageSize = showPageSize;
	}

	public List<Integer> getCurrentShowPage() {
		//首先当前页往前显示currentShowPage页
		for(int i = currentPage - 1;i > 0; i--){
			currentShowPage.add(i);
			if(i <= (currentPage - showPageSize)){
				break;
			}
		}
		//接下来当前页往后显示currentShowPage页
		for(int i = currentPage;i <= totalPage; i++){
			currentShowPage.add(i);
			if(i >= totalPage){
				break;
			}
			if(i >= (showPageSize + currentPage)){
				break;
			}
		}
		Collections.sort(currentShowPage);
		return currentShowPage;
	}

	public void setCurrentShowPage(List<Integer> currentShowPage) {
		this.currentShowPage = currentShowPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getOffset() {
		offset = (currentPage - 1) * pageSize;
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	
}
