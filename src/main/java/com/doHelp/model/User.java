package com.doHelp.model;

import java.io.Serializable;

public class User implements Serializable{
   private static final long serialVersionUID = -5759384645308535203L;
   private  int  user_id;
   private String  user_phone;
   private String user_password;
   private String user_sex;
   private String user_photoAdress;
   private String user_birthdayPlace;
   private String user_accountStatus="优秀";
   private int user_accountCount=100;
   private int  user_violationCount=0;
   private int  user_forbiddenTime=0;
   private String  user_email ;
   private String  user_realName;
   private String user_collegeSchoolAt;
   private String user_collegeSpeciality;
   private String user_postGraduateSchoolAt ;
   private String user_doctorialSchoolAt;
   private String  user_graduateSchoolAt ;
   private String user_is_verify_code="F";  // 初始值为F 如果手机验证码正确则将值改为T 数据库中保存的值为T或者F
   private String user_createAt;
   private String  user_usingAt;
   public int getUser_fans() {
	return user_fans;
}
public void setUser_fans(int user_fans) {
	this.user_fans = user_fans;
}
private int user_fans=0;
   private String user_messageStatus="F";
   public String getUser_messageStatus() {
	return user_messageStatus;
}
public void setUser_messageStatus(String user_messageStatus) {
	this.user_messageStatus = user_messageStatus;
}
private int  user_hotCount=0;
   private String user_ip;
   public String getUser_rpassWord() {
	return user_rpassWord;
}
public void setUser_rpassWord(String user_rpassWord) {
	this.user_rpassWord = user_rpassWord;
}
private String user_rpassWord;
  
