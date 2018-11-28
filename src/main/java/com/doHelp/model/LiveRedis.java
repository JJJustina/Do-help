package com.doHelp.model;

import java.io.Serializable;

public class LiveRedis implements Serializable {

	private static final long serialVersionUID = 1L;

	private String keyname;

	private String userName;

	private String liveName;

	private String content;

	public String getKeyname() {
		return keyname;
	}

	public void setKeyname(String keyname) {
		this.keyname = keyname;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLiveName() {
		return liveName;
	}

	public void setLiveName(String liveName) {
		this.liveName = liveName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
