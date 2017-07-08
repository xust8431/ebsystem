package xust.ebs.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;


public class Reserve implements Serializable{
	
	private String reserve_id;
	private String user_nick;
	private String reserve_item;
	private int reserve_hour;
	private Date reserve_date;
	private Time reserve_starttime;
	private Time reserve_endtime;
	private String reserve_examine_status;
	private String reserve_complete_status;
	private Timestamp reserve_time;
	public String getReserve_id() {
		return reserve_id;
	}
	public void setReserve_id(String reserve_id) {
		this.reserve_id = reserve_id;
	}
	public String getUser_nick() {
		return user_nick;
	}
	public void setUser_nick(String user_nick) {
		this.user_nick = user_nick;
	}
	public String getReserve_item() {
		return reserve_item;
	}
	public void setReserve_item(String reserve_item) {
		this.reserve_item = reserve_item;
	}
	public int getReserve_hour() {
		return reserve_hour;
	}
	public void setReserve_hour(int reserve_hour) {
		this.reserve_hour = reserve_hour;
	}
	public Date getReserve_date() {
		return reserve_date;
	}
	public void setReserve_date(Date reserve_date) {
		this.reserve_date = reserve_date;
	}
	public Time getReserve_starttime() {
		return reserve_starttime;
	}
	public void setReserve_starttime(Time reserve_starttime) {
		this.reserve_starttime = reserve_starttime;
	}
	public Time getReserve_endtime() {
		return reserve_endtime;
	}
	public void setReserve_endtime(Time reserve_endtime) {
		this.reserve_endtime = reserve_endtime;
	}
	public String getReserve_examine_status() {
		return reserve_examine_status;
	}
	public void setReserve_examine_status(String reserve_examine_status) {
		this.reserve_examine_status = reserve_examine_status;
	}
	public String getReserve_complete_status() {
		return reserve_complete_status;
	}
	public void setReserve_complete_status(String reserve_complete_status) {
		this.reserve_complete_status = reserve_complete_status;
	}
	public Timestamp getReserve_time() {
		return reserve_time;
	}
	public void setReserve_time(Timestamp reserve_time) {
		this.reserve_time = reserve_time;
	}
	
	

}
