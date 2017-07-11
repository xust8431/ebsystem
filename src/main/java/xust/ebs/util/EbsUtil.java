package xust.ebs.util;

import java.security.MessageDigest;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;



public class EbsUtil {
	/**
	 * 利用UUID算法生成一个主键值
	 * @return 主键值
	 */
	public static String createId(){
		UUID uuid = UUID.randomUUID();
		String id = uuid.toString();
		return id.replace("-", "");
	}

	/**
	 * 密码加密处理（MD5）
	 * @param src 原密码
	 * @return 加密后的内容
	 */
	public static String md5(String src){
		try{//采用MD5处理
			MessageDigest md = 
				MessageDigest.getInstance("MD5");
			byte[] output = md.digest(
				src.getBytes());//加密处理
			//将加密结果output利用Base64转成字符串输出
			String ret = 
			 Base64.encodeBase64String(output);
			return ret;
		}catch(Exception e){
			throw new EbsException(
				"密码加密失败", e);
		}
	}
	
	/**
	 * 计算两个Date类型的时间相差几天
	 * @param fDate
	 * @param oDate
	 * @return
	 */
	public static int getIntervalDays(Date fDate, Date oDate) {

	       if (null == fDate || null == oDate) {

	           return -1;

	       }

	       long intervalMilli = oDate.getTime() - fDate.getTime();

	       return (int) (intervalMilli / (24 * 60 * 60 * 1000));

	    }
	
	/**
	 * 获取未来一周的日期
	 * @param mdate
	 * @return
	 */
	public static List<Date> dateToWeek(java.util.Date mdate) {  
	    java.util.Date fdate = new java.util.Date();  ;  
	    List list = new ArrayList(); 
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    Long fTime = mdate.getTime();  
	    for (int a = 1; a <= 7; a++) {  
	        fdate.setTime(fTime + (a * 24 * 3600000));  
	        String time = sdf.format(fdate);
	        list.add(a-1, time);  
	    }  
	    return list;  
}
	public static void main(String[] args){
//		System.out.println(md5("123456"));
		//System.out.println(md5("123"));
		System.out.println(createId());
	}
	
}
