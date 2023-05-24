package com.yuanlrc.campus_market.entity.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.yuanlrc.campus_market.annotion.ValidateEntity;

/**
 * 友情链接实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="ylrc_friend_link")
@EntityListeners(AuditingEntityListener.class)
public class FriendLink extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ValidateEntity(required=true,requiredLeng=true,minLength=1,maxLength=64,errorRequiredMsg="友情链接名称不能为空!",errorMinLengthMsg="友情链接名称长度需大于1!",errorMaxLengthMsg="友情链接名称长度不能大于64!")
	@Column(name="name",nullable=false,length=64)
	private String name;//友情链接名称
	
	
	@ValidateEntity(required=true,requiredLeng=true,minLength=1,maxLength=256,errorRequiredMsg="友情链接URL地址不能为空!",errorMinLengthMsg="友情链接URL地址长度需大于1!",errorMaxLengthMsg="友情链接URL地址长度不能大于256!")
	@Column(name="url",nullable=false,length=256)
	private String url;//友情链接地址
	
	@Column(name="sort",nullable=false,length=4)
	private Integer sort = 0;//显示顺序，默认升序排列,默认是0

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Override
	public String toString() {
		return "FriendLink [name=" + name + ", url=" + url + ", sort=" + sort
				+ "]";
	}
	
	

	
	
	
	
}
