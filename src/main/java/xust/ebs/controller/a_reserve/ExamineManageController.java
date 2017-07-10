package xust.ebs.controller.a_reserve;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import xust.ebs.service.reserve.ReserveService;
import xust.ebs.util.EbsResult;

@Controller
@RequestMapping("/a_reserve")
public class ExamineManageController {

	@Resource
	private ReserveService reserveService;
	
	@RequestMapping("/examine_true.do")
	@ResponseBody
	public EbsResult<Object> execute(String reserveId, String status) {
		EbsResult<Object> result = reserveService.examineReserve(reserveId, status);
		return result;
	}
	
}
