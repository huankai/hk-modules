package com.hk.pms.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.core.repository.BaseRepository;
import com.hk.core.service.impl.BaseServiceImpl;
import com.hk.pms.core.domain.UserRole;
import com.hk.pms.core.repository.UserRoleRepostory;
import com.hk.pms.core.service.UserRoleService;


@Service
public class UserRoleServiceImpl extends BaseServiceImpl<UserRole,String> implements UserRoleService {
	
	@Autowired
	private UserRoleRepostory userRoleDao;

	@Override
	protected BaseRepository<UserRole, String> getBaseRepository() {
		return userRoleDao;
	}
	

}
