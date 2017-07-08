package xust.ebs.controller.reserve;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import xust.ebs.service.reserve.ReserveService;
import xust.ebs.util.EbsResult;

@Controller
@RequestMapping("/reserve")
public class AddReserveController {

	@Resource
	private ReserveService reserveService;
	
	@RequestMapping("/addReserve.do")
	@ResponseBody
	
	public EbsResult<Object> execute(String userNick, String item, String hour, String date, String startTime,
			String endTime, String reputation) throws Exception{
		EbsResult<Object> result = reserveService.addReserveMsg(userNick, item, hour, date,
				startTime, endTime, reputation);
		return result;
	}
}
