package com.jinhaoxun.dubbo.module.file.action.request;

import com.jinhaoxun.dubbo.model.action.ActionRequest;
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

    @NotNull(message = "文件名不能为空")
    private String fileName;

    @NotNull(message = "文件类型不能为空")
    private int fileType;

}
