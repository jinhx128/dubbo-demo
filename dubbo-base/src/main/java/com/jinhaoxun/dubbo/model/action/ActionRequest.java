package com.jinhaoxun.dubbo.model.action;


import com.jinhaoxun.dubbo.model.base.Model;
import lombok.Getter;
import lombok.Setter;

/**
 * 
* @Description: http请求的数据对象呢
* @author humeng  
* @date 2018年12月29日 下午8:08:54
 */
@Setter
@Getter
public class ActionRequest extends Model {
	/**
	 * 操作用户编号
	 */
	private String operationUserId;
}
