package test.dao;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xust.ebs.util.EbsResult;
import xust.ebs.util.EbsUtil;

public class test {

	public static List<Date> dateToWeek(Date mdate) {  
	    Date fdate;  
	    List list = new ArrayList(); 
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    Long fTime = mdate.getTime();  
	    for (int a = 1; a <= 7; a++) {  
	        fdate = new Date();  
	        fdate.setTime(fTime + (a * 24 * 3600000));  
	        String time = sdf.format(fdate);
	        list.add(a-1, time);  
	    }  
	    return list;  
}
	public static void main(String[] args) throws Exception {
//		String time = "2017-07-08 08:24:00";
//		String time2 = "2017-07-06";
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		Date date = sdf.parse(time);
//		System.out.println(date);
//		//System.out.println(date.toLocaleString().split("")[2]);
//		java.sql.Date d = new java.sql.Date(date.getTime());
//		System.out.println(d);
//		String time = "08:24:00";
//		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
//		Time t = new Time(sdf.parse(time).getTime());
//		System.out.println(t);
//		String t2 = sdf.format(t);
//		SimpleDateFormat sdf2 = new SimpleDateFormat("EEEE");
//		String week = sdf2.format(date);
//		System.out.println(week);
//		Date date2 = sdf.parse(time2);
//		java.sql.Date d2 = new java.sql.Date(date2.getTime());
//		int day = EbsUtil.getIntervalDays(d, d2);
//		System.out.println(day);

		
		 // 定义输出日期格式  
//	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
//	      
//	    Date currentDate = new Date();  
//	      
//	    // 比如今天是2012-12-25  
//	    List days = dateToWeek(currentDate);   
//		
//	    for(int i=0;i<7;i++){
//	    	System.out.println(days.get(i));
//	    }
		
		
//		System.out.println(t.getHours()+6+":00:00");
//		String start = time;
//		System.out.println(start);
	
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse("2017-07-15");
		SimpleDateFormat sdf2 = new SimpleDateFormat("EEEE");
		String week = sdf2.format(date);
		System.out.println(week);
	}
}

	