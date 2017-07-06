package article.controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class DispatcherServlet extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
	
	// log4j 변수 설정
	private static Logger logger = 
			Logger.getLogger(DispatcherServlet.class);

	private static Map<String, AbstractController> controllerMap = 
			new HashMap<String, AbstractController>();
	
	@Override
	public void init() throws ServletException { // 설정이나 자원에 대한 open 할 때 사용!
		// 메모리가 올라오는 순간, 요청하는 액션의 이름, 실제 class 이름을 key=value로 설정파일에 기록함!!
		// 그 설정 파일을 이 init에서 읽을 거다!!
		// 성능향상되고, 코드에서 new instance 하는 것을 지울 수 있고, 미리 인스턴스를 생성해둠!
		// controller를 2개로 쪼개는 효과를 이부분을 통해서 완성하는 거다. 설정파일!!
		// 정리하면!! => update나, delete같은 새로운 class(controller)를
		// 				 추가 할 때 dispatcher servlet에서 수정하지 않기 위해서 이렇게 하는 거다 
		
		
		
		logger.info("DispatcherServlet.intit... 수행중!!!");
		InputStream is = null;
		Properties pr = new Properties();
		String filePath = this.getClass().getResource("").getPath();
		try {
			is = new FileInputStream(
					filePath + "dispatcher-servlet.properties");
			pr.load(is);
			
			for(Object obj : pr.keySet()){
				String key = ((String)obj).trim();
				String classPath = (pr.getProperty(key)).trim();
				
				try {
					Class className = Class.forName(classPath); //class 존재하는지 확인하고 있으면 참조까지 한다!!
					controllerMap.put(key,(AbstractController) className.newInstance()); // 인스턴스 미리 생성!! 그리고 Map에 담는다!!
					logger.info("loaded : " + key);
				} catch (Exception e) {
					e.printStackTrace();
					logger.info("failure : " + key);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(is != null) try{is.close();} catch(Exception e){};
		}
	}

	@Override
	protected void service(
		HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logger.info("mvcboard 수행 중...");
		String contentPath = request.getContextPath();
		String action = request.getRequestURI().trim().substring(contentPath.length());
		logger.info(action); // 
		
		AbstractController controller = null;
		ModelAndView mav = null;

		controller = controllerMap.get(action); // 여기서 instance 가져옴?!??
		if(controller == null){
			logger.info("수행할 액션이 없거나 AbstractController의 서브타입이 아닙니다.");
			return;
		}
		mav = controller.handleRequestInternal(request, response);
		
//		if(action.equals("/article/insert")){
//			controller = new ArticleInsert(); // up casting 한거니 상속을 받지!!
//			mav = controller.handleRequestInternal(request, response);
//		} else if (action.equals("/article/insertAction")){
//			controller = new ArticleInsertAction(); 
//			mav = controller.handleRequestInternal(request, response);			
//		}
//		dispatcher-servlet.properties를 사용함으로써 이부분이 필요 없어짐!!
		
		if (mav != null){
			for(String key : mav.getModel().keySet()) {
				request.setAttribute(key, mav.getModel().get(key)); 
			}
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher(mav.getViewName());
			dispatcher.forward(request, response);
			return;
		} else {
			logger.info("DispatcherServlet에서 길을 잃었다네");
		}
		
	}
}
