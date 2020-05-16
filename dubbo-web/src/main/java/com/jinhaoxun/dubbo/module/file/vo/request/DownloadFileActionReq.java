package com.jinhaoxun.dubbo.module.file.vo.request;

import com.jinhaoxun.dubbo.vo.action.ActionRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 下载文件请求实体类
 */
@Setter
@Getter
public class DownloadFileActionReq extends ActionRequest {

    @ApiModelProperty(required = true, value = "文件名", example = "我是文件名")
    @NotNull(message = "文件名不能为空")
    private String fileName;

    @ApiModelProperty(required = true, value = "文件类型", example = "1")
    @NotNull(message = "文件类型不能为空")
    private int fileType;

}
