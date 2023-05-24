package com.yuanlrc.campus_market.entity.admin;

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
 * 后台菜单实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="ylrc_menu")
@EntityListeners(AuditingEntityListener.class)
public class Menu extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ValidateEntity(required=true,requiredLeng=true,minLength=1,maxLength=18,errorRequiredMsg="菜单名称不能为空!",errorMinLengthMsg="菜单名称长度需大于1!",errorMaxLengthMsg="菜单名称长度不能大于18!")
	@Column(name="name",nullable=false,length=18)
	private String name;//菜单名称
	
	@ManyToOne
	@JoinColumn(name="parent_id")
	private Menu parent;//菜单父分类
	
	@ValidateEntity(required=false)
	@Column(name="url",length=128)
	private String url;//菜单url

	@ValidateEntity(required=false)
	@Column(name="icon",length=32)
	private String icon;//菜单图标icon
	
	@Column(name="sort",nullable=false,length=4)
	private Integer sort = 0;//菜单顺序，默认升序排列,默认是0

	@Column(name="is_bitton",nullable=false)
	private boolean isButton = false;//是否是按钮
	
	@Column(name="is_show",nullable=false)
	private boolean isShow = true;//是否显示
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Menu getParent() {
		return parent;
	}

	public void setParent(Menu parent) {
		this.parent = parent;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	
	
	public boolean isButton() {
		return isButton;
	}

	public void setButton(boolean isButton) {
		this.isButton = isButton;
	}

	public boolean isShow() {
		return isShow;
	}

	public void setShow(boolean isShow) {
		this.isShow = isShow;
	}

	@Override
	public String toString() {
		return "Menu [name=" + name + ", parent=" + parent + ", url=" + url
				+ ", icon=" + icon + ", sort=" + sort + ", isButton="
				+ isButton + ", isShow=" + isShow + "]";
	}

	

	
	
	
}
