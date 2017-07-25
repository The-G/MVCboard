package article.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.management.Query;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.ibatis.sqlmap.client.SqlMapClient;

import article.controllers.PageNation;
import ibatis.QueryHandler;

public class ArticleDAOImpl implements ArticleDAO {
	private static ArticleDAOImpl articleDAO = null;
	
	private String driver = null;
	private String url = null;
	private String username = null;
	private String password = null;
	
	private DataSource ds = null;
	
	private ArticleDAOImpl() {
		/*Properties pr = new Properties();
		String props =
			this.getClass().getResource("").getPath() + "/database.properties";
		try {
			pr.load(new FileInputStream(props));
			
			driver = pr.getProperty("driver");
			url = pr.getProperty("url");
			username = pr.getProperty("username");
			password = pr.getProperty("password");
			
			Class.forName(driver);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}*/ // DBCP TEST 작업을 했기 때문에 이부분 없어도 된다. 아래 부분이 있기 때문에!!!
		
		try{
				Context context = new InitialContext();
	 			ds = (DataSource)context.lookup("java:/comp/env/jdbc/mydbcp");
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private Connection getConnection() throws SQLException {
//		return DriverManager.getConnection(url, username, password);
		return ds.getConnection(); // DBCP 작업에서 이용!!
	}
	
	public static ArticleDAOImpl getInstance() { // singleton
		if (articleDAO == null) {
			articleDAO = new ArticleDAOImpl();
		}
		return articleDAO;
	}

	private void dbClose(PreparedStatement ps, Connection cn) {
		if (ps != null) try{ps.close();}catch(Exception e){}
		if (cn != null) try{cn.close();}catch(Exception e){}
	}
	
	private void dbClose(ResultSet rs, PreparedStatement ps, Connection cn) {
		if (rs != null) try{rs.close();} catch(Exception e){}
		if (ps != null) try{ps.close();} catch(Exception e){}
		if (cn != null) try{cn.close();} catch(Exception e){}
	}

	@Override
	public void insertArticle(ArticleVO articleVO) throws Exception {
		// use ibatis
		SqlMapClient sqlMap = QueryHandler.getInstance();
		sqlMap.insert("article.insertArticle", articleVO);
		// 이걸로 끝 간결해 진다. 그래서 ibatis 쓴다!!
		
		//
		
		/*Connection cn = null;
		PreparedStatement ps = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append(" INSERT INTO tb_article(no, title, name, pwd, content)");
		sql.append(" VALUES(seq_article.nextval, ?, ?, ?, ?)");
		
		try {
			cn = getConnection();
			ps = cn.prepareStatement(sql.toString());
			ps.setString(1, articleVO.getTitle());
			ps.setString(2, articleVO.getName());
			ps.setString(3, articleVO.getPwd());
			ps.setString(4, articleVO.getContent());
			ps.executeUpdate();
			
		} finally {
			dbClose(ps,cn);
		}*/
	}
	
	@Override
	public List<ArticleVO> getArticleList() throws Exception {
		Connection cn =null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT no, title, name, regdate, viewcount");
		sql.append(" FROM   tb_article");
		sql.append(" ORDER  BY no DESC");

		try {
			cn = getConnection();
			ps = cn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			while(rs.next()) {
				ArticleVO articleVO = new ArticleVO();
				articleVO.setNo(rs.getLong("no"));
				articleVO.setTitle(rs.getString("title"));
				articleVO.setName(rs.getString("name"));
				articleVO.setRegdate(rs.getDate("regdate"));
				articleVO.setViewcount(rs.getInt("viewcount"));
				list.add(articleVO);
			}
		} finally {
			dbClose(rs, ps, cn);
		}
		return list;
	}
	
	@Override
	public List<ArticleVO> getArticleList(PageVO pageVO) throws Exception {
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		StringBuffer sql = new StringBuffer();

		sql.append(" select B.*");
		sql.append(" from (select rownum AS rnum, A.*");
		sql.append("       from (select no, title, name, regdate, viewcount");
		sql.append("             from tb_article");
		sql.append("             order by no desc) A) B");
		sql.append(" where rnum between ? and ?");

		
		try {
			cn = getConnection();
			ps = cn.prepareStatement(sql.toString());
			ps.setLong(1, pageVO.getStartnum());
			ps.setLong(2, pageVO.getEndnum());
			rs = ps.executeQuery();
			while(rs.next()) {
				ArticleVO articleVO = new ArticleVO();
				articleVO.setNo(rs.getLong("no"));
				articleVO.setTitle(rs.getString("title"));
				articleVO.setName(rs.getString("name"));
				articleVO.setRegdate(rs.getDate("regdate"));
				articleVO.setViewcount(rs.getInt("viewcount"));
				list.add(articleVO);
			}

		} finally {
			dbClose(rs, ps, cn);
		}
		
		return list;
	}
	@Override
	public ArticleVO getDetail(long no) throws Exception {
//		ibatis 사용
		SqlMapClient sqlMap = QueryHandler.getInstance();
		return (ArticleVO) sqlMap.queryForObject("article.getDetail", no);
		
		
		/*ArticleVO articleVO = null;
		
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select no, title, name, regdate, viewcount, content ");
		sql.append(" from tb_article ");
		sql.append(" where no=? ");

		try {
			cn = getConnection();
			ps = cn.prepareStatement(sql.toString());
			ps.setLong(1, no);
			rs = ps.executeQuery();
			if (rs.next()) {
				articleVO = new ArticleVO();
				articleVO.setNo(rs.getLong("no"));
				articleVO.setTitle(rs.getString("title"));
				articleVO.setName(rs.getString("name"));
				articleVO.setRegdate(rs.getDate("regdate"));
				articleVO.setViewcount(rs.getInt("viewcount"));
				articleVO.setContent(rs.getString("content"));
			} else {
				throw new RuntimeException("잘못된 접근이거나 해당 게시물이 삭제된 상태입니다.");
			}

		} finally {
			dbClose(rs, ps, cn);
		}
		return articleVO;*/	
	}
	@Override
	public void updateViewcount(long no) throws Exception {
		// use ibatis
		SqlMapClient sqlMap = QueryHandler.getInstance();
		sqlMap.update("article.updateViewcount", no);
		
		/*Connection cn = null;
		PreparedStatement ps = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append(" update tb_article set");
		sql.append(" 		viewcount = viewcount + 1");
		sql.append(" where no = ?");
		
		try {
			cn = getConnection();
			// cn.getAutoCommit(false); // 참고:이걸로 auto commit 막을 수도 있다.
			ps = cn.prepareStatement(sql.toString());
			ps.setLong(1, no);
			if(ps.executeUpdate() == 0) {
				throw new RuntimeException(no + "번 게시물이 존재하지 않습니다.");
			}
		} finally {
			
		}*/
		
	}
	@Override
	public void updateArticle(ArticleVO articleVO) throws Exception {
		// use ibatis		
		SqlMapClient sqlMap = QueryHandler.getInstance();
		if(sqlMap.update("article.updateArticle", articleVO) == 0) {
	 		throw new RuntimeException(articleVO.getNo() + "번 게시물 비밀번호가 틀렸습니다.");
		}
		/*Connection cn = null;
		PreparedStatement ps = null;
				
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE tb_article ");
		sql.append(" SET name = ?, ");
		sql.append(" 	 title = ?, ");
		sql.append(" 	 content = ? ");
		sql.append(" WHERE no = ? and pwd = ? ");
		
		try {
			cn = getConnection();
			ps = cn.prepareStatement(sql.toString());
			ps.setString(1, articleVO.getName());
			ps.setString(2, articleVO.getTitle());
			ps.setString(3, articleVO.getContent());
			ps.setLong(4, articleVO.getNo());
			ps.setString(5, articleVO.getPwd());

			 if(ps.executeUpdate() == 0) {
			 		throw new RuntimeException(articleVO.getNo() + "번 게시물 비밀번호가 틀렸습니다.");
			 }
		} finally {
			dbClose(ps,cn);
		}*/
	}
	@Override
	public void deleteArticle(ArticleVO articleVO) throws Exception {
		SqlMapClient sqlMap = QueryHandler.getInstance();
		if(sqlMap.delete("article.deleteArticle", articleVO) == 0) {
	 		throw new RuntimeException(articleVO.getNo() + "번 게시물 비밀번호가 틀렸습니다.");
		}
		
		/*Connection cn = null;
		PreparedStatement ps = null;
				
		StringBuffer sql = new StringBuffer();
		sql.append(" DELETE from tb_article ");
		sql.append(" WHERE no = ? and pwd = ? ");
		
		try {
			cn = getConnection();
			ps = cn.prepareStatement(sql.toString());
			ps.setLong(1, articleVO.getNo());
			ps.setString(2, articleVO.getPwd());

			 if(ps.executeUpdate() == 0) {
			 		throw new RuntimeException(articleVO.getNo() + "번 게시물 비밀번호가 틀렸습니다.");
			 }
		} finally {
			dbClose(ps,cn);
		}*/
	}
	@Override
	public long getTotalCount() throws Exception {

		SqlMapClient sqlMap = QueryHandler.getInstance();
		return (long) sqlMap.queryForObject("article.getTotalCount"); // 한개를 얻어오는 것이기 때문에!!ㄴ
		// object를 바로 기본 데이터 타입으로 바꾸는 것은 java7부터 가능하다!!
		
		/*Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql_total_count = " select count(no) as cnt from tb_article";
		long result = 0;
		try{
			cn = getConnection();		
			ps = cn.prepareStatement(sql_total_count);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				result = rs.getLong("cnt");		
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(rs,ps,cn);
		}
		return result;*/		
	}
	@Override
	public List<ArticleVO> getArticleList(PageNation pageNation) throws Exception {
		
		SqlMapClient sqlMap = QueryHandler.getInstance();
		return sqlMap.queryForList("article.getArticleList", pageNation);
		
		/*Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		StringBuffer sql = new StringBuffer();

		sql.append(" select B.*");
		sql.append(" from (select rownum AS rnum, A.*");
		sql.append("       from (select no, title, name, regdate, viewcount");
		sql.append("             from tb_article");
		sql.append("             order by no desc) A) B");
		sql.append(" where rnum between ? and ?");

		try {
			cn = getConnection();
			ps = cn.prepareStatement(sql.toString());
			ps.setLong(1, pageNation.getStartnum());
			ps.setLong(2, pageNation.getEndnum());
			rs = ps.executeQuery();
			while(rs.next()) {
				ArticleVO articleVO = new ArticleVO();
				articleVO.setNo(rs.getLong("no"));
				articleVO.setTitle(rs.getString("title"));
				articleVO.setName(rs.getString("name"));
				articleVO.setRegdate(rs.getDate("regdate"));
				articleVO.setViewcount(rs.getInt("viewcount"));
				list.add(articleVO);
			}

		} finally {
			dbClose(rs, ps, cn);
		}
		return list;*/
	}

}
