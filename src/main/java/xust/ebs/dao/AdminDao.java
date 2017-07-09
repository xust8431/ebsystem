package xust.ebs.dao;

import xust.ebs.entity.Admin;

public interface AdminDao {
	/**
	 * 查询管理员
	 * @param adminName
	 * @return
	 */
	public Admin findByAdminName(String adminName);
	/**
	 * 更新管理员信息
	 * @param admin
	 */
	public void update(Admin admin);
}
