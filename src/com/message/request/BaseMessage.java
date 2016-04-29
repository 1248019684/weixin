package com.message.request;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class BaseMessage implements Serializable{
	
	private String toUserName;
    private String fromUserName;
    private long   createTime;
    private String msgType;
    private long msgId;
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
	public long getMsgId() {
		return msgId;
	}
	public void setMsgId(long msgId) {
		this.msgId = msgId;
	}
	
	public String toString() {
		return new ToStringBuilder(this).append(getMsgId()).toString();
	}
	
	public boolean equals(Object obj) {
		if(!(obj instanceof BaseMessage)){
			return false;
		}
		BaseMessage message = (BaseMessage)obj;
		return new EqualsBuilder().append(message.getMsgId(), this.getMsgId()).isEquals();
	}
	
	public int hashCode() {
		return new HashCodeBuilder().append(this.getMsgId()).hashCode();
	}

}
