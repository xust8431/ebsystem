package xust.ebs.controller.reserve;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import xust.ebs.service.reserve.ReserveService;
import xust.ebs.util.EbsResult;

@Controller
@RequestMapping("/reserve")
public class SelectDateController {

	@Resource
	private ReserveService reserveService;
	@RequestMapping("/selectDate.do")
	@ResponseBody
	
	public EbsResult<Object> execute(String reserveHour, String reserveDate) throws ParseException{
		EbsResult<Object> result = reserveService.selectReserveDate(reserveHour, reserveDate);
		return result;
	}
}
