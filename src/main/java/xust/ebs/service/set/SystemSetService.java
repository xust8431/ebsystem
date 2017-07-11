package xust.ebs.service.set;

import java.util.Properties;

import xust.ebs.util.EbsResult;

public interface SystemSetService {

	/**
	 * 添加设置
	 * @param banTimeStr
	 * @param timeScope
	 * @param selectMode
	 * @return
	 */
	public EbsResult<Object> addSet(String timeStr, String timeScope, String selectMode);
	/**
	 * 加载设置
	 * @return
	 */
	public EbsResult<Properties> loadSet();
	public EbsResult<Object> deleteSet(String timeStr);
}
