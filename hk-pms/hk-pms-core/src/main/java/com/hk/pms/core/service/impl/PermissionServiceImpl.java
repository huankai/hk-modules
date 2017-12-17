package com.hk.pms.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.core.repository.BaseRepository;
import com.hk.core.service.impl.BaseServiceImpl;
import com.hk.pms.core.domain.Permission;
import com.hk.pms.core.repository.PermissionRepostory;
import com.hk.pms.core.service.PermissionService;

@Service
public class PermissionServiceImpl extends BaseServiceImpl<Permission,String> implements PermissionService {

	@Autowired
	private PermissionRepostory permissionRepostory;

	@Override
	protected BaseRepository<Permission, String> getBaseRepository() {
		return permissionRepostory;
	}
	
	

}
