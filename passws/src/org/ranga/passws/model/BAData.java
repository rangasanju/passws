package org.ranga.passws.model;

public class BAData {
	
	private String deviceid;
	private String devicename;
	private String srno;
	private String calibdate;
	private String recordno;
	private String badate;
	private String result;
	private String exvol;
	private String extime;
	
	
	
	
	
	public BAData()
	{
		
	}
	
	public BAData(String deviceid, String dn, String sr, String caldate, String no, String badate, String res, String exvol, String extime)
	{
		this.deviceid = deviceid;
		this.devicename = dn;
		
		this.srno = sr;
		this.calibdate = caldate;
		
		this.recordno = no;
		this.badate = badate;
		this.result = res;
		this.exvol = exvol;
		this.extime = extime;
		
	}

	
	
	
	
	public String getDeviceid() {
		return deviceid;
	}

	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}

	public String getDevicename() {
		return devicename;
	}

	public void setDevicename(String devicename) {
		this.devicename = devicename;
	}

	public String getSrno() {
		return srno;
	}

	public void setSrno(String srno) {
		this.srno = srno;
	}

	public String getRecordno() {
		return recordno;
	}

	public void setRecordno(String recordno) {
		this.recordno = recordno;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getCalibdate() {
		return calibdate;
	}

	public void setCalibdate(String calibdate) {
		this.calibdate = calibdate;
	}

	public String getBadate() {
		return badate;
	}

	public void setBadate(String badate) {
		this.badate = badate;
	}

	public String getExvol() {
		return exvol;
	}

	public void setExvol(String exvol) {
		this.exvol = exvol;
	}

	public String getExtime() {
		return extime;
	}

	public void setExtime(String extime) {
		this.extime = extime;
	}
	
	

}
