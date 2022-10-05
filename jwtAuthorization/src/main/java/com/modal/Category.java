package com.modal;

public class Category {
	private int cid;
	private String title;
	private String description;
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Category [cid=" + cid + ", title=" + title + ", description=" + description + "]";
	}
	public Category(String title, String description) { //not taken cid as it is auto inc
		super();
		this.title = title;
		this.description = description;
	}
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
