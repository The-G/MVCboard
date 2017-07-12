package article.controllers;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.models.ArticleDAO;
import article.models.ArticleDAOImpl;
import article.models.ArticleVO;
import article.models.PageVO;

public class ArticleList extends AbstractController {

	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {

		long pg = 1;		// 현재 페이지!!
		try {
			pg = Long.parseLong(request.getParameter("pg"));
		} catch(NumberFormatException e) {}
		ArticleDAO articleDAO = ArticleDAOImpl.getInstance(); // DB단에 대한 비즈니스 로직 분리
		
		ModelAndView mav = new ModelAndView();
		try {
			PageNation pageNation = new PageNation(pg);
			List<ArticleVO> list = articleDAO.getArticleList(pageNation);
			mav.setViewName("/WEB-INF/views/article/list.jsp");
			mav.addObject("list", list);
			mav.addObject("pageNation", pageNation);
			
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
