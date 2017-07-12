package xust.ebs.dao;

import xust.ebs.entity.ReserveFailed;

public interface ReserveFailedDao {

	/**
	 * 在预约失败表中查询预约失败信息
	 * @param reserveId
	 * @return
	 */
	public ReserveFailed findById(String reserveId);
	/**
	 * 添加预约失败信息
	 * @param reserve
	 */
	public void save(ReserveFailed reserve);
}
