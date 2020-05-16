package com.jinhaoxun.dubbo.user.dto.request;

import com.jinhaoxun.dubbo.vo.service.ServiceRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 用户id请求实体类
 */
@Setter
@Getter
public class GetUserServiceReq extends ServiceRequest {

    /**
     * 用户ID(主键)
     */
    private Long userId;

}
