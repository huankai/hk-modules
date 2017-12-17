package com.hk.pms.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.core.repository.BaseRepository;
import com.hk.core.service.impl.BaseServiceImpl;
import com.hk.pms.core.domain.Role;
import com.hk.pms.core.repository.RoleRepostory;
import com.hk.pms.core.service.RoleService;

/**
 * 
 * @author huangkai
 * @date 2017年10月23日下午1:38:43
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role,String> implements RoleService {

	@Autowired
	private RoleRepostory roleDao;

	@Override
	protected BaseRepository<Role, String> getBaseRepository() {
		return roleDao;
	}

	

}
