/**
 * 
 */
package com.hk.pms.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.core.repository.BaseRepository;
import com.hk.core.service.impl.BaseServiceImpl;
import com.hk.pms.core.domain.City;
import com.hk.pms.core.repository.CityRepository;
import com.hk.pms.core.service.CityService;

/**
 * @author huangkai
 *
 */
@Service
public class CityServiceImpl extends BaseServiceImpl<City, String> implements CityService {

	@Autowired
	private CityRepository cityRepository;

	@Override
	protected BaseRepository<City, String> getBaseRepository() {
		return cityRepository;
	}
}
