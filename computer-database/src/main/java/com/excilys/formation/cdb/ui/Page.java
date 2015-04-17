package com.excilys.formation.cdb.ui;

import java.util.ArrayList;
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
	private List<Integer> lPages;

	public Page(Paginable<T> paginable) {
		this(paginable, 0, 1, 10, 5);
	}

	public Page(Paginable<T> paginable, int nbItems, int curPage, int limit,
			int pageLimit) {
		this.paginable = paginable;
		this.nbItems = nbItems;
		this.curPage = curPage;
		this.limit = limit;

		lPages = new ArrayList<Integer>();

		if (limit < 1) {
			limit = 10;
		}

		if (((curPage - 1) * limit) > nbItems) {
			curPage = 1;
		}

		pageMax = (nbItems / limit) + 1;
		firstPage = ((curPage - 2) < 1) ? 1 : (curPage - 2);
		lastPage = (firstPage + (pageLimit - 1)) > pageMax ? pageMax
				: (firstPage + (pageLimit - 1));
		firstPage = lastPage - (pageLimit - 1);

		System.out.println(firstPage + " " + pageMax + " " + lastPage + " "
				+ curPage + " " + limit);

		for (; firstPage <= lastPage; firstPage++) {
			lPages.add(firstPage);
		}

		previous = (curPage - 1) < 1 ? 1 : curPage - 1;
		next = (curPage + 1) > pageMax ? pageMax : curPage + 1;
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

	public List<Integer> getlPages() {
		return lPages;
	}

	public List<T> getPage() {
		return paginable.pagination(limit, ((curPage - 1) * limit));
	}
}
