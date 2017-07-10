package test.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import xust.ebs.dao.ReserveDao;
import xust.ebs.entity.Reserve;
import xust.ebs.service.reserve.ReserveService;
import xust.ebs.util.EbsResult;

public class TestAdminReserve {

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
		ReserveService service = ac.getBean("reserveService", ReserveService.class);
		EbsResult<List<Reserve>> result = service.loadReserveInfo("1");
		for(Reserve r : result.getData()) {
			System.out.println(r.getReserve_id());
		}
	}
	
	@Test
	public void test2() {
		ReserveDao dao = ac.getBean("reserveDao", ReserveDao.class);
		Map<String, String> map = new HashMap<String, String>();
		map.put("examine_status", "1");
		List<Reserve> list = dao.loadReserve(map);
		for(Reserve r : list) {
			System.out.println(r.getReserve_id());
		}
	}
	
}
















