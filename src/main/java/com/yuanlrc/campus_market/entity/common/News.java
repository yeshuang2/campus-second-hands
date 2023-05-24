package com.yuanlrc.campus_market.entity.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.yuanlrc.campus_market.annotion.ValidateEntity;

/**
 * 新闻实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="ylrc_news")
@EntityListeners(AuditingEntityListener.class)
public class News extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ValidateEntity(required=true,requiredLeng=true,minLength=1,maxLength=64,errorRequiredMsg="新闻标题不能为空!",errorMinLengthMsg="新闻标题长度需大于1!",errorMaxLengthMsg="新闻标题长度不能大于64!")
	@Column(name="title",nullable=false,length=64)
	private String title;//新闻标题
	
	
	@ValidateEntity(required=true,requiredLeng=true,minLength=1,maxLength=10000,errorRequiredMsg="新闻内容不能为空!",errorMinLengthMsg="新闻内容长度需大于1!",errorMaxLengthMsg="新闻内容长度不能大于10000!")
	@Column(name="content",nullable=false,length=10024)
	private String content;//新闻内容
	
	@Column(name="view_number",nullable=false,length=8)
	private Integer viewNumber = 0;

	@Column(name="sort",nullable=false,length=4)
	private Integer sort = 0;//分类顺序，默认升序排列,默认是0
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getViewNumber() {
		return viewNumber;
	}

	public void setViewNumber(Integer viewNumber) {
		this.viewNumber = viewNumber;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Override
	public String toString() {
		return "News [title=" + title + ", content=" + content
				+ ", viewNumber=" + viewNumber + ", sort=" + sort + "]";
	}

	
	
	
	
}
