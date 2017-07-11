package test.service;

import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import xust.ebs.service.set.SystemSetService;
import xust.ebs.util.EbsResult;

public class TestSetService {

private ApplicationContext ac = null;
	
	@Before
	public void init(){
		String[] conf = {
				"conf/spring-set.xml",
				"conf/spring-mvc.xml",
				"conf/spring-mybatis.xml",
				"conf/spring-transaction.xml"};
		ac = new ClassPathXmlApplicationContext(conf);
	}
	
	@Test
	public void test1() {
		SystemSetService service = ac.getBean("systemSetService", SystemSetService.class);
		String banTimeStr = "1/14:00:00-16:00";
		String timeScope = "7";
		String selectMode = "1";
		EbsResult<Object> result = service.addSet(banTimeStr, timeScope, selectMode);
		System.out.println(result.getStatus());
	}
	
	@Test
	public void test2() {
		System.out.println(ac.getBean("timeset", Properties.class));
	}
}
















