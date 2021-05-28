package com.icia.board.dto;

public class BoardDTO {
	
	private int bnumber;
	private String btitle;
	private String bpassword;
	private String bwriter;
	private String bcontents; 
	private String bdate;
	private int bhits;
	
	public int getBnumber() {
		return bnumber;
	}
	public void setBnumber(int bnumber) {
		this.bnumber = bnumber;
	}
	public String getBtitle() {
		return btitle;
	}
	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}
	public String getBpassword() {
		return bpassword;
	}
	public void setBpassword(String bpassword) {
		this.bpassword = bpassword;
	}
	public String getBwriter() {
		return bwriter;
	}
	public void setBwriter(String bwriter) {
		this.bwriter = bwriter;
	}
	public String getBcontents() {
		return bcontents;
	}
	public void setBcontents(String bcontents) {
		this.bcontents = bcontents;
	}
	public String getBdate() {
		return bdate;
	}
	public void setBdate(String bdate) {
		this.bdate = bdate;
	}
	public int getBhits() {
		return bhits;
	}
	public void setBhits(int bhits) {
		this.bhits = bhits;
	}
	@Override
	public String toString() {
		return "BoardDTO [bnumber=" + bnumber + ", btitle=" + btitle + ", bpassword=" + bpassword + ", bwriter="
				+ bwriter + ", bcontents=" + bcontents + ", bdate=" + bdate + ", bhits=" + bhits + "]";
	}
	
	
}
