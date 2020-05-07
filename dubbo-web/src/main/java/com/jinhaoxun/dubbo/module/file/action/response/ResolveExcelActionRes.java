package com.jinhaoxun.dubbo.module.file.action.response;

import com.alibaba.fastjson.JSONObject;
import com.jinhaoxun.dubbo.model.action.ActionResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 获取文章响应实体类
 */
@Setter
@Getter
public class ResolveExcelActionRes extends ActionResponse {

    List<JSONObject> paramList = new ArrayList<>();

}
