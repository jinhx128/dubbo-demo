package com.jinhaoxun.dubbo.model.web;

import com.jinhaoxun.dubbo.model.base.Model;

import javax.validation.Valid;
import java.util.Date;

/**
* @Description: Http的请求的大对象
* @author humeng  
* @date 2018年12月29日 下午8:16:52
 */
public class HttpRequest<T> extends Model {
	/**
	 * 客户端的版本
	 */
	private String version;
	/**
	 * 客户端的渠道
	 */
	private String channel;
	/**
	 * 客户端发起请求的时间
	 */
	private Date time;
	/**
	 * 请求的数据对象
	 */
	@Valid
	private T data;
	/**
	 * 请求的密文，如果该接口需要加密上送，
	 * 则将sdt的密文反序化到data，
	 * sdt和action至少有一个为空
	 */
	private String sdt;
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
    

}
