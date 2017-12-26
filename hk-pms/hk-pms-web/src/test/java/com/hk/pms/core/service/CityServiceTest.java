/**
 * 
 */
package com.hk.pms.core.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.hk.commons.fastjson.JsonUtils;
import com.hk.pms.PmsApplication;
import com.hk.pms.core.domain.City;

/**
 * @author huangkai
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { PmsApplication.class })
public class CityServiceTest {

	@Autowired
	private CityService cityService;

	/**
	 * Test method for
	 * {@link com.hk.core.service.BaseService#saveOrUpdate(java.lang.Object)}.
	 */
	@Test
	public void testSaveOrUpdate() {
//		City city = new City();
//		city.setCode("1");
//		city.setEnglishName("China");
//		city.setFullName("中国");
//		city.setShortName("中国");
//		city.setPostOffice("1");
//		cityService.saveOrUpdate(city);
		
		City parent = cityService.findOne("402881e66092f9a9016092f9cd320000");
		if(null != parent) {
				City city = new City();
				city.setCode("110100");
				city.setEnglishName("ShiXiaQu");
				city.setFullName("市辖区");
				city.setShortName("市辖区");
				city.setPostOffice("110101");
				city.setParent(parent);
				cityService.saveOrUpdate(city);
				city = new City();
				city.setCode("110100");
				city.setEnglishName("DongChengQu");
				city.setFullName("东城区");
				city.setShortName("东城");
				city.setPostOffice("110102");
				city.setParent(parent);
				cityService.saveOrUpdate(city);
		}
	}

	/**
	 * Test method for
	 * {@link com.hk.core.service.BaseService#findOne(java.io.Serializable)}.
	 */
	@Test
	@Transactional(readOnly = true)
	public void testFindOnePK() {
		City city = cityService.findOne("402881e66092f9a9016092f9cd320000");
		System.out.println(JsonUtils.toJSONString(city));
//		System.out.println(city.getFullName());
//		System.out.println(city.getParent());
//		List<City> childs = city.getChilds();
//		for (City child : childs) {
//			System.out.println(child.getFullName());
//		}
	}

	/**
	 * Test method for
	 * {@link com.hk.core.service.BaseService#getOne(java.io.Serializable)}.
	 */
	@Test
	public void testGetOne() {
	}

	/**
	 * Test method for
	 * {@link com.hk.core.service.BaseService#delete(java.io.Serializable)}.
	 */
	@Test
	public void testDeletePK() {
		cityService.delete("402881e66092f9a9016092f9cd320000");
	}

}
