package xust.ebs.service.reserve;

import java.util.List;

import xust.ebs.entity.Reserve;
import xust.ebs.util.EbsResult;

public interface ReserveService {
	public EbsResult<Object> addReserveMsg(String userNick, String item, String hour, String date, 
			String startTime, String endTime, String reputation) throws Exception;
	
	//public EbsResult<Object> checkConflit(String date, String startTime, String endTime) throws Exception;
	
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
	public EbsResult<Object> examineReserve(String reserveId, String status);
	/**
	 * 更新审核状态
	 * @param reserveId
	 * @return
	 */
	public EbsResult<Object> completedReserve(String reserveId);
}
