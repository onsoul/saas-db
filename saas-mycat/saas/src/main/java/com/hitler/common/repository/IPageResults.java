package com.hitler.common.repository;

import java.util.Iterator;
import java.util.List;

/**
 * 分页列表接口
 *
 * @param <T>
 */
public interface IPageResults<T> {

	/**
	 * 总页数
	 * 
	 * @return
	 */
	int getTotalPages();
	
	/**
	 * 是否有下一页
	 * @return
	 */
	public boolean hasNext();

	/**
	 * 总记录数
	 * 
	 * @return
	 */
	long getTotalElements();

	/**
	 * 当前页数
	 * 
	 * @return
	 */
	public int getNumber();

	/**
	 * 是否第一页
	 * 
	 * @return
	 */
	public boolean isFirst();

	/**
	 * 是否最后一页
	 * 
	 * @return
	 */
	public boolean isLast();

	/**
	 * 返回所有结果
	 * 
	 * @return
	 */
	public List<T> getContent();

	/**
	 * 迭代结果列表
	 * 
	 * @return
	 */
	public Iterator<T> iterator();

	/**
	 * 返回排序对象
	 * 
	 * @return
	 */
	public Sorter getSort();
}
