package com.hitler.common.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 *  分页列表实现类
 *
 * @param <T>
 */
public class PageResultsImpl<T> implements IPageResults<T>, Serializable {

	private static final long serialVersionUID = 8262911041470573788L;
	private final long total;
	private List<T> content = new ArrayList<T>();
	private IPageable pageable = null;

	public PageResultsImpl(List<T> content, IPageable pageable) {
		this.content.addAll(content);
		this.pageable = pageable;
		this.total = content.size();
	}
	
	public PageResultsImpl(List<T> content, IPageable pageable, long totleSize) {
		this.content.addAll(content);
		this.pageable = pageable;
		this.total = totleSize;
	}

	@Override
	public int getTotalPages() {
		return getSize() == 0 ? 1 : (int) Math.ceil((double) total
				/ (double) getSize());
	}

	@Override
	public long getTotalElements() {
		return total;
	}

	@Override
	public int getNumber() {
		return pageable == null ? 0 : pageable.getPageNumber();
	}

	@Override
	public boolean isFirst() {
		return !hasPrevious();
	}

	@Override
	public boolean isLast() {
		return !hasNext();
	}

	@Override
	public boolean hasNext() {
		return getNumber() + 1 < getTotalPages();
	}

	@Override
	public List<T> getContent() {
		return Collections.unmodifiableList(content);
	}

	@Override
	public Iterator<T> iterator() {
		return content.iterator();
	}

	@Override
	public Sorter getSort() {
		return pageable == null ? null : pageable.getSort();
	}

	private int getSize() {
		return pageable == null ? 0 : pageable.getPageSize();
	}

	private boolean hasPrevious() {
		return getNumber() > 0;
	}
}
