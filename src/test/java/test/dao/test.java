package test.dao;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class test {

	public static void main(String[] args) throws Exception {
		String time = "2017-07-08 08:24:00";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(time);
//		System.out.println(date);
//		//System.out.println(date.toLocaleString().split("")[2]);
//		java.sql.Date d = new java.sql.Date(date.getTime());
//		System.out.println(d);
//		String time = "08:24:00";
//		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
//		Time t = new Time(sdf.parse(time).getTime());
//		System.out.println(t);
		SimpleDateFormat sdf2 = new SimpleDateFormat("EEEE");
		String week = sdf2.format(date);
		System.out.println(week);
		
	}

}
