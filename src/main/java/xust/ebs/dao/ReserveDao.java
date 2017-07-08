package xust.ebs.dao;

import java.util.List;

import xust.ebs.entity.Reserve;

public interface ReserveDao {

	public List<Reserve> selectReserveTime();//查询已预约时间
	public void save(Reserve reserve);//添加预约信息
	public Reserve checkConflit();//检查时间冲突
	
}
