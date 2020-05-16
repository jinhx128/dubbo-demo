package com.jinhaoxun.dubbo.module.file.vo.response;

import com.alibaba.fastjson.JSONObject;
import com.jinhaoxun.dubbo.vo.action.ActionResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 解析文件响应实体类
 */
@Setter
@Getter
@ApiModel("解析文件响应实体类")
public class ResolveExcelActionRes extends ActionResponse {

    @ApiModelProperty(required = true, value = "解析后的数据", example = "解析后的数据")
    List<JSONObject> paramList = new ArrayList<>();

}
