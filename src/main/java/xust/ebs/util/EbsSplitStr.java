package xust.ebs.util;

import java.util.ArrayList;
import java.util.List;

public class EbsSplitStr {

	public static List<String> splitStr(String srcStr, String c) {
		List<String> list = new ArrayList<String>();  
		for(String t : srcStr.split(c)){  
		        list.add(t);  
		}
		return list;
	}
}
