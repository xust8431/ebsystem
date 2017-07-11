package test.bean;

import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBean {

	private ApplicationContext ac = null;
	
	@Before
	public void init(){
		String[] conf = {
				"conf/spring-set.xml"};
		ac = new ClassPathXmlApplicationContext(conf);
	}
	
	@Test
	public void test1() {
		Properties p = ac.getBean("timeset", Properties.class);
		System.out.println(p);
	}
	
}

















