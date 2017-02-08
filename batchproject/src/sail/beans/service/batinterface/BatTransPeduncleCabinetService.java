package sail.beans.service.batinterface;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sail.beans.Constants;
import sail.beans.dao.GenericDao;
import sail.beans.entity.BatWorkOrder;
import sail.beans.entity.BatWorkOrderInput;
import sail.beans.entity.BatWorkOrderOutput;
import sail.beans.entity.UBatTransPeduncleCabinet;
import sail.beans.support.DateBean;

@Service
public class BatTransPeduncleCabinetService extends CommonService{
	@Autowired
	private GenericDao genericDao;  
	
	/**
	 * 获取待转储并新增生产工单投料后台服务（梗丝进柜）
	 * @return
	 */
	public void saveBatTransPeduncleCabinet(){
		try{
			List<UBatTransPeduncleCabinet> mainList = genericDao.getListWithVariableParas("SYNCHRO.U_BAT_TRANSPEDUNCLECABINET.LIST", new Object[]{});
			UBatTransPeduncleCabinet order = null;
			if (mainList != null && mainList.size() > 0){
				for(int i=0;i<mainList.size();i++){
					String workorderCode = mainList.get(i).getWorkorderCode().toString();
					BatWorkOrder batWorkOrder = this.getWorkorderByCode(workorderCode);
					if(batWorkOrder != null && !"".equals(batWorkOrder)){
						BatWorkOrderOutput output = new BatWorkOrderOutput();
						order = mainList.get(0);
						output.setWorkorderpid(batWorkOrder.getPid());
						output.setMatbatch(order.getMatBatch()==null?"":order.getMatBatch().toString());
						output.setWater(null);
						output.setLocation(order.getLocation()==null?"":order.getLocation().toString());
						output.setLocationname(order.getLocationName()==null?"":order.getLocationName().toString());
						output.setStime2(order.getActualStarttime()==null?"":order.getActualStarttime().toString());
						output.setEtime2(order.getActualEndtime()==null?"":order.getActualEndtime().toString());
						output.setQuantity(order.getQuantity()==null?0.0:Double.parseDouble(order.getQuantity().toString()));
						output.setUnit(order.getUnit()==null?"":order.getUnit().toString());
						output.setDepot(Constants.DEPOT);
						output.setOperateuserid(order.getOperateUsercode()==null?"":order.getOperateUsercode().toString());
						output.setRemark(order.getRemark()==null?"":order.getRemark().toString());
						output.setSysflag(Constants.SYS_FLAG_USEING);
						output.setCreator(Constants.USERID);
						output.setCreatetime(DateBean.getSysdateTime());
						genericDao.save(output);
						//转储完数据后更新转储状态
						UBatTransPeduncleCabinet main1 = (UBatTransPeduncleCabinet)genericDao.getById(UBatTransPeduncleCabinet.class,order.getPid());
						main1.setSynchroFlag(Constants.SYN_CHRO_USED);
						main1.setSynchroTime(DateBean.getSysdateTime());
						genericDao.save(main1);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
