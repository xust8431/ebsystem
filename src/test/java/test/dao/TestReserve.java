package test.dao;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import xust.ebs.dao.ReserveDao;
import xust.ebs.entity.Reserve;
import xust.ebs.service.reserve.ReserveService;
import xust.ebs.util.EbsResult;

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
	
	@Test
	public void test2(){
		ReserveDao dao = ac.getBean("reserveDao",ReserveDao.class);
		List<Reserve> re = dao.findByNick("demo");
		System.out.println(re.get(0).getReserve_time());
	}
	
	@Test
	public void test3(){
		ReserveService re = ac.getBean("reserveService",ReserveService.class);
		EbsResult<List<Reserve>> result = re.selectReserveMsg("demo");
		System.out.println(result.getData().get(0).getReserve_time());
	}
}
