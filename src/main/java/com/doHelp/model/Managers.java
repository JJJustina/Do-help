package com.doHelp.model;

import java.io.Serializable;

public class Managers implements Serializable{
	private static final long serialVersionUID = -5759384645308535203L;
	private int manager_id;
	private String manager_phone;
	private String manager_realName;
	private String manager_password;
	private String manager_BuiltInPassword;
	private String manager_birthdayPlaca;
	private String manager_is_verify_code;
	private String manager_createAt;
	private String manager_ip;
	private String manager_usingAt;
	public int getManager_id() {
		return manager_id;
	}
	public void setManager_id(int manager_id) {
		this.manager_id = manager_id;
	}
	public String getManager_phone() {
		return manager_phone;
	}
	public void setManager_phone(String manager_phone) {
		this.manager_phone = manager_phone;
	}
	public String getManager_realName() {
		return manager_realName;
	}
	public void setManager_realName(String manager_realName) {
		this.manager_realName = manager_realName;
	}
	public String getManager_password() {
		return manager_password;
	}
	public void setManager_password(String manager_password) {
		this.manager_password = manager_password;
	}
	public String getManager_BuiltInPassword() {
		return manager_BuiltInPassword;
	}
	public void setManager_BuiltInPassword(String manager_BuiltInPassword) {
		this.manager_BuiltInPassword = manager_BuiltInPassword;
	}
	public String getManager_birthdayPlaca() {
		return manager_birthdayPlaca;
	}
	public void setManager_birthdayPlaca(String manager_birthdayPlaca) {
		this.manager_birthdayPlaca = manager_birthdayPlaca;
	}
	public String getManager_is_verify_code() {
		return manager_is_verify_code;
	}
	public void setManager_is_verify_code(String manager_is_verify_code) {
		this.manager_is_verify_code = manager_is_verify_code;
	}
	public String getManager_createAt() {
		return manager_createAt;
	}
	public void setManager_createAt(String manager_createAt) {
		this.manager_createAt = manager_createAt;
	}
	public String getManager_ip() {
		return manager_ip;
	}
	public void setManager_ip(String manager_ip) {
		this.manager_ip = manager_ip;
	}
	public String getManager_usingAt() {
		return manager_usingAt;
	}
	public void setManager_usingAt(String manager_usingAt) {
		this.manager_usingAt = manager_usingAt;
	}


}
