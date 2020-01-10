package com.jinhaoxun.dubbo.module.file.model.request;

import com.jinhaoxun.dubbo.model.service.ServiceRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 下载文件请求实体类
 */
@Setter
@Getter
public class DownloadFileServiceReq extends ServiceRequest {

    private String fileName;

    private int fileType;

}
