package com.jinhaoxun.dubbo.module.file.controller;

import com.jinhaoxun.dubbo.model.action.ActionResponse;
import com.jinhaoxun.dubbo.model.http.HttpRequest;
import com.jinhaoxun.dubbo.model.http.HttpResponse;
import com.jinhaoxun.dubbo.module.file.action.request.DownloadFileActionReq;
import com.jinhaoxun.dubbo.module.file.action.request.UploadFileActionReq;
import com.jinhaoxun.dubbo.module.file.action.response.ResolveExcelActionRes;
import com.jinhaoxun.dubbo.module.file.business.FileBusiness;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2019-08-09
 * @description 上传下载文件前端控制器
 */
@RequestMapping("/file")
@RestController
@Api("上传下载文件接口")
public class FileController {

    @Resource
    private FileBusiness fileBusiness;

    /**
     * @author jinhaoxun
     * @description 上传文件
     * @param uploadFileActionReqHttpRequest 上传文件参数
     * @return HttpResponse<ActionResponse> 上传结果
     * @throws Exception
     */
    @PostMapping(value = "/uploadfile", produces = "application/json; charset=UTF-8")
    @ApiOperation("上传文件")
    public HttpResponse<ActionResponse> uploadFile(@Validated HttpRequest<UploadFileActionReq> uploadFileActionReqHttpRequest) throws Exception {
        fileBusiness.uploadFile(uploadFileActionReqHttpRequest.getData());
        return HttpResponse.buildSuccess();
    }

    /**
     * @author jinhaoxun
     * @description 下载文件
     * @param downloadFileActionReqHttpRequest 下载文件参数
     * @param httpServletResponse
     * @return HttpResponse<ActionResponse> 下载结果
     * @throws Exception
     */
    @GetMapping(value = "/downloadfile", produces = "application/json; charset=UTF-8")
    @ApiOperation("下载文件")
    public HttpResponse<ActionResponse> downloadFile(@Validated HttpRequest<DownloadFileActionReq> downloadFileActionReqHttpRequest, HttpServletResponse httpServletResponse) throws Exception {
        fileBusiness.downloadFile(downloadFileActionReqHttpRequest.getData(), httpServletResponse);
        return HttpResponse.buildSuccess();
    }

    /**
     * @author jinhaoxun
     * @description 创建Excel
     * @return HttpResponse<ActionResponse> 创建结果
     * @throws Exception
     */
    @GetMapping(value = "/createexcel", produces = "application/json; charset=UTF-8")
    @ApiOperation("创建Excel")
    public HttpResponse<ActionResponse> createExcel() throws Exception {
        fileBusiness.createExcel();
        return HttpResponse.buildSuccess();
    }

    /**
     * @author jinhaoxun
     * @description 解析Excel
     * @param multipartFile 要解析的文件
     * @return ResolveExcelActionRes 解析后的数据
     * @throws Exception
     */
    @PostMapping(value = "/resolveexcel", produces = "application/json; charset=UTF-8")
    @ApiOperation("解析Excel")
    public HttpResponse<ResolveExcelActionRes> resolveExcel(MultipartFile multipartFile) throws Exception {
        ResolveExcelActionRes resolveExcelActionRes = fileBusiness.resolveExcel(multipartFile);
        return HttpResponse.buildSuccess(resolveExcelActionRes);
    }
}
