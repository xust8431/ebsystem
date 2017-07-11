package xust.ebs.dao;

import java.util.List;
import java.util.Map;

import xust.ebs.entity.Reserve;

public interface ReserveDao {

	public List<Reserve> selectReserveTime(String reserveDate);//查询已预约时间
	public void save(Reserve reserve);//添加预约信息
	public List<Reserve> findByNick(String userNick);//查询预约信息
	
	/**
	 * 根据状态查询预约信息
	 * @param map
	 * @return
	 */
	public List<Reserve> loadReserve(Map<String, String> map);
	/**
	 * 更新审核状态
	 * @param map
	 */
	public void updateExamine(Map<String, String> map);
	/**
	 * 更新完成状态
	 * @param reserveId
	 */
	public void updateCompleted(Map<String, String> map);
	
	/**
	 * 通过reserveId查询该条记录
	 * @param reserveId
	 * @return
	 */
	public Reserve findByReserveId(String reserveId);
}
