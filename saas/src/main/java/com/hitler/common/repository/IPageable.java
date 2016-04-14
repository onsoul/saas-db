package com.hitler.common.repository;

/**
 * 分页接口
 *
 */
public interface IPageable {

	/**
	 * 当前页数，0-->
	 * @return
	 */
	int getPageNumber();
	/**
	 * 每页记录数，默认10
	 * @return
	 */
	int getPageSize();

	/**
	 * 返回排序条件
	 * @return
	 */
	Sorter getSort();

	/**
	 * 构造下一页
	 * @return
	 */
	IPageable next();

	/**
	 * 构造上一页或第一页
	 * @return
	 */
	IPageable previousOrFirst();
	
	/**
	 * 构造第一页
	 * @return
	 */
	IPageable first();
	/**
	 * 是否有上一页
	 * @return
	 */
	boolean hasPrevious();
}