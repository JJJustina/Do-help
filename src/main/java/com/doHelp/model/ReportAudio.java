package com.doHelp.model;

public class ReportAudio {
	private int reportAudio_id;    //(举报id  自增长)  int 
	private int reportType_id;    //(举报类型id) int 
    private int audio_id;        //  (音频id) int 
	private int user_id;        // (举报人id) int 
	public int getReportAudio_id() {
		return reportAudio_id;
	}
	public void setReportAudio_id(int reportAudio_id) {
		this.reportAudio_id = reportAudio_id;
	}
	public int getReportType_id() {
		return reportType_id;
	}
	public void setReportType_id(int reportType_id) {
		this.reportType_id = reportType_id;
	}
	public int getAudio_id() {
		return audio_id;
	}
	public void setAudio_id(int audio_id) {
		this.audio_id = audio_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
}
