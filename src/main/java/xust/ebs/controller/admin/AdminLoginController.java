package xust.ebs.controller.admin;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import xust.ebs.entity.Admin;
import xust.ebs.service.admin.AdminService;
import xust.ebs.util.EbsResult;

@Controller
@RequestMapping("/admin")
public class AdminLoginController {

	@Resource
	private AdminService adminService;
	
	@RequestMapping("/adminLogin.do")
	@ResponseBody
	
	public EbsResult<Admin> execute(String adminName, String password, String value){
		EbsResult<Admin> result = adminService.checkAdminLogin(adminName, password, value);
		return result;
	}
}
