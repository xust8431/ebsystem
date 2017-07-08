package xust.ebs.util;

import java.security.MessageDigest;
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
	
	public static void main(String[] args){
//		System.out.println(md5("123456"));
		//System.out.println(md5("123"));
		System.out.println(createId());
	}
	
}
