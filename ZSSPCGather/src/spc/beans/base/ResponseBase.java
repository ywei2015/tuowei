package spc.beans.base;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import spc.beans.dao.iml.PaginationSupport;


/**
* <p>类说明：Spring MVC Response Base Class</p>  
* <p>Copyright: Copyright (c) 2016</p> 
* @author xie&li
* 2016-08-26 
* @version 1.0 
 * 
 */
public class ResponseBase implements Serializable {
	private static final long serialVersionUID = 1223013741741997981L;
	
	private ResponseStatus response = new ResponseStatus();

	@SuppressWarnings("rawtypes")
	private Map dataset = new HashMap(); 
	private long totalRecords = 0;

	public ResponseBase(){
		if(dataset.containsKey("response"))
			dataset.remove("response");
		dataset.put("response",response);
		if(dataset.containsKey("totalRecords"))
			dataset.remove("totalRecords");
		dataset.put("totalRecords",totalRecords);
	}
	 
	public void setResponseData(boolean success, String content){
		this.response.setSuccess(success);
		this.response.setContent(content);
		
		if(dataset.containsKey("response"))
			dataset.remove("response");
		dataset.put("response",response);
	}
	 
	public Map getDataset() {
		return dataset;
	}
 
	public void setDataset(Object obj, String entityname) {
		if(obj==null) return;
		this.dataset.put(entityname, obj);
		this.setTotalRecords(1);
	}
	public void setDataset(List _dataset, String entityname) {
		if(_dataset==null) return;
		this.dataset.put(entityname, _dataset);
		this.setTotalRecords(_dataset.size());
	}

	public void setDataset(PaginationSupport ps, String entityname) {
		if(ps==null) return;
		this.dataset.put(entityname, ps.getItems());
		this.setTotalRecords(ps.getTotalCount());
	}
 
	public void setTotalRecords(long _totalRecords) {
		this.totalRecords = _totalRecords;
		if(dataset.containsKey("totalRecords"))
			dataset.remove("totalRecords");
		dataset.put("totalRecords",totalRecords);
	}
 
	
}
