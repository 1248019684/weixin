package com.message.response;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class BaseRespMessage implements Serializable {
	
	private String toUserName;
    private String fromUserName;
    private long   createTime;
    private String msgType;
	public String getToUserName() {
		return toUserName;
	}
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	public String getFromUserName() {
		return fromUserName;
	}
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	
	public String toString() {
		return new ToStringBuilder(this).append(getCreateTime()).
				append(getMsgType()).append(getToUserName()).toString();
	}
	
	public boolean equals(Object obj) {
		if(!(obj instanceof BaseRespMessage)){
			return false;
		}
		BaseRespMessage event = (BaseRespMessage)obj;
		return new EqualsBuilder().append(event.getCreateTime(), this.getCreateTime())
				.append(event.getMsgType(), this.getMsgType())
				.append(event.getToUserName(),this.getToUserName()).isEquals();
	}
	
	public int hashCode() {
		return new HashCodeBuilder().append(this.getCreateTime()).
				append(this.getMsgType()).append(this.getToUserName()).hashCode();
	}

}
