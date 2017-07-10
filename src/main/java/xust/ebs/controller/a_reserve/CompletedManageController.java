package xust.ebs.controller.a_reserve;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import xust.ebs.service.reserve.ReserveService;
import xust.ebs.util.EbsResult;

@Controller
@RequestMapping("/a_reserve")
public class CompletedManageController {

	@Resource
	private ReserveService reserveService;
	
	@RequestMapping("/completed.do")
	@ResponseBody
	public EbsResult<Object> execute(String reserveId) {
		EbsResult<Object> result = reserveService.completedReserve(reserveId);
		return result;
	}
	
}
