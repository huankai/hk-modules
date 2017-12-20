package com.hk.fs.core.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.hk.core.domain.AbstractAuditable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
public class EntityHolder {

	@MappedSuperclass
	@Data
	@EqualsAndHashCode(callSuper = false)
	public static class FileInfoBase extends AbstractAuditable {

		@Column(name = "file_name")
		private String fileName;

		@Column(name = "file_path")
		private String filePath;

		@Column(name = "file_size")
		private Long fileSize;

		@Column(name = "ext")
		private String ext;

	}

}
