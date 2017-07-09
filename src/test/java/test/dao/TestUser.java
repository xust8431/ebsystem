package test.dao;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import xust.ebs.dao.AdminDao;
import xust.ebs.dao.UserDao;
import xust.ebs.entity.Admin;
import xust.ebs.entity.User;
import xust.ebs.service.user.UserService;
import xust.ebs.service.user.UserServiceImpl;
import xust.ebs.util.EbsResult;

public class TestUser {
	ApplicationContext ac = null;
	
	@Before
	public void init(){
		String[] conf = {
				"conf/spring-mybatis.xml",
				"conf/spring-mvc.xml"};
		ac = new ClassPathXmlApplicationContext(conf);
	}
	@Test
	public void test1(){
		UserDao dao = ac.getBean("userDao",UserDao.class);
		User user = dao.findByNickOrPhone("demo");
		if(user != null){
			System.out.println("用户名已存在");
		}else{
			System.out.println("用户名可以使用");
			return;
		}
		
		System.out.println(user.getUser_company());
	}
	
	@Test
	public void test2(){
		UserService userService = ac.getBean("userService",UserServiceImpl.class);
		EbsResult<User> result = userService.checkLogin("123", "123456", "1");
		System.out.println(result.getMsg());
	}
	
	@Test
	public void test3(){
		AdminDao dao = ac.getBean("adminDao",AdminDao.class);
		Admin admin = new Admin();
		admin.setAdmin_name("admin");
		admin.setAdmin_password("123456");
		admin.setAdmin_token("abcdefghijklmnopqrstuvwxyz");
		dao.update(admin);
		
	}
	
}

















