package com.event;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class BaseEvent implements Serializable {
	
	private String toUserName;
    private String fromUserName;
    private long   createTime;
    private String msgType;
    private String event;
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
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	
	public String toString() {
		return new ToStringBuilder(this).append(getCreateTime()).
				append(getEvent()).append(getFromUserName()).toString();
	}
	
	public boolean equals(Object obj) {
		if(!(obj instanceof BaseEvent)){
			return false;
		}
		BaseEvent event = (BaseEvent)obj;
		return new EqualsBuilder().append(event.getCreateTime(), this.getCreateTime())
				.append(event.getEvent(), this.getEvent())
				.append(event.getFromUserName(),this.getFromUserName()).isEquals();
	}
	
	public int hashCode() {
		return new HashCodeBuilder().append(this.getCreateTime()).
				append(this.getEvent()).append(this.getFromUserName()).hashCode();
	}

}
