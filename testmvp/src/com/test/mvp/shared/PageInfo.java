
package com.test.mvp.shared;

import java.io.Serializable;

public class PageInfo implements Serializable {
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= -1044118558332802750L;
	
	public static final int		FIRST				= 0;
	
	public static final int		PREV				= 1;
	
	public static final int		NEXT				= 2;
	
	public static final int		LAST				= 3;
	
	private int					pageSize			= 0;
	
	private int					currentPage			= 1;
	
	private int					noOfPages			= 0;
	
	private int					currentAction		= FIRST;
	
	public int getPageSize() {

		return pageSize;
	}
	
	public void setPageSize( int pageSize ) {

		this.pageSize = pageSize;
	}
	
	public int getCurrentPage() {

		return currentPage;
	}
	
	public void setCurrentPage( int currentPage ) {

		this.currentPage = currentPage;
	}
	
	public int getNoOfPages() {

		return noOfPages;
	}
	
	public void setNoOfPages( int noOfPages ) {

		this.noOfPages = noOfPages;
	}
	
	public int getCurrentAction() {

		return currentAction;
	}
	
	public void setCurrentAction( int currentAction ) {

		this.currentAction = currentAction;
	}
	
	public void setFirstPage() {

		currentPage = 1;
	}
	
	public void setNextPage() {

		currentPage++;
	}
	
	public void setPrevPage() {

		currentPage--;
	}
	
	public void setLastPage( int lastPageNo ) {

		currentPage = lastPageNo;
	}
	
}
