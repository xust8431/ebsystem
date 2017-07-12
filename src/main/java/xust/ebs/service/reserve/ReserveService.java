package xust.ebs.service.reserve;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import xust.ebs.entity.Reserve;
import xust.ebs.entity.ReserveFailed;
import xust.ebs.util.EbsResult;

public interface ReserveService {
	public EbsResult<Object> addReserveMsg(String userNick, String item, String hour, String date, 
			String startTime, String reputation) throws Exception;
	
	public EbsResult<List<Reserve>> selectReserveMsg(String userNick);
	
	/**
	 * 根据状态加载与预约信息
	 * @param status
	 * @return
	 */
	public EbsResult<List<Reserve>> loadReserveInfo(String status);
	/**
	 * 更新审核状态
	 * @param reserveId
	 * @param status
	 * @return
	 */
	public EbsResult<Object> examineReserve(String reserveId, String status, String reason);
	/**
	 * 更新审核状态
	 * @param reserveId
	 * @return
	 */
	public EbsResult<Object> completedReserve(String reserveId);
	
	/**
	 * 取消预约
	 * @param reserveId
	 * @param reserveDate
	 * @return
	 * @throws Exception 
	 */
	public EbsResult<Object> cancelReserver(String reserveId, String reserveDate) throws Exception;
	
	/**
	 * 选择空闲时间
	 * @param reserveItem
	 * @param reserveHour
	 * @return
	 * @throws ParseException 
	 */
	public EbsResult<Object> selectReserveDate(String reserveHour, String reserveDate) throws ParseException;
	/**
	 * 查询审核未通过的原因
	 * @param reserveId
	 * @return
	 */
	public EbsResult<ReserveFailed> selectReasonInfo(String reserveId);
}
