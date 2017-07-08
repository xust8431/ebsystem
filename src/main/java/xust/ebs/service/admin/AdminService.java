package xust.ebs.service.admin;

import xust.ebs.entity.Admin;
import xust.ebs.util.EbsResult;

public interface AdminService {
 
	public EbsResult<Admin> checkAdminLogin(String adminName, String password, String value);
}
