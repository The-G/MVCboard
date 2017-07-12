package article.models;

public class PageVO_kjh {
	private long startnum;
	private long endnum;
	public long getStartnum() {
		return startnum;
	}
	
	public PageVO_kjh(long startnum, long endnum) {
		this.startnum = startnum;
		this.endnum = endnum;
	}
	public PageVO_kjh(){}
	
	public void setStartnum(long startnum) {
		this.startnum = startnum;
	}
	public long getEndnum() {
		return endnum;
	}
	public void setEndnum(long endnum) {
		this.endnum = endnum;
	}
	@Override
	public String toString() {
		return "PageVO [startnum=" + startnum + ", endnum=" + endnum + "]";
	}
	
	
}
