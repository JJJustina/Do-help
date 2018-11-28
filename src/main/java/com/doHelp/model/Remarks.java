package com.doHelp.model;

public class Remarks {

	 private  int remarks_id;
	 private String  remarks_info;
	 private  int  snapCount=0;
	 private  int   user_id;
	public int getRemarks_id() {
		return remarks_id;
	}
	public void setRemarks_id(int remarks_id) {
		this.remarks_id = remarks_id;
	}
	public String getRemarks_info() {
		return remarks_info;
	}
	public void setRemarks_info(String remarks_info) {
		this.remarks_info = remarks_info;
	}
	public int getSnapCount() {
		return snapCount;
	}
	public void setSnapCount(int snapCount) {
		this.snapCount = snapCount;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	@Override
	public String toString() {
		return "Remarks [remarks_id=" + remarks_id + ", remarks_info=" + remarks_info + ", snapCount=" + snapCount
				+ ", user_id=" + user_id + ", getRemarks_id()=" + getRemarks_id() + ", getRemarks_info()="
				+ getRemarks_info() + ", getSnapCount()=" + getSnapCount() + ", getUser_id()=" + getUser_id()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
}
