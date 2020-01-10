package com.jinhaoxun.dubbo.module.user.action.response;

import com.jinhaoxun.dubbo.model.action.ActionPageableResponse;
import com.jinhaoxun.dubbo.pojo.user.User;
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
public class GetUserListActionRes extends ActionPageableResponse {

    private List<User> articleList;

}
