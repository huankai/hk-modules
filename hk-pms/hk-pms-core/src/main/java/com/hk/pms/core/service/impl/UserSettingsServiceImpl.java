package com.hk.pms.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.core.repository.BaseRepository;
import com.hk.core.service.impl.BaseServiceImpl;
import com.hk.pms.core.domain.UserSetting;
import com.hk.pms.core.repository.UserSettingsRepostory;
import com.hk.pms.core.service.UserSettingsService;

/**
 * @author huangkai
 * @date 2017-11-24 15:54
 */
@Service
public class UserSettingsServiceImpl extends BaseServiceImpl<UserSetting,String> implements UserSettingsService {

    @Autowired
    private UserSettingsRepostory userSettingsRepostory;

    @Override
    protected BaseRepository<UserSetting, String> getBaseRepository() {
    	return userSettingsRepostory;
    }
}
