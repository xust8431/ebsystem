package xust.ebs.controller.reserve;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import xust.ebs.service.reserve.ReserveService;
import xust.ebs.util.EbsResult;

@Controller
@RequestMapping("/reserve")
public class UpdateCancelController {

	@Resource
	private ReserveService reserveService;
	@RequestMapping("/updateCancel.do")
	@ResponseBody
	
	public EbsResult<Object> execute(String reserveId, String reserveDate) throws Exception{
		EbsResult<Object> result = reserveService.cancelReserver(reserveId, reserveDate);
		return result;
	}
}
