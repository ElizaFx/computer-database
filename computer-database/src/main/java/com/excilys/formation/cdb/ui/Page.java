package com.excilys.formation.cdb.ui;

import java.util.List;

import com.excilys.formation.cdb.service.Paginable;

public class Page<T> {
	private final Paginable<T> paginable;
	private int nbItems;
	private int curPage;
	private int limit;
	private int previous;
	private int next;
	private int pageMax;
	private int firstPage;
	private int lastPage;

	public Page(Paginable<T> paginable) {
		this(paginable, 0, 1, 10, 5);
	}

	public Page(Paginable<T> paginable, int nbItems, int curPage, int limit,
			int pageLimit) {
		this.paginable = paginable;
		this.nbItems = nbItems;
		this.curPage = curPage;
		this.limit = limit;

		if (this.limit < 1) {
			this.limit = 10;
		}

		if ((((this.curPage - 1) * this.limit) > this.nbItems)
				|| (this.curPage < 1)) {
			this.curPage = 1;
		}

		pageMax = (this.nbItems / this.limit)
				+ ((this.nbItems % this.limit) == 0 ? 0 : 1);
		firstPage = Integer.max(this.curPage - 2, 1);
		lastPage = Integer.min(firstPage + (pageLimit - 1), pageMax);
		firstPage = lastPage - (pageLimit - 1);
		if (firstPage < 1) {
			firstPage = 1;
		}

		previous = (this.curPage - 1) < 1 ? 1 : this.curPage - 1;
		next = (this.curPage + 1) > pageMax ? pageMax : this.curPage + 1;
	}

	public int getNbItems() {
		return nbItems;
	}

	public int getCurPage() {
		return curPage;
	}

	public int getLimit() {
		return limit;
	}

	public int getPrevious() {
		return previous;
	}

	public int getNext() {
		return next;
	}

	public int getPageMax() {
		return pageMax;
	}

	public int getFirstPage() {
		return firstPage;
	}

	public boolean isFirst() {
		return previous == curPage;
	}

	public boolean isLast() {
		return lastPage == curPage;
	}

	public int getLastPage() {
		return lastPage;
	}

	public List<T> getPage() {
		return paginable.pagination(limit, ((curPage - 1) * limit));
	}
}
