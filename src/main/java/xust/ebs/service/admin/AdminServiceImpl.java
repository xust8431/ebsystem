package xust.ebs.service.admin;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xust.ebs.dao.AdminDao;
import xust.ebs.entity.Admin;
import xust.ebs.util.EbsResult;
import xust.ebs.util.EbsUtil;

@Service("adminService")
@Transactional
public class AdminServiceImpl implements AdminService{

	@Resource
	private AdminDao adminDao;
	
	public EbsResult<Admin> checkAdminLogin(String adminName, String password, String value) {
		EbsResult<Admin> result = new EbsResult<Admin>();
		Admin admin = adminDao.findByAdminName(adminName);
		if("2".equals(value)){
			if(admin == null){
				result.setStatus(1);
				result.setMsg("管理员不存在");
				return result;
			}
			
			if(!admin.getAdmin_password().equals(EbsUtil.md5(password))){
				result.setStatus(2);
				result.setMsg("密码错误");
				return result;
			}
			
			//更新管理员token
			String token = EbsUtil.createId();
			admin.setAdmin_token(token);
			adminDao.update(admin);
			result.setStatus(0);
			result.setMsg("登录成功");
			result.setData(admin);
			return result;
		}
		result.setStatus(3);
		result.setMsg("出错啦！！！");
		return result;
	}
}
