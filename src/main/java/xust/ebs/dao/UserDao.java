package xust.ebs.dao;

import java.util.List;

import xust.ebs.entity.User;

public interface UserDao {

	public User findByNickOrPhone(String nickOrPhone);
	public void save(User user);
	public void update(User user);
	/**
	 * 查询所有用户
	 * @return
	 */
	public List<User> findAll();
}
