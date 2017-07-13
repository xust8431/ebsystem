package xust.ebs.service.reserve;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xust.ebs.dao.ReserveDao;
import xust.ebs.dao.ReserveFailedDao;
import xust.ebs.entity.Reserve;
import xust.ebs.entity.ReserveFailed;
import xust.ebs.util.EbsResult;
import xust.ebs.util.EbsUtil;

@Service("reserveService")
@Transactional
public class ReserveServiceImpl implements ReserveService {

	@Resource
	private ReserveDao reserveDao;
	@Resource
	private ReserveFailedDao reserveFailedDao;

	@Resource
	private Properties timeset;
	
	//预约实验
	public EbsResult<Object> addReserveMsg(String userNick, String item, String hour, String date, String startTime,
			String reputation) throws Exception {
		EbsResult<Object> result = new EbsResult<Object>();
		Reserve reserve = new Reserve();
		if (timeset.get("selectMode").equals("1")) {
			if (Integer.valueOf(reputation) >= -3) {
				reserve.setReserve_complete_status("0");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date time = sdf.parse(date);
				java.sql.Date d = new java.sql.Date(time.getTime());
				reserve.setReserve_date(d);
				SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
				Time start = new Time(sdf2.parse(startTime).getTime());
				reserve.setReserve_starttime(start);
				String endTime = null;
				Time end;
				if(Integer.valueOf(hour) >= 6){
					endTime = start.getHours() + Integer.valueOf(hour) + 2 + ":00:00";
					end = new Time(sdf2.parse(endTime).getTime());
				}else{
					endTime = start.getHours() + Integer.valueOf(hour) + ":00:00";
					end = new Time(sdf2.parse(endTime).getTime());
				}
				reserve.setReserve_endtime(end);
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
			} else {
				result.setStatus(1);
				result.setMsg("您的信誉度过低，不可预约");
				return result;
			}
		} else {
			if (Integer.valueOf(reputation) >= -3) {
				reserve.setReserve_complete_status("0");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date time = sdf.parse(date);
				java.sql.Date d = new java.sql.Date(time.getTime());
				reserve.setReserve_date(d);
				SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
				Time start = new Time(sdf2.parse(startTime).getTime());
				reserve.setReserve_starttime(start);
				String endTime = start.getHours() + Integer.valueOf(hour) + ":00:00";
				Time end = new Time(sdf2.parse(endTime).getTime());
				reserve.setReserve_endtime(end);
				reserve.setReserve_examine_status("1");
				reserve.setReserve_hour(Integer.valueOf(hour));
				reserve.setReserve_id(EbsUtil.createId());
				reserve.setReserve_item(item);
				Timestamp ts = new Timestamp(System.currentTimeMillis());
				reserve.setReserve_time(ts);
				reserve.setUser_nick(userNick);
				reserveDao.save(reserve);

				result.setStatus(2);
				result.setMsg("预约成功");
				return result;
			} else {
				result.setStatus(1);
				result.setMsg("您的信誉度过低，不可预约");
				return result;
			}
		}

	}

