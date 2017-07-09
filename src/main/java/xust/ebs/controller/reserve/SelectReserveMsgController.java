package xust.ebs.controller.reserve;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import xust.ebs.entity.Reserve;
import xust.ebs.service.reserve.ReserveService;
import xust.ebs.util.EbsResult;

@Controller
@RequestMapping("/reserve")
public class SelectReserveMsgController {

	@Resource
	private ReserveService reserveService;
	
	@RequestMapping("/select.do")
	@ResponseBody
	
	public EbsResult<List<Reserve>> execute(String userNick){
		EbsResult<List<Reserve>> result = reserveService.selectReserveMsg(userNick);
		return result;
	}
}
