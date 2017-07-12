package article.controllers;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.models.ArticleDAO;
import article.models.ArticleDAOImpl;
import article.models.ArticleVO;
import article.models.PageVO;

public class ArticleList_kjh extends AbstractController {

	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {

		long pg = 1;		// 현재 페이지!!
		try {
			pg = Long.parseLong(request.getParameter("pg"));
		} catch(NumberFormatException e) {}
		
		int pageSize = 10;		// 한 페이지에 출력할 게시물 수 
		long startnum = (pg - 1) * pageSize + 1;	// 출력페이지 시작번호
		long endnum = pg * pageSize;				// 출력페이지 끝번호
		long blockSize = 10;											// 한 페이지에 출력할 페이지 수
		long startPage = ((pg-1) / blockSize) * blockSize +1;			// 페이지 블럭 시작 페이지
		long endPage = ((pg-1) / blockSize) * blockSize + blockSize;	// 페이지 블럭 끝 페이지
		long pageCount = 0;				   // 전체 게시물 수
		long totalCount = 0;						// 전체 페이지 수

		PageVO pageVO = new PageVO(startnum, endnum);
	
	
		ArticleDAO articleDAO = ArticleDAOImpl.getInstance(); // DB단에 대한 비즈니스 로직 분리

		try {
			totalCount =  articleDAO.getTotalCount();
		} catch (Exception e1) {
			e1.printStackTrace();			//왜 try catch 묶어야 하지..?! => DAO에서는 예외처리를 하지 않고 
																		   // 사용자에게 날리지!!!
		}						            // 전체 게시물 개수 조회
		
		pageCount = totalCount / pageSize; // 전체 페이지 수
		if (totalCount % pageSize != 0) { pageCount++; }
		if (pageCount < endPage) { endPage = pageCount; }
		
		ModelAndView mav = new ModelAndView();
		try {
			List<ArticleVO> list = articleDAO.getArticleList(pageVO);
			mav.setViewName("/WEB-INF/views/article/list.jsp");
			mav.addObject("list", list);
			mav.addObject("startPage", startPage);
			mav.addObject("endPage", endPage);
			mav.addObject("pageCount", pageCount);
			mav.addObject("pg", pg);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mav.setViewName("/WEB-INF/views/result.jsp");
			mav.addObject("msg", "게시물 리스트 출력에러 \\n 관리자에게 문의하세요!!");
			mav.addObject("url", "test");
			
		}
		return mav;
	}

}
