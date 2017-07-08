package xust.ebs.dao;

import xust.ebs.entity.Admin;

public interface AdminDao {
	public Admin findByAdminName(String adminName);
}
