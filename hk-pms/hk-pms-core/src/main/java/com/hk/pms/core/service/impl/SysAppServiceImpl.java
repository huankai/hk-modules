package com.hk.pms.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.core.repository.BaseRepository;
import com.hk.core.service.impl.BaseServiceImpl;
import com.hk.pms.core.domain.SysApp;
import com.hk.pms.core.repository.SysAppRepostory;
import com.hk.pms.core.service.SysAppService;

/**
 * @author huangkai
 * @date 2017-11-24 15:57
 */
@Service
public class SysAppServiceImpl extends BaseServiceImpl<SysApp,String> implements SysAppService {

    @Autowired
    private SysAppRepostory sysAppDao;

    @Override
    protected BaseRepository<SysApp, String> getBaseRepository() {
    	return sysAppDao;
    }
}
