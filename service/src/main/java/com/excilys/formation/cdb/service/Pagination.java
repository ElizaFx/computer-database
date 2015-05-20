package com.excilys.formation.cdb.service;

import java.util.List;

import org.springframework.validation.Errors;

import com.excilys.formation.cdb.persistence.IComputerDAO.OrderBy;

public class Pagination<T> {
	private Paginable<T> paginable;
	private int nbItems;

	private int page;
	private int limit;
	private int previous;
	private int next;
	private int pageMax;
	private int firstPage;
	private int lastPage;
	private String search;
	private OrderBy mOrderBy;
	private String orderBy;
	private int pageLimit = 5;

	public void setPageLimit(int pageLimit) {
		this.pageLimit = pageLimit;
	}

	private boolean asc;

	public Pagination() {
		mOrderBy = OrderBy.ID;
	}

	public Pagination(Paginable<T> paginable) {
		this(paginable, "", 0, 1, 10, 5);
	}

	public Pagination(Paginable<T> paginable, int nbItems, int curPage,
			int limit, int pageLimit) {
		this(paginable, "", nbItems, curPage, limit, pageLimit);
	}

	public Pagination(Paginable<T> paginable, String search, int nbItems,
			int curPage, int limit, int pageLimit) {
		this(paginable, search, nbItems, curPage, limit, pageLimit, OrderBy.ID,
				true);
	}

	public Pagination(Paginable<T> paginable, String search, int nbItems,
			int curPage, int limit, int pageLimit, OrderBy ob, boolean asc) {
		this.paginable = paginable;
		this.nbItems = nbItems;
		this.page = curPage;
		this.limit = limit;
		this.search = search == null ? "" : search;
		this.mOrderBy = ob == null ? OrderBy.ID : ob;
		this.asc = asc;
		if (this.limit < 1) {
			this.limit = 10;
		}

		if ((((this.page - 1) * this.limit) > this.nbItems) || (this.page < 1)) {
			this.page = 1;
		}

		pageMax = (this.nbItems / this.limit)
				+ ((this.nbItems % this.limit) == 0 ? 0 : 1);
		firstPage = Integer.max(this.page - 2, 1);
		lastPage = Integer.min(firstPage + (pageLimit - 1), pageMax);
		firstPage = lastPage - (pageLimit - 1);
		if (firstPage < 1) {
			firstPage = 1;
		}

		previous = (this.page - 1) < 1 ? 1 : this.page - 1;
		next = (this.page + 1) > pageMax ? pageMax : this.page + 1;
	}

	public int getNbItems() {
		return nbItems;
	}

	public int getPage() {
		return page;
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
		return previous == page;
	}

	public boolean isLast() {
		return lastPage == page;
	}

	public int getLastPage() {
		return lastPage;
	}

	public List<T> getPagination() {
		return paginable.pagination(search, limit, ((page - 1) * limit),
				mOrderBy, asc);
	}

	public String getSearch() {
		return search;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public String getObName() {
		return mOrderBy.name().toLowerCase();
	}

	public boolean isAsc() {
		return asc;
	}

	@Override
	public String toString() {
		return "Page [paginable=" + paginable + ", nbItems=" + nbItems
				+ ", curPage=" + page + ", limit=" + limit + ", previous="
				+ previous + ", next=" + next + ", pageMax=" + pageMax
				+ ", firstPage=" + firstPage + ", lastPage=" + lastPage
				+ ", search=" + search + ", orderBy=" + mOrderBy + ", asc="
				+ asc + "]";
	}

	public void validate(Errors errors) {
		mOrderBy = OrderBy.map(orderBy);

		if (limit < 1) {
			limit = 10;
		}

		if (page < 1) {
			page = 1;
		}

		if (search == null) {
			search = "";
		}
		nbItems = paginable.count(search);

		if ((((page - 1) * limit) > nbItems) || (page < 1)) {
			page = 1;
		}

		pageMax = (nbItems / limit) + ((nbItems % limit) == 0 ? 0 : 1);
		firstPage = Integer.max(page - 2, 1);
		lastPage = Integer.min(firstPage + (pageLimit - 1), pageMax);
		firstPage = lastPage - (pageLimit - 1);
		if (firstPage < 1) {
			firstPage = 1;
		}

		previous = (page - 1) < 1 ? 1 : page - 1;
		next = (page + 1) > pageMax ? pageMax : page + 1;
	}

	public void setPaginable(Paginable<T> paginable) {
		this.paginable = paginable;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public void setNbItems(int nbItems) {
		this.nbItems = nbItems;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public void setAsc(boolean asc) {
		this.asc = asc;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
}
