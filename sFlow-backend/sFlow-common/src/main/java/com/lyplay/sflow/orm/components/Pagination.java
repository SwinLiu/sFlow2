package com.lyplay.sflow.orm.components;

import java.util.Collections;
import java.util.List;

public class Pagination<T> {

	public static final int DEFAULT_PER_PAGE = 10;
	public static final int DEFAULT_CURRENT_PAGE = 1;

	private int numPerPage;

	private long totalRows;

	private long totalPages;

	private int currentPage;

	private long startIndex;

	private long lastIndex;

	private List<T> resultList;
	
	public Pagination() {
		super();
		this.numPerPage = DEFAULT_PER_PAGE;
		this.totalRows = 0;
		this.totalPages = 0;
		this.currentPage = DEFAULT_CURRENT_PAGE;
		this.startIndex = 0;
		this.lastIndex = 0;
		this.resultList = Collections.emptyList();
	}
	
	public Pagination(int numPerPage, long totalRows, int currentPage, List<T> list) {
		super();
		setNumPerPage(numPerPage);
		setTotalRows(totalRows);
		setCurrentPage(currentPage);
		setResultList(list);
		setTotalPages();
		setStartIndex();
		setLastIndex();
	}

	public int getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}

	public long getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(long totalRows) {
		this.totalRows = totalRows;
	}

	public long getTotalPages() {
		return totalPages;
	}

	public void setTotalPages() {
		if (totalRows % numPerPage == 0) {
			this.totalPages = totalRows / numPerPage;
		} else {
			this.totalPages = (totalRows / numPerPage) + 1;
		}
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public long getStartIndex() {
		return startIndex;
	}

	public void setStartIndex() {
		this.startIndex = (currentPage - 1) * numPerPage + 1;
	}

	public long getLastIndex() {
		return lastIndex;
	}

	public void setLastIndex() {
		if (totalRows < numPerPage) {
			this.lastIndex = totalRows;
		} else if ((totalRows % numPerPage == 0)
				|| (totalRows % numPerPage != 0 && currentPage < totalPages)) {
			this.lastIndex = currentPage * numPerPage;
		} else if (totalRows % numPerPage != 0 && currentPage == totalPages) {
			this.lastIndex = totalRows;
		}
	}

	public List<T> getResultList() {
		return resultList;
	}

	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}

}
