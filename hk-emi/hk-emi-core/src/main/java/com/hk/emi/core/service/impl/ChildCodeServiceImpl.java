/**
 * 
 */
package com.hk.emi.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.core.repository.BaseRepository;
import com.hk.core.service.impl.BaseServiceImpl;
import com.hk.emi.core.domain.ChildCode;
import com.hk.emi.core.repository.ChildCodeRepostory;
import com.hk.emi.core.service.ChildCodeService;

/**
 * @author kally
 * @date 2018年1月24日下午1:44:55
 */
@Service
public class ChildCodeServiceImpl extends BaseServiceImpl<ChildCode, String> implements ChildCodeService {

	@Autowired
	private ChildCodeRepostory childCodeRepostory;

	@Override
	protected BaseRepository<ChildCode, String> getBaseRepository() {
		return childCodeRepostory;
	}

}
