package xust.ebs.service.reserve;

import xust.ebs.util.EbsResult;

public interface ReserveService {
	public EbsResult<Object> addReserveMsg(String userNick, String item, String hour, String date, 
			String startTime, String endTime, String reputation) throws Exception;
	
	//public EbsResult<Object> checkConflit(String date, String startTime, String endTime) throws Exception;
}
