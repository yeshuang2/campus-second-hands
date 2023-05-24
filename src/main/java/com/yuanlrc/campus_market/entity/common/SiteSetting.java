package com.yuanlrc.campus_market.entity.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.yuanlrc.campus_market.annotion.ValidateEntity;

/**
 * 网站设置实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="ylrc_site_setting")
@EntityListeners(AuditingEntityListener.class)
public class SiteSetting extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ValidateEntity(required=true,requiredLeng=true,minLength=1,maxLength=64,errorRequiredMsg="网站名称不能为空!",errorMinLengthMsg="网站名称长度需大于1!",errorMaxLengthMsg="网站设置名称长度不能大于128!")
	@Column(name="site_name",nullable=false,length=128)
	private String siteName;//网站名称
	
	
	@ValidateEntity(required=true,requiredLeng=true,minLength=1,maxLength=256,errorRequiredMsg="网站URL地址不能为空!",errorMinLengthMsg="网站URL地址长度需大于1!",errorMaxLengthMsg="网站URL地址长度不能大于256!")
	@Column(name="site_url",nullable=false,length=256)
	private String siteUrl;//网站地址
	
	@ValidateEntity(required=true,requiredLeng=true,minLength=1,maxLength=256,errorRequiredMsg="网站logo不能为空!",errorMinLengthMsg="网站logo长度需大于1!",errorMaxLengthMsg="网站logo长度不能大于256!")
	@Column(name="logo_1",nullable=false,length=256)
	private String logo1;//网站logo1
	
	@ValidateEntity(required=true,requiredLeng=true,minLength=1,maxLength=256,errorRequiredMsg="网站logo不能为空!",errorMinLengthMsg="网站logo长度需大于1!",errorMaxLengthMsg="网站logo长度不能大于256!")
	@Column(name="logo_2",nullable=false,length=256)
	private String logo2;//网站logo2
	
	@ValidateEntity(required=true,requiredLeng=true,minLength=1,maxLength=256,errorRequiredMsg="网站公众号二维码不能为空!",errorMinLengthMsg="网站公众号二维码长度需大于1!",errorMaxLengthMsg="网站公众号二维码长度不能大于256!")
	@Column(name="qrcode",nullable=false,length=256)
	private String qrcode;//网站公众号二维码
	
	@ValidateEntity(required=true,requiredLeng=true,minLength=1,maxLength=256,errorRequiredMsg="网站版权信息不能为空!",errorMinLengthMsg="网站版权信息长度需大于1!",errorMaxLengthMsg="网站版权信息长度不能大于256!")
	@Column(name="all_rights",nullable=false,length=256)
	private String allRights;//网站版权

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getSiteUrl() {
		return siteUrl;
	}

	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}

	public String getLogo1() {
		return logo1;
	}

	public void setLogo1(String logo1) {
		this.logo1 = logo1;
	}

	public String getLogo2() {
		return logo2;
	}

	public void setLogo2(String logo2) {
		this.logo2 = logo2;
	}

	public String getQrcode() {
		return qrcode;
	}

	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}

	public String getAllRights() {
		return allRights;
	}

	public void setAllRights(String allRights) {
		this.allRights = allRights;
	}

	@Override
	public String toString() {
		return "SiteSetting [siteName=" + siteName + ", siteUrl=" + siteUrl
				+ ", logo1=" + logo1 + ", logo2=" + logo2 + ", qrcode="
				+ qrcode + ", allRights=" + allRights + "]";
	}

	
	
	
	
	
	

	
	
	
	
}
