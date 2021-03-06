package sail.beans.controller.batinterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import sail.beans.service.batinterface.BatTransPermixTankOrderService;

/**
 * 批次管理_PRODUCE_生产工单信息
 * @author cyj
 * 2017-02-03
 */
@Controller
public class BatTransPermixTankOrderController {
	@Autowired
	private BatTransPermixTankOrderService batTransPermixTankOrderService = null;
	/**
	 * 新增制丝中控－叶片入预混柜
	 */
//	@Scheduled(cron = "0 29 09 * * ?")
//	@Scheduled(cron = "0 0/3 * * * ?")
	public void saveBatTransPermixTankOrder(){
		if(batTransPermixTankOrderService==null) return;
		batTransPermixTankOrderService.SaveBatTransPermixTankOrder();
	}
}
