package article.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import article.models.ArticleDAO;
import article.models.ArticleDAOImpl;
import article.models.ArticleVO;

public class ArticleDelete extends AbstractController{

	private static Logger logger = Logger.getLogger(ArticleUpdate.class);

	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		ArticleDAO articleDAO = ArticleDAOImpl.getInstance(); 
		try {
			long no = Long.parseLong(request.getParameter("no")); 
			ArticleVO articleVO = articleDAO.getDetail(no);
			return new ModelAndView("/WEB-INF/views/article/delete.jsp", "articleVO", articleVO);
		} catch (Exception e) {
			logger.info(e.toString()); 
			ModelAndView mav = new ModelAndView("/WEB-INF/views/result.jsp");
			mav.addObject("msg", e.getMessage()); 
			mav.addObject("url", "list");
			return mav;
		}
	}

}
