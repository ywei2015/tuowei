package sail.beans.controller.batinterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import sail.beans.service.batinterface.BatTransYesiIntoCabinetService;

/**
 * 批次管理_PRODUCE_生产工单信息
 * @author cyj
 * 2017-02-03
 */
@Controller
public class BatTransYesiIntoCabinetController {
	@Autowired
	private BatTransYesiIntoCabinetService batTransYesiIntoCabinetService = null;
	/**
	 * 新增制丝中控－叶丝入柜
	 */
//	@Scheduled(cron = "0 49 16 * * ?")
//	@Scheduled(cron = "0 0/2 * * * ?")
	public void saveBatTransYesiIntoCabinet(){
		if(batTransYesiIntoCabinetService==null) return;
		batTransYesiIntoCabinetService.SaveBatTransYesiIntoCabinet();
	}
}
