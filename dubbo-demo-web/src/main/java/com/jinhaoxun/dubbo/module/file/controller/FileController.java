package com.jinhaoxun.dubbo.module.file.controller;

import com.jinhaoxun.dubbo.module.file.business.FileBusiness;
import com.jinhaoxun.dubbo.module.file.model.request.DownloadFileReq;
import com.jinhaoxun.dubbo.module.file.model.request.UploadFileReq;
import com.jinhaoxun.dubbo.response.ResponseResult;
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
     * @param uploadFileReq 上传文件参数
     * @return ResponseResult 上传结果
     * @throws Exception
     */
    @PostMapping(value = "/uploadfile", produces = "application/json; charset=UTF-8")
    @ApiOperation("上传文件")
    public ResponseResult uploadFile(@Validated UploadFileReq uploadFileReq) throws Exception {
        return fileBusiness.uploadFile(uploadFileReq);
    }

    /**
     * @author jinhaoxun
     * @description 下载文件
     * @param downloadFileReq 下载文件参数
     * @return ResponseResult 下载结果
     * @throws Exception
     */
    @GetMapping(value = "/downloadfile", produces = "application/json; charset=UTF-8")
    @ApiOperation("下载文件")
    public ResponseResult downloadFile(@Validated DownloadFileReq downloadFileReq, HttpServletResponse httpServletResponse) throws Exception {
        return fileBusiness.downloadFile(downloadFileReq,httpServletResponse);
    }

    /**
     * @author jinhaoxun
     * @description 创建Excel
     * @return ResponseResult 创建结果
     * @throws Exception
     */
    @GetMapping(value = "/createexcel", produces = "application/json; charset=UTF-8")
    @ApiOperation("创建Excel")
    public ResponseResult createExcel() throws Exception {
        return fileBusiness.createExcel();
    }

    /**
     * @author jinhaoxun
     * @description 解析Excel
     * @param multipartFile 要解析的文件
     * @return ResponseResult 解析后的数据
     * @throws Exception
     */
    @PostMapping(value = "/resolveexcel", produces = "application/json; charset=UTF-8")
    @ApiOperation("解析Excel")
    public ResponseResult resolveExcel(MultipartFile multipartFile) throws Exception {
        return fileBusiness.resolveExcel(multipartFile);
    }
}
