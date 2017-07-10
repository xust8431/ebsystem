package xust.ebs.controller.a_reserve;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import xust.ebs.entity.Reserve;
import xust.ebs.service.reserve.ReserveService;
import xust.ebs.util.EbsResult;

@Controller
@RequestMapping("/a_reserve")
public class LoadReserveInfoController {

	@Resource
	private ReserveService reserveService;
	
	@RequestMapping("/load_reserve.do")
	@ResponseBody
	public EbsResult<List<Reserve>> loadAllReserve(String status) {
		EbsResult<List<Reserve>> result = reserveService.loadReserveInfo(status);
		return result;
	}
	
}
