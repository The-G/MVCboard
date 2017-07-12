package article.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import article.models.ArticleDAO;
import article.models.ArticleDAOImpl;

public class PageNation {
	private static Logger logger = 
			LoggerFactory.getLogger(PageNation.class);
	
	long pg = 1;						// 현재 페이지!!
	long pageCount = 0;			        // 전체 게시물 수
	long totalCount = 0;				// 전체 페이지 수
	long blockSize = 10;				// 한 페이지에 출력할 페이지 수
	int pageSize = 10;		// 한 페이지에 출력할 게시물 수 
	long startPage = 0;			// 페이지 블럭 시작 페이지
	long endPage = 0;	// 페이지 블럭 끝 페이지

	long startnum = 0;
	long endnum = 0;
	private StringBuffer display = new StringBuffer();
	
	public PageNation(long pg) throws Exception {
		this.pg = pg;
		startnum = (pg - 1) * pageSize + 1;
		endnum = pg * pageSize;
		startPage = ((pg-1) / blockSize) * blockSize +1;			// 페이지 블럭 시작 페이지
		endPage = ((pg-1) / blockSize) * blockSize + blockSize;	// 페이지 블럭 끝 페이지
		ArticleDAO articleDAO = ArticleDAOImpl.getInstance();
		totalCount = articleDAO.getTotalCount();
		pageCount = totalCount / pageSize; // 전체 페이지 수
		if (totalCount % pageSize != 0) { pageCount++; }
		if (pageCount < endPage) { endPage = pageCount; }
		
		if (startPage == 1) {
			display.append(" Prev ");
		} else {
			display.append(" <a href='list?pg=" + (startPage - 1) + "'>Prev</a> ");
		}		
		for(long p = startPage; p <= endPage; p++) {
			if (p == pg) {
				display.append(" " + p + " ");
			} else {
				display.append(" <a href='list?pg="+p+"'> " + p + " </a> ");
			}
		}
		if (endPage == pageCount) {
			display.append(" Next ");
		} else {
			display.append(" <a href='list?pg=" + (endPage + 1) + "'>Next</a> ");
		}
	}

	public long getPg() {
		return pg;
	}

	public void setPg(long pg) {
		this.pg = pg;
	}

	public long getPageCount() {
		return pageCount;
	}

	public void setPageCount(long pageCount) {
		this.pageCount = pageCount;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public long getBlockSize() {
		return blockSize;
	}

	public void setBlockSize(long blockSize) {
		this.blockSize = blockSize;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getStartPage() {
		return startPage;
	}

	public void setStartPage(long startPage) {
		this.startPage = startPage;
	}

	public long getEndPage() {
		return endPage;
	}

	public void setEndPage(long endPage) {
		this.endPage = endPage;
	}

	public long getStartnum() {
		return startnum;
	}

	public void setStartnum(long startnum) {
		this.startnum = startnum;
	}

	public long getEndnum() {
		return endnum;
	}

	public void setEndnum(long endnum) {
		this.endnum = endnum;
	}

	public StringBuffer getDisplay() {
		return display;
	}

	public void setDisplay(StringBuffer display) {
		this.display = display;
	}

	@Override
	public String toString() {
		return "PageNation [pg=" + pg + ", pageCount=" + pageCount + ", totalCount=" + totalCount + ", blockSize="
				+ blockSize + ", pageSize=" + pageSize + ", startPage=" + startPage + ", endPage=" + endPage
				+ ", startnum=" + startnum + ", endnum=" + endnum + ", display=" + display + "]";
	}
}
