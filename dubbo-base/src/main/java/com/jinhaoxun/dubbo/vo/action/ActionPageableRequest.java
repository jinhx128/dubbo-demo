package com.jinhaoxun.dubbo.vo.action;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @description: 分页请求模型
 * @program: session1
 * @author: jinhaaoxun
 * @create: 2019-03-12 15:17
 **/
@Setter
@Getter
public class ActionPageableRequest extends ActionRequest {

    /** 当前页号 */
    @Min(value = 1, message = "当前页号不能低于1")
    private int page;
    /** 每页显示记录数 */
    @Min(value = 1, message = "每页显示记录数不能低于1")
    private int size;

}
