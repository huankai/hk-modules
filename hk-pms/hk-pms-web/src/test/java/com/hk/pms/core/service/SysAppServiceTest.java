/**
 * 
 */
package com.hk.pms.core.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hk.pms.PmsApplication;
import com.hk.pms.core.domain.SysApp;

/**
 * @author huangkai
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { PmsApplication.class })
public class SysAppServiceTest {

	@Autowired
	private SysAppService sysAppService;

	/**
	 * Test method for
	 * {@link com.hk.core.service.BaseService#saveOrUpdate(java.lang.Object)}.
	 */
	@Test
	public void testSaveOrUpdate() {
		SysApp app = new SysApp();
		app.setAppCode("HK_PMS");
		app.setAppIcon("icon_");
		app.setAppIp("127.0.0.1");
		app.setAppPort(80);
		app.setAppName("统一权限管理系统");
		app.setLoginUrl("/login");
		app.setStatus(1);
		sysAppService.saveOrUpdate(app);
	}

	/**
	 * Test method for
	 * {@link com.hk.core.service.BaseService#findOne(java.io.Serializable)}.
	 */
	@Test
	public void testFindOnePK() {
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
	}

}
