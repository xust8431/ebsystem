package xust.ebs.controller.set;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import xust.ebs.service.set.SystemSetService;
import xust.ebs.util.EbsResult;

@Controller
@RequestMapping("/set")
public class ConfigTimeController {

	@Resource
	private SystemSetService systemSetService;
	
	@RequestMapping("/set_time.do")
	@ResponseBody
	public EbsResult<Object> execute(String timeStr, String timeScope, String mode) {
		EbsResult<Object> result = systemSetService.addSet(timeStr, timeScope, mode);
		return result;
	}
	
}
