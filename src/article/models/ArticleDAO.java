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

}
