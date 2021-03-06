package sail.beans.entity;

import java.io.Serializable;

/**
 * 嘴棒添加中转表
 * */
public class BatFiltertipPut implements Serializable{

	private String pid;
	private String transmitter; //滤棒投料对应发射机
	private String matbatch;
	private String matcode;
	private String matname;
	private String location;
	private String locationname;
	private Double quantity;
	private String unit;
	private String starttime;
	private String endtime;
	private String operateuserid;
	private String operateusername;
	private String operatetime;
	private String masterslaveflag;
	private String isdeal;   //是否已转储(0-默认未转储,1-已转储)
	private String remark1;      
	private String remark2;       
	private String remark3;   
	private String remark4;   
	private String remark5;  
	private String remark;  
	private String sysflag;
	private String creator;
	private String createtime;
	private String lastmodifier;
	private String lastmodifiedtime;
	private String isrepair;//是否重复 

	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getTransmitter() {
		return transmitter;
	}
	public void setTransmitter(String transmitter) {
		this.transmitter = transmitter;
	}
	public String getMatcode() {
		return matcode;
	}
	public void setMatcode(String matcode) {
		this.matcode = matcode;
	}
	public String getMatname() {
		return matname;
	}
	public void setMatname(String matname) {
		this.matname = matname;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getLocationname() {
		return locationname;
	}
	public void setLocationname(String locationname) {
		this.locationname = locationname;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getOperateuserid() {
		return operateuserid;
	}
	public void setOperateuserid(String operateuserid) {
		this.operateuserid = operateuserid;
	}
	public String getOperateusername() {
		return operateusername;
	}
	public void setOperateusername(String operateusername) {
		this.operateusername = operateusername;
	}
	public String getOperatetime() {
		return operatetime;
	}
	public void setOperatetime(String operatetime) {
		this.operatetime = operatetime;
	}
	public String getMasterslaveflag() {
		return masterslaveflag;
	}
	public void setMasterslaveflag(String masterslaveflag) {
		this.masterslaveflag = masterslaveflag;
	}
	public String getIsdeal() {
		return isdeal;
	}
	public void setIsdeal(String isdeal) {
		this.isdeal = isdeal;
	}
	public String getRemark1() {
		return remark1;
	}
	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}
	public String getRemark2() {
		return remark2;
	}
	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}
	public String getRemark3() {
		return remark3;
	}
	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}
	public String getRemark4() {
		return remark4;
	}
	public void setRemark4(String remark4) {
		this.remark4 = remark4;
	}
	public String getRemark5() {
		return remark5;
	}
	public void setRemark5(String remark5) {
		this.remark5 = remark5;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getSysflag() {
		return sysflag;
	}
	public void setSysflag(String sysflag) {
		this.sysflag = sysflag;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getLastmodifier() {
		return lastmodifier;
	}
	public void setLastmodifier(String lastmodifier) {
		this.lastmodifier = lastmodifier;
	}
	public String getLastmodifiedtime() {
		return lastmodifiedtime;
	}
	public void setLastmodifiedtime(String lastmodifiedtime) {
		this.lastmodifiedtime = lastmodifiedtime;
	}
	public String getIsrepair() {
		return isrepair;
	}
	public void setIsrepair(String isrepair) {
		this.isrepair = isrepair;
	}
	public String getMatbatch() {
		return matbatch;
	}
	public void setMatbatch(String matbatch) {
		this.matbatch = matbatch;
	}

	
	
	
	
}
