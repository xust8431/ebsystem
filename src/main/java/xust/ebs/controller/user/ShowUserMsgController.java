package xust.ebs.controller.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import xust.ebs.entity.User;
import xust.ebs.service.user.UserService;
import xust.ebs.util.EbsResult;

@Controller
@RequestMapping("/user")
public class ShowUserMsgController {

	@Resource
	private UserService userService;
	
	@RequestMapping("/show.do")
	@ResponseBody
	
	public EbsResult<User> execute(String userNick){
		EbsResult<User> result = userService.showUserMsg(userNick);
		return result;
	}
}
