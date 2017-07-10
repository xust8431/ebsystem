package xust.ebs.service.reserve;



import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xust.ebs.dao.ReserveDao;
import xust.ebs.entity.Reserve;
import xust.ebs.util.EbsResult;
import xust.ebs.util.EbsUtil;

@Service("reserveService")
@Transactional
public class ReserveServiceImpl implements ReserveService{

	@Resource
	private ReserveDao reserveDao;
	
	//预约实验
	public EbsResult<Object> addReserveMsg(String userNick, String item, String hour, String date, String startTime,
			String endTime, String reputation) throws Exception {
		EbsResult<Object> result = new EbsResult<Object>();
		Reserve reserve = new Reserve();
		if(Integer.valueOf(reputation) >= -3){
			reserve.setReserve_complete_status("0");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date time = sdf.parse(date);
			java.sql.Date d = new java.sql.Date(time.getTime());
			reserve.setReserve_date(d);
			SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
			Time end = new Time(sdf2.parse(endTime).getTime());
			reserve.setReserve_endtime(end);
			Time start = new Time(sdf2.parse(startTime).getTime());
			reserve.setReserve_starttime(start);
			reserve.setReserve_examine_status("0");
			reserve.setReserve_hour(Integer.valueOf(hour));
			reserve.setReserve_id(EbsUtil.createId());
			reserve.setReserve_item(item);
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			reserve.setReserve_time(ts);
			reserve.setUser_nick(userNick);
			reserveDao.save(reserve);
			
			result.setStatus(0);
			result.setMsg("预约成功，待审核");
			return result;
		}
		result.setStatus(1);
		result.setMsg("您的信誉度过低，不可预约");
		return result;
	}
	
	//查看已预约的实验
	public EbsResult<List<Reserve>> selectReserveMsg(String userNick) {
		EbsResult<List<Reserve>> result = new EbsResult<List<Reserve>>();
		List<Reserve> list = reserveDao.findByNick(userNick);
		if(list == null){
			result.setStatus(1);
			result.setMsg("您还没有预约实验");
			return result;
		}
		result.setStatus(0);
		result.setMsg("查询成功");
		result.setData(list);
		return result;
	}

	public EbsResult<List<Reserve>> loadReserveInfo(String status) {
		Map<String, String> map = new HashMap<String, String>();
		List<Reserve> reserves = null;
		EbsResult<List<Reserve>> result = new EbsResult<List<Reserve>>();
		if("1".equals(status)) {
			map.put("examine_status", "0");
			reserves = reserveDao.loadReserve(map);
		} else if("2".equals(status)) {
			map.put("examine_status", "1");
			map.put("complete_status", "0");
			reserves = reserveDao.loadReserve(map);
		} else if("3".equals(status)) {
			map.put("examine_status", "1");
			map.put("complete_status", "1");
			reserves = reserveDao.loadReserve(map);
		}
		result.setStatus(0);
		result.setMsg("查询成功");
		result.setData(reserves);
		return result;
	}

	public EbsResult<Object> examineReserve(String reserveId, String status) {
		EbsResult<Object> result = new EbsResult<Object>();
		Map<String, String> map = new HashMap<String, String>();
		map.put("reserveId", reserveId);
		map.put("status", status);
		reserveDao.updateExamine(map);
		result.setStatus(0);
		result.setMsg("更新成功");
		return result;
	}

	public EbsResult<Object> completedReserve(String reserveId) {
		EbsResult<Object> result = new EbsResult<Object>();
		reserveDao.updateCompleted(reserveId);
		result.setStatus(0);
		result.setMsg("更新成功");
		return result;
	}
}
















