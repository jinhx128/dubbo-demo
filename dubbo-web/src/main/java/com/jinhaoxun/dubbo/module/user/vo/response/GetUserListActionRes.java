package com.jinhaoxun.dubbo.module.user.vo.response;

import com.jinhaoxun.dubbo.vo.action.ActionPageableResponse;
import com.jinhaoxun.dubbo.pojo.user.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 获取用户列表响应实体类
 */
@Setter
@Getter
@ApiModel("获取用户列表响应实体类")
public class GetUserListActionRes extends ActionPageableResponse {

    @ApiModelProperty(required = true, value = "用户列表", example = "用户列表")
    private List<User> userList;

}
