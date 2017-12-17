package com.hk.pms.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.core.repository.BaseRepository;
import com.hk.core.service.impl.BaseServiceImpl;
import com.hk.pms.core.domain.RolePermission;
import com.hk.pms.core.repository.RolePermissionRepostory;
import com.hk.pms.core.service.RolePermissionService;


@Service
public class RolePermissionServiceImpl extends BaseServiceImpl<RolePermission,String> implements RolePermissionService {

	@Autowired
	private RolePermissionRepostory rolePermissionDao;

	@Override
	protected BaseRepository<RolePermission, String> getBaseRepository() {
		return rolePermissionDao;
	}
	

}
