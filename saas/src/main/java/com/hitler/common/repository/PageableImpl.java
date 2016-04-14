package com.hitler.common.repository;

import java.io.Serializable;

/**
 * 分页对象，实现分页接口
 *
 */
public class PageableImpl implements IPageable, Serializable {

	private static final long serialVersionUID = 1232825578694716871L;

	/**
	 * 当前页数，0-->
	 */
	private final int page;

	/**
	 * 每页记录数，默认10
	 */
	private final int size;

	private Sorter sort = null;
	
	public PageableImpl(int page, int size) {
		if (page < 0) {
			throw new IllegalArgumentException(
					"Page index must not be less than zero!");
		}
		if (size < 1) {
			throw new IllegalArgumentException(
					"Page size must not be less than one!");
		}
		this.page = page;
		this.size = size;
	}

	public PageableImpl(int page, int size, Sorter sort) {
		this(page, size);
		this.sort = sort;
	}

	@Override
	public int getPageSize() {
		return size;
	}

	@Override
	public int getPageNumber() {
		return page;
	}

	@SuppressWarnings("unused")
	private int getOffset() {
		return page * size;
	}

	@Override
	public boolean hasPrevious() {
		return page > 0;
	}

	@Override
	public IPageable previousOrFirst() {
		return hasPrevious() ? previous() : first();
	}

	@Override
	public IPageable next() {
		return new PageableImpl(getPageNumber() + 1, getPageSize(), getSort());
	}

	public PageableImpl previous() {
		return getPageNumber() == 0 ? this : new PageableImpl(
				getPageNumber() - 1, getPageSize(), getSort());
	}

	@Override
	public IPageable first() {
		return new PageableImpl(0, getPageSize(), getSort());
	}

	@Override
	public Sorter getSort() {
		return sort;
	}
}
