package ai.bi.com.charts.bean;

import java.io.Serializable;

public class RevenueBean implements Serializable{
	private static final long serialVersionUID = -3269607910499822925L;
	private String optimeStart;
	private String optimeEnd;
	private String[] citys;
	private String[] countys;

	public RevenueBean(String optimeStart, String optimeEnd, String[] citys, String[] countys) {
		this.optimeStart = optimeStart;
		this.optimeEnd = optimeEnd;
		this.citys = citys;
		this.countys = countys;
	}
	public String getOptimeStart() {
		return optimeStart;
	}

	public void setOptimeStart(String optimeStart) {
		this.optimeStart = optimeStart;
	}

	public String getOptimeEnd() {
		return optimeEnd;
	}

	public void setOptimeEnd(String optimeEnd) {
		this.optimeEnd = optimeEnd;
	}

	public String[] getCitys() {
		return citys;
	}

	public void setCitys(String[] citys) {
		this.citys = citys;
	}

	public String[] getCountys() {
		return countys;
	}

	public void setCountys(String[] countys) {
		this.countys = countys;
	}

}
