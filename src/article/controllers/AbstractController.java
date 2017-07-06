package article.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractController { // 항상 같은 규격으로 controller class를 만들기 위해서

	public abstract ModelAndView handleRequestInternal(
			HttpServletRequest request, HttpServletResponse response);

}
