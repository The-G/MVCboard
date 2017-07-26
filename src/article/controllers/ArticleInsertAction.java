package article.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import article.models.ArticleDAO;
import article.models.ArticleDAOImpl;
import article.models.ArticleVO;
import sun.util.logging.resources.logging;

public class ArticleInsertAction extends AbstractController {

	// log4j 변수 설정
//	private static Logger logger = 
//			Logger.getLogger(DispatcherServlet.class);

	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {

		String name = request.getParameter("name");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String pwd = request.getParameter("pwd");

		ArticleVO articleVO = new ArticleVO();
		articleVO.setName(name);
		articleVO.setTitle(title);
		articleVO.setContent(content);
		articleVO.setPwd(pwd);
		
		
		ArticleDAO articleDAO = ArticleDAOImpl.getInstance();
		try {
			ModelAndView mav = new ModelAndView("redirect:list");
			articleDAO.insertArticle(articleVO);
//			logger.info("글 등록성공");
			return mav;
		} catch (Exception e) {
			ModelAndView mav = new ModelAndView("/WEB-INF/views/result.jsp");
			e.printStackTrace();
//			logger.info("글 등록실패");
			mav.addObject("msg", "글 등록 실패");
			mav.addObject("url", "javascript:history.back();");
			return mav;
		}
		
	}

}
