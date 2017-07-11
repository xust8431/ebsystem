package xust.ebs.controller.set;

import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import xust.ebs.service.set.SystemSetService;
import xust.ebs.util.EbsResult;

@Controller
@RequestMapping("/set")
public class LoadConfigController {

	@Resource
	private SystemSetService systemSetService;
	
	@RequestMapping("/load.do")
	@ResponseBody
	public EbsResult<Properties> execute() {
		EbsResult<Properties> result = systemSetService.loadSet();
		return result;
	}
	
}
