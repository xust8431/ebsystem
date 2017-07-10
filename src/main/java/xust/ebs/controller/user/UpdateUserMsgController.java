package xust.ebs.controller.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import xust.ebs.service.user.UserService;
import xust.ebs.util.EbsResult;

@Controller
@RequestMapping("/user")
public class UpdateUserMsgController {

	@Resource
	private UserService userService;
	
	@RequestMapping("/update.do")
	@ResponseBody
	
	public EbsResult<Object> execute(String userNick, String userPhone, String userTel, String userCompany,
			String userEmail, String userPassword){
		EbsResult<Object> result = userService.updateUser(userNick, userPhone, 
				userTel, userCompany, userEmail, userPassword);
		return result;
	}
}
