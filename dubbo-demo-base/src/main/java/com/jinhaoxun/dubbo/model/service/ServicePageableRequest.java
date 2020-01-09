package com.jinhaoxun.dubbo.model.service;

import lombok.Getter;
import lombok.Setter;

/**
 * @description: 分页请求模型
 * @program: session1
 * @author: jinhaaoxun
 * @create: 2019-03-12 15:17
 **/
@Setter
@Getter
public class ServicePageableRequest extends ServiceRequest {

    /** 当前页号 */
    private short page;
    /** 每页显示记录数 */
    private short size;

}
