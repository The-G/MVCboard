package article.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.models.ArticleDAO;
import article.models.ArticleDAOImpl;
import article.models.ArticleVO;

public class ArticleUpdateAction extends AbstractController {

	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		Long no = Long.parseLong(request.getParameter("no"));
		String name = request.getParameter("name");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String pwd = request.getParameter("pwd");

		ArticleVO articleVO = new ArticleVO();
		articleVO.setNo(no);
		articleVO.setName(name);
		articleVO.setTitle(title);
		articleVO.setContent(content);
		articleVO.setPwd(pwd);
		
		ArticleDAO articleDAO = ArticleDAOImpl.getInstance();
		ModelAndView mav = new ModelAndView("/WEB-INF/views/result.jsp");
		try {
			articleDAO.updateArticle(articleVO);
//			logger.info("글 등록성공");
			mav.addObject("msg", "글 수정 성공");
			mav.addObject("url", "detail?no=" + no);
		} catch (Exception e) {
			e.printStackTrace();
//			logger.info("글 등록실패");
			mav.addObject("msg", e.getMessage());
			mav.addObject("url", "javascript:history.back();");
		}
		
		return mav;

	}

}
