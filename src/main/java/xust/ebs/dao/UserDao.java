package xust.ebs.dao;

import xust.ebs.entity.User;

public interface UserDao {

	public User findByNickOrPhone(String nickOrPhone);
	public void save(User user);
}
