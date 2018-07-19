package com.cn.taskManager.common;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 实体基类
 * @author leo.aqing
 *
 */
@Entity
public class BaseEntity /* extends JSONObject*/ implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "Id")
    private String id;

    @Transient
    private Integer page = 1;

    @Transient
    private Integer rows = 10;

	/*@Column(name="enable_")
	private Integer enable;
	@Column(name="remark_")
	private String remark;
	private Long createBy;
	private Date createTime;
	private Long updateBy;
	private Date updateTime;*/


	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	public Integer getEnable() {
		return enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}*/

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}
}
