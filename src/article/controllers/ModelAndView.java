package article.controllers;

import java.util.HashMap;
import java.util.Map;

public class ModelAndView {	// data를 담아서 view로 보낼거다!!
	
	
	private String viewName;
	private Map<String, Object> model = new HashMap<String, Object>();

	public ModelAndView() {}

	public ModelAndView(String viewName) {
		this.viewName = viewName;
	}

	public ModelAndView(String viewName, String key, Object obj) { //데이터를 1개만 가지고 다음 페이지로 이동하지!!
		this.viewName = viewName;
		model.put(key, obj);
//		this.model = model;
	}
	
	public Map<String, Object> getModel() {
		return model;
	}
	public void addObject(String key, Object obj) {
		model.put(key, obj); // Map에다가 값을 저장하기 위해서!!
	}
	public String getViewName() {
		return viewName;
	}
	public void setViewName(String viewName) {
		this.viewName = viewName;
	}
	@Override
	public String toString() {
		return "ModelAndView [model=" + model + ", viewName=" + viewName + "]";
	}
		
}
