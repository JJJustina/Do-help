package com.doHelp.model;

public class ReportVideo {
	private int reportVideo_id;    //(举报id  自增长)  int 
	private int reportType_id;    //(举报类型id) int 
    private int video_id;        //  (视频id) int 
	private int user_id;        // (举报人id) int 
	public int getReportVideo_id() {
		return reportVideo_id;
	}
	public void setReportVideo_id(int reportVideo_id) {
		this.reportVideo_id = reportVideo_id;
	}
	public int getReportType_id() {
		return reportType_id;
	}
	public void setReportType_id(int reportType_id) {
		this.reportType_id = reportType_id;
	}
	public int getVideo_id() {
		return video_id;
	}
	public void setVideo_id(int video_id) {
		this.video_id = video_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
}
