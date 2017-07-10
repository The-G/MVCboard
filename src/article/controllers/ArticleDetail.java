package article.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import article.models.ArticleDAO;
import article.models.ArticleDAOImpl;
import article.models.ArticleVO;

public class ArticleDetail extends AbstractController {

	private static Logger logger = Logger.getLogger(ArticleDetail.class);
	
	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {

		ArticleDAO articleDAO = ArticleDAOImpl.getInstance(); // DB단에 대한 비즈니스 로직 분리

		try {
			long no = Long.parseLong(request.getParameter("no")); // 예외처리 위해서 여기에 넣음!!
			articleDAO.updateViewcount(no);
			ArticleVO articleVO = articleDAO.getDetail(no);
			return new ModelAndView("/WEB-INF/views/article/detail.jsp", "articleVO", articleVO); //list에서 한 거랑 같은거다!!
		} catch (Exception e) {
			logger.info(e.toString()); // e.getMessage() 도 가능하고!!
			ModelAndView mav = new ModelAndView("/WEB-INF/views/result.jsp");
			mav.addObject("msg", e.getMessage()); //왜 여기 메세지가 adaoimpl 의 것이 나오지?!
			mav.addObject("url", "list");
			return mav;
		}

	}
}
