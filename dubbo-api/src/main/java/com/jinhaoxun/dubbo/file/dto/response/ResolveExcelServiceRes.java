package com.jinhaoxun.dubbo.file.dto.response;

import com.alibaba.fastjson.JSONObject;
import com.jinhaoxun.dubbo.vo.service.ServiceResponse;
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
public class ResolveExcelServiceRes extends ServiceResponse {

    List<JSONObject> paramList = new ArrayList<>();

}
