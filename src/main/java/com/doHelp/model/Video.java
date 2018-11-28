package com.doHelp.model;

public class Video {
	        //13个字段
			private int video_id;                                 // (视频id,自增长0 int
			private String video_publishTime;                    //(发布时间)  String
			private String video_is_verify_code;                //(被举报分数到一定程度时管理员是否审核T/F)  String
			private String video_title;                        //(视频标题) String
			private String video_info;                        //(介绍视频信息) String
			private int video_seeCount=0 ;                   //(观看次数)  int
			private int video_snapCount=0;                  //(视频点赞数量) int
			private int video_money=0;                     //(视频费用) int
			private int video_reportCount=0 ;             //(视频被举报总分数) int
			private int video_commentCount=0;            //(视频评论数量) int
			private String video_address;               //(存储地址)   String
			private int contentType_id;                // (内容类型id) int
			private int user_id;                      //(不显示)(发布人id号) int
			public int getVideo_id() {
				return video_id;
			}
			public void setVideo_id(int video_id) {
				this.video_id = video_id;
			}
			public String getVideo_publishTime() {
				return video_publishTime;
			}
			public void setVideo_publishTime(String video_publishTime) {
				this.video_publishTime = video_publishTime;
			}
			public String getVideo_is_verify_code() {
				return video_is_verify_code;
			}
			public void setVideo_is_verify_code(String video_is_verify_code) {
				this.video_is_verify_code = video_is_verify_code;
			}
			public String getVideo_title() {
				return video_title;
			}
			public void setVideo_title(String video_title) {
				this.video_title = video_title;
			}
			public String getVideo_info() {
				return video_info;
			}
			public void setVideo_info(String video_info) {
				this.video_info = video_info;
			}
			public int getVideo_seeCount() {
				return video_seeCount;
			}
			public void setVideo_seeCount(int video_seeCount) {
				this.video_seeCount = video_seeCount;
			}
			public int getVideo_snapCount() {
				return video_snapCount;
			}
			public void setVideo_snapCount(int video_snapCount) {
				this.video_snapCount = video_snapCount;
			}
			public int getVideo_money() {
				return video_money;
			}
			public void setVideo_money(int video_money) {
				this.video_money = video_money;
			}
			public int getVideo_reportCount() {
				return video_reportCount;
			}
			public void setVideo_reportCount(int video_reportCount) {
				this.video_reportCount = video_reportCount;
			}
			public int getVideo_commentCount() {
				return video_commentCount;
			}
			public void setVideo_commentCount(int video_commentCount) {
				this.video_commentCount = video_commentCount;
			}
			public String getVideo_address() {
				return video_address;
			}
			public void setVideo_address(String video_address) {
				this.video_address = video_address;
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
