package article.models;

import java.util.List;

import article.models.ArticleVO;

public interface ArticleDAO {
	
	void insertArticle(ArticleVO articleVO) throws Exception;
	
	List<ArticleVO> getArticleList() throws Exception;

	List<ArticleVO> getArticleList(PageVO pageVO) throws Exception; // overloading 함!!

	ArticleVO getDetail(long no) throws Exception;

	void updateViewcount(long no) throws Exception;

	void updateArticle(ArticleVO articleVO) throws Exception;

	void deleteArticle(ArticleVO articleVO) throws Exception;

	long getTotalCount() throws Exception;  // 예외처리를 하지 않고 DB연결만 하고자 한다. 
											// 그래서 사용자 측에서 예외처리를 하도록!!!!!

}
