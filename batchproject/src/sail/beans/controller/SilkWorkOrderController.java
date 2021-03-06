package sail.beans.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sail.beans.base.ResponseBase;
import sail.beans.entity.BatDepotIoDetail;
import sail.beans.entity.BatWorkOrder;
import sail.beans.entity.BatWorkOrderInput;
import sail.beans.entity.User;
import sail.beans.entity.vo.BatWorkOrderVo;
import sail.beans.service.SilkWorkOrderService;

/**
 * 投料记录管理类
 * */
@Controller
@RequestMapping("/silkorder")
public class SilkWorkOrderController {

	@Resource
	private SilkWorkOrderService silkWorkOrderService; 
	
	/**
	 * 工单信息获取
	 * @param request
	 * */
	@ResponseBody
	@RequestMapping(value="/getWorkOrderList")	 
	public ResponseBase getWorkOrderList(HttpServletRequest request){
		String type = request.getParameter("f_type");
		ResponseBase res = new ResponseBase();
		List<BatWorkOrderVo> orderList = silkWorkOrderService.getWorkOrderList(type);
		if (orderList != null && orderList.size() > 0){
			res.setResponseData("1", "操作成功!");
			res.setDataset(orderList, "batworkordervo");
		}else{
			res.setResponseData("0", "该类型不存在工单!");
		}
		return res;
	}
	
	/**
	 * 投料记录保存
	 * @param request
	 * */
	@ResponseBody
	@RequestMapping(value="/saveBatWorkOrderInput")	 
	public ResponseBase saveBatWorkOrderInput(HttpServletRequest request) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		String workOrderCode = request.getParameter("f_workorder_code");
		if(workOrderCode.contains("-")){
			workOrderCode=workOrderCode.substring(workOrderCode.lastIndexOf("-")+1, workOrderCode.length());
		}
		String type=request.getParameter("type");//解码类型
		String matBatch = request.getParameter("f_mat_batch");
		String quantity = request.getParameter("f_quantity");
		String location = request.getParameter("location");
		String userId = request.getParameter("userId");
		String tl_type=request.getParameter("f_tl_type");
		String remark=request.getParameter("remark");
		ResponseBase res = new ResponseBase();
			BatWorkOrderInput batWorkOrderInput = silkWorkOrderService.saveBatWorkOrderInput(workOrderCode, matBatch, quantity, location,userId,tl_type,remark,type);
			if (batWorkOrderInput != null){
				if ("1".equals(batWorkOrderInput.getIsrepair())){
					res.setResponseData("0", "该批次数据已经存在!");
				}else{
					if("e".equalsIgnoreCase(batWorkOrderInput.getRemark5())){
						res.setResponseData("0", "操作失败,该批次处于禁止状态!");
					}else if("2".equalsIgnoreCase(batWorkOrderInput.getRemark5())){
						res.setResponseData("0", "操作失败,该批次处于冻结状态!");
					}else{
						res.setResponseData("1", "操作成功!");
						res.setDataset(batWorkOrderInput, "batworkorderinput");
					}
				}
			}else{
				res.setResponseData("0", "该批次信息有误,请进行核对!");
			}
		return res;
	}
	
	/**
	 * 投料记录删除
	 * @param request
	 * */
	@ResponseBody
	@RequestMapping(value="/deleteBatWorkOrderInput")	 
	public ResponseBase deleteBatWorkOrderInput(HttpServletRequest request){
		String pid = request.getParameter("f_pid");
		String userId = request.getParameter("userId");
		ResponseBase res = new ResponseBase();
		boolean falg = silkWorkOrderService.deleteBatWorkOrderInput(pid, userId);
		if (falg){
			res.setResponseData("1", "操作成功!");
		}else{
			res.setResponseData("0", "删除数据失败!");
		}
		return res;
	}
	
	/**
	 * 投料记录获取
	 * @param request
	 * */
	@ResponseBody
	@RequestMapping(value="/getBatWorkOrderInput")	 
	public ResponseBase getBatWorkOrderInput(HttpServletRequest request){
		String workOrderType=request.getParameter("f_workorder_type");
		String workOrderCode = request.getParameter("f_workorder_code");
		if(workOrderCode!=null){
			if(workOrderCode.contains("-")){
				workOrderCode=workOrderCode.substring(workOrderCode.lastIndexOf("-")+1, workOrderCode.length());
			}
		}
		String tl_type=request.getParameter("f_tl_type");
		String remark=request.getParameter("remark");
		ResponseBase res = new ResponseBase();
		List<BatWorkOrderInput> inputList = silkWorkOrderService.getBatWorkOrderInput(workOrderCode,tl_type,workOrderType,remark);
		if (inputList != null && inputList.size() > 0){
			res.setResponseData("1", "操作成功!");
			res.setDataset(inputList, "batdepotiodetail");
		}else{
			res.setResponseData("0", "该工单暂无数据!");
		}
		return res;
	}
	/**
	 * 香料其它消耗业务特殊出库保存
	 * */
	@ResponseBody
	@RequestMapping(value="/saveBatchStorageOut")	 
	public ResponseBase saveBatchStorageOut(HttpServletRequest request){
		ResponseBase res = new ResponseBase();
		String f_mat_batch = request.getParameter("f_mat_batch");
		String reason = request.getParameter("f_reason");
		String userId = request.getParameter("userId");
		BatDepotIoDetail batDepotIoDetail = silkWorkOrderService.saveBatchStorageOut(reason,f_mat_batch,userId);
		if (batDepotIoDetail != null){
			if("1".equals(batDepotIoDetail.getRepeated())){
				res.setResponseData("0", "该批次已经出库!");
				res.setDataset(batDepotIoDetail, "batdepotiodetail");
			}else{
				res.setResponseData("1", "操作成功!");
				res.setDataset(batDepotIoDetail, "batdepotiodetail");
			}
			
		}else{
			res.setResponseData("0", "该批次信息有误,请进行核对!");
		}
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteIeafInput")	 
	public ResponseBase deleteIeafInput(HttpServletRequest request){
		String batch = request.getParameter("f_batch");
		String userId = request.getParameter("userId");
		ResponseBase res = new ResponseBase();
		boolean falg = silkWorkOrderService.deleteIeafInput(batch, userId);
		if (falg){
			res.setResponseData("1", "操作成功!");
		}else{
			res.setResponseData("0", "删除数据失败!");
		}
		return res;
	}
		
	
}
