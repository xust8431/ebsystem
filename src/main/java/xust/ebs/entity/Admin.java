package xust.ebs.entity;

import java.io.Serializable;

public class Admin implements Serializable{

	private String admin_name;
	private String admin_password;
	private String admin_token;
	public String getAdmin_name() {
		return admin_name;
	}
	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}
	public String getAdmin_password() {
		return admin_password;
	}
	public void setAdmin_password(String admin_password) {
		this.admin_password = admin_password;
	}
	public String getAdmin_token() {
		return admin_token;
	}
	public void setAdmin_token(String admin_token) {
		this.admin_token = admin_token;
	}
	
	
}
