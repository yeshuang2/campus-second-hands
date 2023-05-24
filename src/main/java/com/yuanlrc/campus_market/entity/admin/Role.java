package com.yuanlrc.campus_market.entity.admin;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.yuanlrc.campus_market.annotion.ValidateEntity;
import com.yuanlrc.campus_market.entity.common.BaseEntity;

/**
 * 后台角色实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="ylrc_role")
@EntityListeners(AuditingEntityListener.class)
public class Role extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int ADMIN_ROLE_STATUS_ENABLE = 1;//角色状态正常可用
	public static final int ADMIN_ROLE_STATUS_UNABLE = 0;//角色状态不可用
	
	@ValidateEntity(required=true,requiredLeng=true,minLength=1,maxLength=18,errorRequiredMsg="角色名称不能为空!",errorMinLengthMsg="角色名称长度需大于1!",errorMaxLengthMsg="角色名称长度不能大于18!")
	@Column(name="name",nullable=false,length=18)
	private String name;//角色名称
	
	@ValidateEntity(required=false)
	@Column(name="authorities")
	@ManyToMany
	private List<Menu> authorities;//角色所对应的权限（菜单）列表
	
	@ValidateEntity(required=false)
	@Column(name="status",length=1)
	private int status = ADMIN_ROLE_STATUS_ENABLE;//角色状态,默认可用

	@ValidateEntity(required=false)
	@Column(name="remark",length=128)
	private String remark;//角色备注

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Menu> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Menu> authorities) {
		this.authorities = authorities;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Role [name=" + name + ", authorities=" + authorities
				+ ", status=" + status + ", remark=" + remark + "]";
	}
	
	
	
	
	
	
}
