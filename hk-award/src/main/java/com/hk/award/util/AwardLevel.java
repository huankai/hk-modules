package com.hk.award.util;

import com.hk.commons.annotations.EnumDisplay;

/**
 * 中奖等级
 * 
 * @author kally
 * @date 2018年1月15日下午5:47:12
 */
public enum AwardLevel {

	@EnumDisplay(value = "一等奖", order = 1)
	ONE,

	@EnumDisplay(value = "二等奖", order = 2)
	TWO,

	@EnumDisplay(value = "三等奖", order = 3)
	THREE;
}
