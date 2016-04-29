package com.message.request;

public class VoiceMessage extends BaseMessage {
	
	private String mediaId;
	private String format;
	private String recognition;
	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	
	public void setRecognition(String recognition){
		this.recognition = recognition;
	}
	
	public String getRecognition(){
		return recognition;
	}

}
