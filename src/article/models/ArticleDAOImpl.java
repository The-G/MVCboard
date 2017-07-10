package article.models;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ArticleDAOImpl implements ArticleDAO {
	private static ArticleDAOImpl articleDAO = null;
	
	private String driver = null;
	private String url = null;
	private String username = null;
	private String password = null;
	
	private ArticleDAOImpl() {
		Properties pr = new Properties();
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
		}
	}
	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
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
		Connection cn = null;
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
		}
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
		ArticleVO articleVO = null;
		
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
		return articleVO;
	}
	@Override
	public void updateViewcount(long no) throws Exception {
		Connection cn = null;
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
			
		}
		
	}
	@Override
	public void updateArticle(ArticleVO articleVO) throws Exception {
		Connection cn = null;
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
		}
	}
	@Override
	public void deleteArticle(ArticleVO articleVO) throws Exception {
		Connection cn = null;
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
		}
	}

}
