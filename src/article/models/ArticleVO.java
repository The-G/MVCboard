package article.models;

import java.util.Date;

public class ArticleVO {
	private long no;
	private String name;
	private String title;
	private String content;
	private String pwd;
	private Date regdate;
	private int viewcount;
	
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public int getViewcount() {
		return viewcount;
	}
	public void setViewcount(int viewcount) {
		this.viewcount = viewcount;
	}
	@Override
	public String toString() {
		return "ArticleVO [no=" + no + ", name=" + name + ", title=" + title + ", content=" + content + ", pwd=" + pwd
				+ ", regdate=" + regdate + ", viewcount=" + viewcount + "]";
	}
	
	
}