	// 查看已预约的实验
	public EbsResult<List<Reserve>> selectReserveMsg(String userNick) {
		EbsResult<List<Reserve>> result = new EbsResult<List<Reserve>>();
		List<Reserve> list = reserveDao.findByNick(userNick);
		if (list == null) {
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
		if ("1".equals(status)) {
			map.put("examine_status", "0");
			reserves = reserveDao.loadReserve(map);
		} else if ("2".equals(status)) {
			map.put("examine_status", "1");
			map.put("complete_status", "0");
			reserves = reserveDao.loadReserve(map);
		} else if ("3".equals(status)) {
			map.put("examine_status", "1");
			map.put("complete_status", "1");
			reserves = reserveDao.loadReserve(map);
		}
		result.setStatus(0);
		result.setMsg("查询成功");
		result.setData(reserves);
		return result;
	}

	public EbsResult<Object> examineReserve(String reserveId, String status, String reason) {
		EbsResult<Object> result = new EbsResult<Object>();
		Map<String, String> map = new HashMap<String, String>();
		map.put("reserveId", reserveId);
		map.put("status", status);
		reserveDao.updateExamine(map);
		if("2".equals(status)) {
			ReserveFailed reserve = new ReserveFailed();
			reserve.setReserve_failed_id(reserveId);
			reserve.setReserve_failed_reason(reason);
			reserveFailedDao.save(reserve);
		}
		result.setStatus(0);
		result.setMsg("更新成功");
		return result;
	}

	public EbsResult<Object> completedReserve(String reserveId) {
		EbsResult<Object> result = new EbsResult<Object>();
		Map<String, String> map = new HashMap<String, String>();
		map.put("reserveId", reserveId);
		map.put("status", "1");
		reserveDao.updateCompleted(map);
		result.setStatus(0);
		result.setMsg("更新成功");
		return result;
	}

	public EbsResult<Object> cancelReserver(String reserveId, String reserveDate) throws Exception {
		EbsResult<Object> result = new EbsResult<Object>();
		Map<String, String> map = new HashMap<String, String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = sdf.parse(reserveDate);
		java.sql.Date d1 = new java.sql.Date(date1.getTime());
		// 获取当前时间
		Date ndate = new Date();
		String time = sdf.format(ndate);
		Date date2 = sdf.parse(time);
		java.sql.Date d2 = new java.sql.Date(date2.getTime());

		// 比较两个时间差
		if (EbsUtil.getIntervalDays(d2, d1) < 2) {
			result.setStatus(1);
			result.setMsg("2个工作日内请电话联系管理员取消预约");
			return result;
		}

		map.put("reserveId", reserveId);
		map.put("status", "2");
		reserveDao.updateCompleted(map);
		result.setStatus(0);
		result.setMsg("取消预约成功");
		return result;
	}

	public EbsResult<Object> selectReserveDate(String reserveHour, String reserveDate) throws ParseException {
		EbsResult<Object> result = new EbsResult<Object>();
		List<Reserve> re = reserveDao.selectReserveTime(reserveDate);// 查询数据库已经占用的时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(reserveDate);
		SimpleDateFormat sdf2 = new SimpleDateFormat("EEEE");
		SimpleDateFormat sdf3 = new SimpleDateFormat("HH:mm:ss");
		String week = sdf2.format(date);
		String time = "08:00:00";
		Time t = new Time(sdf3.parse(time).getTime());
		List<String> startTime = new ArrayList<String>();
		if ("星期五".equals(week)) {// 是否是星期五
			if (re.size() != 0) {
				for (int i = 0; i < re.size(); i++) {
					if (re.get(i).getReserve_hour() == 2
							&& sdf3.format(re.get(i).getReserve_starttime()).equals(time)) {// 判断时长为2并且8点被用
						switch (Integer.valueOf(reserveHour)) {
						case 2:
							startTime.add(t.getHours() + 2 + ":00:00");
							break;
						}
						result.setMsg("星期五上午如果8点被用");
						result.setData(startTime);
						result.setStatus(0);
						//return result;
					} else if (re.get(i).getReserve_hour() == 4) {
						result.setStatus(1);
						result.setMsg("周五上午已经没时间了");
						//return result;
					}
				}
				return result;
			} else {
				switch (Integer.valueOf(reserveHour)) {
				case 2:
					startTime.add(time);
					break;
				case 4:
					startTime.add(t.getHours() + 2 + ":00:00");
					break;
				}
				result.setStatus(2);
				result.setMsg("周五都是空闲时间");
				result.setData(startTime);
				return result;
			}
		} 
		
		if(!"星期五".equals(week)){
			if (re.size() != 0) {
				for (int i = 0; i < re.size(); i++) {
					if (re.get(i).getReserve_hour() == 2
							&& sdf3.format(re.get(i).getReserve_starttime()).equals(time)) {
						startTime.clear();
						switch (Integer.valueOf(reserveHour)) {
						case 2:
							startTime.add(t.getHours() + 6 + ":00:00");
							break;
						case 4:
							startTime.add(t.getHours() + 6 + ":00:00");
							break;
						// case 6: startTime.add(t.getHours()+2+":00:00");break;
						}
						result.setStatus(3);
						result.setMsg("2小时，只有8点被占");
						result.setData(startTime);
						//return result;
					} else if (re.get(i).getReserve_hour() == 2
							&& sdf3.format(re.get(i).getReserve_starttime()).equals("14:00:00")) {
						startTime.clear();
						switch (Integer.valueOf(reserveHour)) {
						case 2:
							startTime.add(t.getHours() + 2 + ":00:00");
							startTime.add(t.getHours() + 8 + ":00:00");
							break;
						}
						result.setStatus(4);
						result.setMsg("2小时，8点和14点被占");
						result.setData(startTime);
						//return result;
					} else if (re.get(i).getReserve_hour() == 4
							&& sdf3.format(re.get(i).getReserve_starttime()).equals("8:00:00")) {
						startTime.clear();
						switch (Integer.valueOf(reserveHour)) {
						case 2:
							startTime.add(t.getHours() + 6 + ":00:00");
							break;
						case 4:
							startTime.add(t.getHours() + 6 + ":00:00");
							break;
						}
						result.setStatus(5);
						result.setMsg("4小时，8点被占");
						result.setData(startTime);
						// return result;
					} else if (re.get(i).getReserve_hour() == 6
							&& sdf3.format(re.get(i).getReserve_starttime()).equals("8:00:00")) {
						startTime.clear();
						switch (Integer.valueOf(reserveHour)) {
						case 2:
							startTime.add(t.getHours() + 8 + ":00:00");
							break;
						}
						result.setStatus(6);
						result.setMsg("6小时，8点被占");
						result.setData(startTime);
						//return result;
					} else if (re.get(i).getReserve_hour() == 8
							&& sdf3.format(re.get(i).getReserve_starttime()).equals("8:00:00")) {
						// startTime.clear();
						result.setStatus(7);
						result.setMsg("这一天没有空闲时间");
						//return result;
					}
				}
				return result;
			} else {
				switch (Integer.valueOf(reserveHour)) {
				case 2:
					startTime.add(time);
					startTime.add(t.getHours() + 6 + ":00:00");
					break;
				case 4:
					startTime.add(time);
					startTime.add(t.getHours() + 6 + ":00:00");
					break;
				case 6:
					startTime.add(time);
					break;
				case 8:
					startTime.add(time);
					break;
				}
				result.setStatus(8);
				result.setMsg("一天时间都是空闲的");
				result.setData(startTime);
				return result;
			}
		}
		result.setStatus(9);
		result.setMsg("指定时间有误");
		return result;
	}

	public EbsResult<ReserveFailed> selectReasonInfo(String reserveId) {
		EbsResult<ReserveFailed> result = new EbsResult<ReserveFailed>();
		ReserveFailed reserve = reserveFailedDao.findById(reserveId);
		if(reserve != null) {
			result.setStatus(0);
			result.setMsg("查询成功");
			result.setData(reserve);
			return result;
		}
		result.setStatus(1);
		result.setMsg("没有记录");
		return result;
	}
}
