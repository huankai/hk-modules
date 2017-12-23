/**
 * 
 */
package com.hk.core.web.query;

import java.util.List;

/**
 * @author huangkai
 * @date 2017年12月23日下午3:39:51
 */
public interface QueryResultPageable<T> {

	/**
	 * 
	 * @return
	 */
	int getPageIndex();

	/**
	 * 
	 * @return
	 */
	int getPageSize();

	/**
	 * 
	 * @return
	 */
	long getTotalRowCount();

	/**
	 * 
	 * @return
	 */
	List<T> getData();

}
