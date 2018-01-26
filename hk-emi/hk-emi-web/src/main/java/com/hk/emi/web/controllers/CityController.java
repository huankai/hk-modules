/**
 * 
 */
package com.hk.emi.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hk.core.query.JpaQueryModel;
import com.hk.core.query.QueryPageable;
import com.hk.emi.core.domain.City;
import com.hk.emi.core.service.CityService;

/**
 * @author kally
 * @date 2018年1月24日上午9:45:58
 */
@Controller
@RequestMapping("city")
public class CityController {

	@Autowired
	private CityService cityService;

	@RequestMapping("list")
	public String list() {
		return "";
	}

	@RequestMapping("search")
	public String search(JpaQueryModel<City> query, ModelMap modelMap) {
		QueryPageable<City> page = cityService.queryForPage(query);
		modelMap.put("result", page);
		return null;
	}

}
