package xust.ebs.controller.reserve;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import xust.ebs.entity.ReserveFailed;
import xust.ebs.service.reserve.ReserveService;
import xust.ebs.util.EbsResult;

@Controller
@RequestMapping("/reserve")
public class ReasonInfoController {

	@Resource
	private ReserveService reserveService;
	
	@RequestMapping("/reason_info.do")
	@ResponseBody
	public EbsResult<ReserveFailed> execute(String reserveId) {
		EbsResult<ReserveFailed> result = reserveService.selectReasonInfo(reserveId);
		return result;
	}
	
}
