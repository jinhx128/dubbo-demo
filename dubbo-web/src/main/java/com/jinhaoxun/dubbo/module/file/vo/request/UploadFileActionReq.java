package com.jinhaoxun.dubbo.module.file.vo.request;

import com.jinhaoxun.dubbo.vo.action.ActionRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 上传文件请求实体类
 */
@Setter
@Getter
public class UploadFileActionReq extends ActionRequest {

    @ApiModelProperty(required = true, value = "上传文件", example = "上传文件")
    @NotNull(message = "上传文件不能为空")
    private MultipartFile[] multipartFile;

}
