package com.jinhaoxun.dubbo.module.file.model.request;

import com.jinhaoxun.dubbo.model.service.ServiceRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 上传文件请求实体类
 */
@Setter
@Getter
public class UploadFileServiceReq extends ServiceRequest {

    private MultipartFile[] multipartFile;

}
