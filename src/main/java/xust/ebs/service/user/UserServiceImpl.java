package xust.ebs.service.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xust.ebs.dao.UserDao;
import xust.ebs.entity.User;
import xust.ebs.util.EbsResult;
import xust.ebs.util.EbsUtil;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

	@Resource
	private UserDao userDao;
	
	public EbsResult<User> checkLogin(String nickOrPhone, String password, String value) {
		EbsResult<User> result = new EbsResult<User>();
		User user = userDao.findByNickOrPhone(nickOrPhone);
		if("1".equals(value)){
			if(user == null){
				result.setStatus(1);
				result.setMsg("用户不存在");
				return result;
			}
			
			//检测密码
			if(!user.getUser_password().equals(EbsUtil.md5(password))){
				result.setStatus(2);
				result.setMsg("密码错误");
				return result;
			}
			
			result.setStatus(0);
			result.setMsg("登录成功");
			result.setData(user);
			return result;
		}
		result.setStatus(3);
		result.setMsg("出错啦！！！");
		return result;
	}
	
	public EbsResult<Object> addUser(String nick, String name, String phone, String tel, String company,
			String email, String password) {
		EbsResult<Object> result = new EbsResult<Object>();
		User user = new User();
		if(userDao.findByNickOrPhone(nick) != null){
			result.setStatus(1);
			result.setMsg("用户名已存在");
			return result;
		}
		
		if(userDao.findByNickOrPhone(phone) != null){
			result.setStatus(2);
			result.setMsg("该号码已经注册过了");
			return result;
		}
		user.setUser_company(company);
		user.setUser_email(email);
		user.setUser_name(name);
		user.setUser_nick(nick);
		user.setUser_password(EbsUtil.md5(password));
		user.setUser_phone(phone);
		user.setUser_tel(tel);
		userDao.save(user);
		result.setStatus(0);
		result.setMsg("注册成功");
		return result;
	}
	
	public EbsResult<User> showUserMsg(String userNick) {
		EbsResult<User> result = new EbsResult<User>();
		User user = userDao.findByNickOrPhone(userNick);
		if(user == null){
			result.setStatus(1);
			result.setMsg("出错啦！！！");
			return result;
		}
		result.setStatus(0);
		result.setMsg("查询成功");
		result.setData(user);
		return result;
	}
	
	public EbsResult<Object> updateUser(String userNick, String userPhone, String userTel, String userCompany,
			String userEmail, String userPassword) {
		EbsResult<Object> result = new EbsResult<Object>();
		User user = new User();
		user.setUser_nick(userNick);
		user.setUser_phone(userPhone);
		//System.out.println(userPhone+"-"+userTel);
		user.setUser_tel(userTel);
		user.setUser_company(userCompany);
		user.setUser_email(userEmail);
		user.setUser_password(EbsUtil.md5(userPassword));
		userDao.update(user);
		
		result.setStatus(0);
		result.setMsg("修改成功");
		//result.setData(user);
		return result;
	}
}
