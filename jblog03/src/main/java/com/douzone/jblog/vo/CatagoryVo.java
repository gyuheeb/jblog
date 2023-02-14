package com.douzone.jblog.vo;

public class CatagoryVo {
	private Long no;
	private String id;
	private String name;
	private Long postcount;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

	public Long getPostcount() {
		return postcount;
	}
	public void setPostcount(Long postcount) {
		this.postcount = postcount;
	}
	@Override
	public String toString() {
		return "CatagoryVo [no=" + no + ", id=" + id + ", name=" + name + ", postcount=" + postcount + "]";
	}
	
	
}
