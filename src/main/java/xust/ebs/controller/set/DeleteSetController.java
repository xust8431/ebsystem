package xust.ebs.controller.set;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import xust.ebs.service.set.SystemSetService;
import xust.ebs.util.EbsResult;

@Controller
@RequestMapping("/set")
public class DeleteSetController {

	@Resource
	private SystemSetService systemSetService;
	
	@RequestMapping("/delete_set.do")
	@ResponseBody
	public EbsResult<Object> execute(String timeStr) {
		EbsResult<Object> result = systemSetService.deleteSet(timeStr);
		return result;
	}
	
}
