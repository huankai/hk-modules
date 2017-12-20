/**
 * 
 */
package com.hk.fs.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.hk.core.repository.BaseRepository;
import com.hk.core.service.impl.BaseServiceImpl;
import com.hk.fs.core.domain.FileInfo;
import com.hk.fs.core.repository.FileInfoRepository;
import com.hk.fs.core.service.FileInfoService;

/**
 * @author huangkai
 * @date 2017年12月19日下午12:42:17
 */
public class FileInfoServiceImpl extends BaseServiceImpl<FileInfo, String> implements FileInfoService {

	@Autowired
	private FileInfoRepository fileInfoRepository;

	@Override
	protected BaseRepository<FileInfo, String> getBaseRepository() {
		return fileInfoRepository;
	}

}
