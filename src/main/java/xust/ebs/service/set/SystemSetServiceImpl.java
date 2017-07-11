package xust.ebs.service.set;

import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import xust.ebs.util.EbsResult;
import xust.ebs.util.EbsSplitStr;

@Service("systemSetService")
public class SystemSetServiceImpl implements SystemSetService {

	@Resource
	private Properties timeset;
	
	public EbsResult<Object> addSet(String timeStr, String timeScope, String selectMode) {
		EbsResult<Object> result = new EbsResult<Object>();
		boolean flag = false;
		if(timeStr != null) {
			String banTimeStr = (String) timeset.get("banTimeStr");
//			System.out.println(banTimeStr);
			banTimeStr += timeStr;
			banTimeStr += ",";
//			System.out.println(banTimeStr);
			timeset.setProperty("banTimeStr", banTimeStr);
			flag = true;
		}
		if(timeScope != null) {
			timeset.setProperty("timeScope", timeScope);
			flag = true;
		}
		if(selectMode != null) {
			timeset.setProperty("selectMode", selectMode);
			flag = true;
		}
		if(flag == true) {
			result.setStatus(0);
			result.setMsg("修改成功");
		} else {
			result.setStatus(1);
			result.setMsg("无任何选项修改");
		}
		
		return result;
	}

	public EbsResult<Properties> loadSet() {
		EbsResult<Properties> result = new EbsResult<Properties>();
		result.setStatus(0);
		result.setMsg("加载成功");
		result.setData(timeset);
		return result;
	}

	public EbsResult<Object> deleteSet(String timeStr) {
		EbsResult<Object> result = new EbsResult<Object>();
		String banTimeStr = (String) timeset.get("banTimeStr");
		List<String> banTimeList = EbsSplitStr.splitStr(banTimeStr, ",");
		//循环找出需要删除的项，删除该项
		for(int i = 0; i < banTimeList.size(); i++) {
			if(banTimeList.get(i).equals(timeStr)) {
				banTimeList.remove(i);
				break;
			}
		}
		banTimeStr = "";
		//将删除后剩余的项重新写入
		for(int i = 0; i < banTimeList.size(); i++) {
			banTimeStr += banTimeList.get(i);
			banTimeStr += ",";
		}
		timeset.setProperty("banTimeStr", banTimeStr);
		return result;
	}
	
}











