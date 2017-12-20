package com.hk.fs.core.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.hk.fs.core.domain.EntityHolder.FileInfoBase;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @author huangkai
 * @date 2017年12月19日下午12:36:44
 */
@Entity
@Table(name = "sys_fileinfo")
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class FileInfo extends FileInfoBase {

}
