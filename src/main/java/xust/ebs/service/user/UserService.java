package xust.ebs.service.user;

import xust.ebs.entity.User;
import xust.ebs.util.EbsResult;

public interface UserService {

	public EbsResult<User> checkLogin(String nickOrPhone, String password, String value);//登录
	
	public EbsResult<Object> addUser(String nick, String name, String phone, String tel, String company,
			String email, String password);//注册
	
	public EbsResult<User> showUserMsg(String userNick);//显示用户信息
	
	public EbsResult<Object> updateUser(String userNick, String userPhone, String userTel, String userCompany,
			String userEmail, String userPassword);//更新用户信息
}
