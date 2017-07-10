package xust.ebs.controller.user;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import xust.ebs.entity.User;
import xust.ebs.service.user.UserService;
import xust.ebs.util.EbsResult;

@Controller
@RequestMapping("/user")
public class LoadUserinfoController {
	
	@Resource
	private UserService userService;

	@RequestMapping("/load_userinfo.do")
	@ResponseBody
	public EbsResult<List<User>> execute() {
		EbsResult<List<User>> result = userService.loadUserinfo();
		return result;
	}
	
}
