package com.yuanlrc.campus_market.entity.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.yuanlrc.campus_market.annotion.ValidateEntity;
import com.yuanlrc.campus_market.entity.common.BaseEntity;

/**
 * 物品分类实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="ylrc_goods_category")
@EntityListeners(AuditingEntityListener.class)
public class GoodsCategory extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ValidateEntity(required=true,requiredLeng=true,minLength=1,maxLength=18,errorRequiredMsg="分类名称不能为空!",errorMinLengthMsg="分类名称长度需大于1!",errorMaxLengthMsg="分类名称长度不能大于18!")
	@Column(name="name",nullable=false,length=18)
	private String name;//分类名称
	
	@ManyToOne
	@JoinColumn(name="parent_id")
	private GoodsCategory parent;//分类父分类
	
	@ValidateEntity(required=false)
	@Column(name="icon",length=32)
	private String icon;//分类图标icon
	
	@Column(name="sort",nullable=false,length=4)
	private Integer sort = 0;//分类顺序，默认升序排列,默认是0

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GoodsCategory getParent() {
		return parent;
	}

	public void setParent(GoodsCategory parent) {
		this.parent = parent;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Override
	public String toString() {
		return "GoodsCategory [name=" + name + ", parent=" + parent + ", icon="
				+ icon + ", sort=" + sort + "]";
	}

	
	

	
	
	
}
