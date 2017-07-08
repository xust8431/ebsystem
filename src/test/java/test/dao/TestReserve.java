package test.dao;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import xust.ebs.dao.ReserveDao;
import xust.ebs.entity.Reserve;

public class TestReserve {

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
		ReserveDao dao = ac.getBean("reserveDao",ReserveDao.class);
		List<Reserve> re = dao.selectReserveTime();
		if(re == null){
			System.out.println("暂无记录");
			return;
		}
		System.out.println(re.get(0).getReserve_endtime());
	}
}
