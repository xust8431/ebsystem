package xust.ebs.service.user;

import java.util.List;

import xust.ebs.entity.User;
import xust.ebs.util.EbsResult;

public interface UserService {

	public EbsResult<User> checkLogin(String nickOrPhone, String password, String value);
	
	public EbsResult<Object> addUser(String nick, String name, String phone, String tel, String company,
			String email, String password);
	
	/**
	 * 加载所有用户信息
	 * @return
	 */
	public EbsResult<List<User>> loadUserinfo();
}