   public String getUser_usingAt1() {
	return user_usingAt1;
}
public void setUser_usingAt1(String user_usingAt1) {
	this.user_usingAt1 = user_usingAt1;
}
public String getUser_ip1() {
	return user_ip1;
}
public void setUser_ip1(String user_ip1) {
	this.user_ip1 = user_ip1;
}
private String user_usingAt1;
   private String user_ip1;
   public int getUser_id() {
	return user_id;
}
public void setUser_id(int user_id) {
	this.user_id = user_id;
}
public String getUser_phone() {
	return user_phone;
}
public void setUser_phone(String user_phone) {
	this.user_phone = user_phone;
}
public String getUser_password() {
	return user_password;
}
public void setUser_password(String user_password) {
	this.user_password = user_password;
}
public String getUser_sex() {
	return user_sex;
}
public void setUser_sex(String user_sex) {
	this.user_sex = user_sex;
}
public String getUser_photoAdress() {
	return user_photoAdress;
}
public void setUser_photoAdress(String user_photoAdress) {
	this.user_photoAdress = user_photoAdress;
}
public String getUser_birthdayPlace() {
	return user_birthdayPlace;
}
public void setUser_birthdayPlace(String user_birthdayPlace) {
	this.user_birthdayPlace = user_birthdayPlace;
}
public String getUser_accountStatus() {
	return user_accountStatus;
}
public void setUser_accountStatus(String user_accountStatus) {
	this.user_accountStatus = user_accountStatus;
}
public int getUser_accountCount() {
	return user_accountCount;
}
public void setUser_accountCount(int user_accountCount) {
	this.user_accountCount = user_accountCount;
}
public int getUser_violationCount() {
	return user_violationCount;
}
public void setUser_violationCount(int user_violationCount) {
	this.user_violationCount = user_violationCount;
}
public int getUser_forbiddenTime() {
	return user_forbiddenTime;
}
public void setUser_forbiddenTime(int user_forbiddenTime) {
	this.user_forbiddenTime = user_forbiddenTime;
}
public String getUser_email() {
	return user_email;
}
public void setUser_email(String user_email) {
	this.user_email = user_email;
}
public String getUser_realName() {
	return user_realName;
}
public void setUser_realName(String user_realName) {
	this.user_realName = user_realName;
}
public String getUser_collegeSchoolAt() {
	return user_collegeSchoolAt;
}
public void setUser_collegeSchoolAt(String user_collegeSchoolAt) {
	this.user_collegeSchoolAt = user_collegeSchoolAt;
}
public String getUser_collegeSpeciality() {
	return user_collegeSpeciality;
}
public void setUser_collegeSpeciality(String user_collegeSpeciality) {
	this.user_collegeSpeciality = user_collegeSpeciality;
}
public String getUser_postGraduateSchoolAt() {
	return user_postGraduateSchoolAt;
}
public void setUser_postGraduateSchoolAt(String user_postGraduateSchoolAt) {
	this.user_postGraduateSchoolAt = user_postGraduateSchoolAt;
}
public String getUser_doctorialSchoolAt() {
	return user_doctorialSchoolAt;
}
public void setUser_doctorialSchoolAt(String user_doctorialSchoolAt) {
	this.user_doctorialSchoolAt = user_doctorialSchoolAt;
}
public String getUser_graduateSchoolAt() {
	return user_graduateSchoolAt;
}
public void setUser_graduateSchoolAt(String user_graduateSchoolAt) {
	this.user_graduateSchoolAt = user_graduateSchoolAt;
}
public String getUser_is_verify_code() {
	return user_is_verify_code;
}
public void setUser_is_verify_code(String string) {
	this.user_is_verify_code = string;
}
public String getUser_createAt() {
	return user_createAt;
}
public void setUser_createAt(String user_createAt) {
	this.user_createAt = user_createAt;
}
public String getUser_usingAt() {
	return user_usingAt;
}
public void setUser_usingAt(String user_usingAt) {
	this.user_usingAt = user_usingAt;
}
public int getUser_hotCount() {
	return user_hotCount;
}
public void setUser_hotCount(int user_hotCount) {
	this.user_hotCount = user_hotCount;
}
public String getUser_ip() {
	return user_ip;
}
public void setUser_ip(String user_ip) {
	this.user_ip = user_ip;
}
public static long getSerialversionuid() {
	return serialVersionUID;
}
@Override
public String toString() {
	return "User [user_id=" + user_id + ", user_phone=" + user_phone + ", user_password=" + user_password
			+ ", user_sex=" + user_sex + ", user_photoAdress=" + user_photoAdress + ", user_birthdayPlace="
			+ user_birthdayPlace + ", user_accountStatus=" + user_accountStatus + ", user_accountCount="
			+ user_accountCount + ", user_violationCount=" + user_violationCount + ", user_forbiddenTime="
			+ user_forbiddenTime + ", user_email=" + user_email + ", user_realName=" + user_realName
			+ ", user_collegeSchoolAt=" + user_collegeSchoolAt + ", user_collegeSpeciality=" + user_collegeSpeciality
			+ ", user_postGraduateSchoolAt=" + user_postGraduateSchoolAt + ", user_doctorialSchoolAt="
			+ user_doctorialSchoolAt + ", user_graduateSchoolAt=" + user_graduateSchoolAt + ", user_is_verify_code="
			+ user_is_verify_code + ", user_createAt=" + user_createAt + ", user_usingAt=" + user_usingAt
			+ ", user_hotCount=" + user_hotCount + ", user_ip=" + user_ip + ", getUser_id()=" + getUser_id()
			+ ", getUser_phone()=" + getUser_phone() + ", getUser_password()=" + getUser_password() + ", getUser_sex()="
			+ getUser_sex() + ", getUser_photoAdress()=" + getUser_photoAdress() + ", getUser_birthdayPlace()="
			+ getUser_birthdayPlace() + ", getUser_accountStatus()=" + getUser_accountStatus()
			+ ", getUser_accountCount()=" + getUser_accountCount() + ", getUser_violationCount()="
			+ getUser_violationCount() + ", getUser_forbiddenTime()=" + getUser_forbiddenTime() + ", getUser_email()="
			+ getUser_email() + ", getUser_realName()=" + getUser_realName() + ", getUser_collegeSchoolAt()="
			+ getUser_collegeSchoolAt() + ", getUser_collegeSpeciality()=" + getUser_collegeSpeciality()
			+ ", getUser_postGraduateSchoolAt()=" + getUser_postGraduateSchoolAt() + ", getUser_doctorialSchoolAt()="
			+ getUser_doctorialSchoolAt() + ", getUser_graduateSchoolAt()=" + getUser_graduateSchoolAt()
			+ ", getUser_is_verify_code()=" + getUser_is_verify_code() + ", getUser_createAt()=" + getUser_createAt()
			+ ", getUser_usingAt()=" + getUser_usingAt() + ", getUser_hotCount()=" + getUser_hotCount()
			+ ", getUser_ip()=" + getUser_ip() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
			+ ", toString()=" + super.toString() + "]";
}

}
