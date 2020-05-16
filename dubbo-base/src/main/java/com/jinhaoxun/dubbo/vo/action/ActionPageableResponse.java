package com.jinhaoxun.dubbo.vo.action;

import lombok.Getter;
import lombok.Setter;

/**
 * @description: 分页查询响应模型
 * @program: session1
 * @author: jinhaoxun
 * @create: 2019-03-11 18:36
 **/
@Setter
@Getter
public class ActionPageableResponse extends ActionResponse {

    /** 总记录数 */
    private int totals;

}
