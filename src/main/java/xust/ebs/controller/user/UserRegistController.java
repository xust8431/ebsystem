package xust.ebs.controller.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import xust.ebs.service.user.UserService;
import xust.ebs.util.EbsResult;

@Controller
@RequestMapping("/user")
public class UserRegistController {

	@Resource
	private UserService userService;
	
	@RequestMapping("/add.do")
	@ResponseBody
	
	public EbsResult<Object> execute(String nick, String name, String phone, String tel, String company,
			String email, String password){
		EbsResult<Object> result = userService.addUser(nick, name, phone, tel,
				company, email, password);
		return result;
		
	}
	
}
