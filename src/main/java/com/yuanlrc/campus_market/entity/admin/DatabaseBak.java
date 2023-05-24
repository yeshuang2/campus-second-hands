package com.yuanlrc.campus_market.entity.admin;
/**
 * 数据库备份记录实体类
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.yuanlrc.campus_market.entity.common.BaseEntity;

@Entity
@Table(name="ylrc_database_bak")
@EntityListeners(AuditingEntityListener.class)
public class DatabaseBak extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="filename",nullable=false,length=200)
	private String filename;//备份的文件名
	
	@Column(name="filepath",nullable=false,length=128)
	private String filepath;//备份的文件路径

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	@Override
	public String toString() {
		return "DatabaseBak [filename=" + filename + ", filepath=" + filepath
				+ "]";
	}

	
	
	
	
	
	
	
	
}
