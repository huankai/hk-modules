package com.hk.emi.core.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

import com.alibaba.fastjson.annotation.JSONField;
import com.hk.core.domain.AbstractAuditable;
import com.hk.core.domain.AbstractUUIDPersistable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author huangkai
 * @date 2017年10月31日下午1:08:08
 */
@SuppressWarnings("serial")
public class ModelHolder {

	private ModelHolder() {

	}
	
	@Data
	@EqualsAndHashCode(callSuper = false)
	@MappedSuperclass
	public static class BaseCodeBase extends AbstractUUIDPersistable {

		/**
		 *
		 */
		@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
		@JoinColumn(name = "base_code_id", referencedColumnName = "id")
		private List<ChildCode> childCodes;

		@Column(name = "code_name")
		private String codeName;

		@Column(name = "remark")
		private String remark;

	}
	
	@Data
	@EqualsAndHashCode(callSuper = false)
	@MappedSuperclass
	public static class ChildCodeBase extends AbstractUUIDPersistable {

		@ManyToOne(optional = false)
		private BaseCode sysBaseCode;

		@Column(name = "child_code")
		private String childCode;

		@Column(name = "chde_name")
		private String codeName;

		@Column(name = "status")
		private Integer status;

		@Column(name = "conditions")
		private String conditions;

		/**
		 * 排序
		 */
		@Column(name = "sort")
		private Integer sort;

		@Column(name = "remark")
		private String remark;

	}
	

	@Data
	@EqualsAndHashCode(callSuper = false)
	@MappedSuperclass
	public static class CityBase extends AbstractAuditable {

		/**
		 * 行政代码
		 */
		@Column(name = "code")
		private String code;

		/**
		 * 全称
		 */
		@Column(name = "full_name")
		private String fullName;

		/**
		 * 简称
		 */
		@Column(name = "short_name")
		private String shortName;

		/**
		 * 英文名
		 */
		@Column(name = "english_name")
		private String englishName;

		/**
		 * 邮编
		 */
		@Column(name = "post_office")
		private String postOffice;

		/**
		 * 上级
		 */
		@ManyToOne(fetch = FetchType.LAZY, optional = false)
		@JSONField(serialize = false)
		private City parent;

		/**
		 * 子级
		 */
		@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true,cascade = {CascadeType.ALL})
		@JoinColumn(name = "parent_id", referencedColumnName = "id")
		private List<City> childs;

	}
	
}
