package com.doHelp.model;

public class Audio {
	private int audio_id ;                                // (音频id,自增长) int
	private String audio_publishTime;                    //(发布时间)  String
	private String audio_is_verify_code;                //(被举报分数到一定程度时管理员是否审核T/F)  String
	private String audio_title;                        //(音频标题) String
	private String audio_info;                        //(介绍音频信息) String
	private int audio_seeCount ;                     // (观看次数)  int
	private int audio_snapCount ;                   //(音频点赞数量) int
	private int audio_money;                             //(音频费用) int
	public int getAudio_money() {
		return audio_money;
	}
	public void setAudio_money(int audio_money) {
		this.audio_money = audio_money;
	}
	private int audio_reportCount ;               // (音频被举报总分数) int
	private int audio_commentCount;             //  (音频评论数量) int
	public int getAudio_commentCount() {
		return audio_commentCount;
	}
	public void setAudio_commentCount(int audio_commentCount) {
		this.audio_commentCount = audio_commentCount;
	}
	private String audio_address;               //(存储地址)   String
	private int contentType_id ;               // (内容类型id) int
	private int user_id;                      //(不显示)(发布人id号) int
	public int getAudio_id() {
		return audio_id;
	}
	public void setAudio_id(int audio_id) {
		this.audio_id = audio_id;
	}
	public String getAudio_publishTime() {
		return audio_publishTime;
	}
	public void setAudio_publishTime(String audio_publishTime) {
		this.audio_publishTime = audio_publishTime;
	}
	public String getAudio_is_verify_code() {
		return audio_is_verify_code;
	}
	public void setAudio_is_verify_code(String audio_is_verify_code) {
		this.audio_is_verify_code = audio_is_verify_code;
	}
	public String getAudio_title() {
		return audio_title;
	}
	public void setAudio_title(String audio_title) {
		this.audio_title = audio_title;
	}
	public String getAudio_info() {
		return audio_info;
	}
	public void setAudio_info(String audio_info) {
		this.audio_info = audio_info;
	}
	public int getAudio_seeCount() {
		return audio_seeCount;
	}
	public void setAudio_seeCount(int audio_seeCount) {
		this.audio_seeCount = audio_seeCount;
	}
	public int getAudio_snapCount() {
		return audio_snapCount;
	}
	public void setAudio_snapCount(int audio_snapCount) {
		this.audio_snapCount = audio_snapCount;
	}
	public int getAudio_reportCount() {
		return audio_reportCount;
	}
	public void setAudio_reportCount(int audio_reportCount) {
		this.audio_reportCount = audio_reportCount;
	}

	public String getAudio_address() {
		return audio_address;
	}
	public void setAudio_address(String audio_address) {
		this.audio_address = audio_address;
	}
	public int getContentType_id() {
		return contentType_id;
	}
	public void setContentType_id(int contentType_id) {
		this.contentType_id = contentType_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
}
