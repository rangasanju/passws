package org.ranga.passws.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RequestData {
	
	private String deviceId;
	private String requestStatus;
	private String bookingstatus;
	
	public RequestData()
	{
		
	}
	
	public RequestData(String id, String status, String bookingstatus)
	{
		this.deviceId = id;
		this.requestStatus = status;
		this.bookingstatus = bookingstatus;
	}
	
	
	
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getRequestStatus() {
		return requestStatus;
	}
	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}

	public String getBookingstatus() {
		return bookingstatus;
	}

	public void setBookingstatus(String bookingstatus) {
		this.bookingstatus = bookingstatus;
	}
	
	
	
	

}
