package com.jinhaoxun.dubbo.module.file.business;

import com.jinhaoxun.dubbo.module.file.model.request.DownloadFileReq;
import com.jinhaoxun.dubbo.module.file.model.request.UploadFileReq;
import com.jinhaoxun.dubbo.module.file.service.FileService;
import com.jinhaoxun.dubbo.response.ResponseResult;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @Description:
 * @Author: jinhaoxun
 * @Date: 2019/12/10 14:42
 * @Version: 1.0
 */
@Service
public class FileBusiness {

    @Reference
    private FileService fileService;

    /**
     * @author jinhaoxun
     * @description 上传文件
     * @param uploadFileReq 上传文件参数
     * @return ResponseResult 上传结果
     * @throws Exception
     */
    @HystrixCommand(fallbackMethod = "uploadFileFallBack")
    public ResponseResult uploadFile(UploadFileReq uploadFileReq) throws Exception {
        return fileService.uploadFile(uploadFileReq);
    }

    /**
     * @author jinhaoxun
     * @description 上传文件
     * @param uploadFileReq 上传文件参数
     * @return ResponseResult 上传结果
     * @throws Exception
     */
    public ResponseResult uploadFileFallBack(UploadFileReq uploadFileReq) throws Exception {
        return null;
    }

    /**
     * @author jinhaoxun
     * @description 下载文件
     * @param downloadFileReq 下载文件参数
     * @return ResponseResult 下载结果
     * @throws Exception
     */
    @HystrixCommand(fallbackMethod = "downloadFileFallBack")
    public ResponseResult downloadFile(DownloadFileReq downloadFileReq, HttpServletResponse httpServletResponse) throws Exception {
        return fileService.downloadFile(downloadFileReq,httpServletResponse);
    }

    /**
     * @author jinhaoxun
     * @description 下载文件
     * @param downloadFileReq 下载文件参数
     * @return ResponseResult 下载结果
     * @throws Exception
     */
    public ResponseResult downloadFileFallBack(DownloadFileReq downloadFileReq, HttpServletResponse httpServletResponse) throws Exception {
        return null;
    }

    /**
     * @author jinhaoxun
     * @description 创建Excel
     * @return ResponseResult 创建结果
     * @throws Exception
     */
    @HystrixCommand(fallbackMethod = "createExcelFallBack")
    public ResponseResult createExcel() throws Exception {
        return fileService.createExcel();
    }

    /**
     * @author jinhaoxun
     * @description 创建Excel
     * @return ResponseResult 创建结果
     * @throws Exception
     */
    public ResponseResult createExcelFallBack() throws Exception {
        return null;
    }


    /**
     * @author jinhaoxun
     * @description 解析Excel
     * @param multipartFile 要解析的文件
     * @return ResponseResult 解析后的数据
     * @throws Exception
     */
    @HystrixCommand(fallbackMethod = "resolveExcelFallBack")
    public ResponseResult resolveExcel(MultipartFile multipartFile) throws Exception {
        return fileService.resolveExcel(multipartFile);
    }

    /**
     * @author jinhaoxun
     * @description 解析Excel
     * @param multipartFile 要解析的文件
     * @return ResponseResult 解析后的数据
     * @throws Exception
     */
    public ResponseResult resolveExcelFallBack(MultipartFile multipartFile) throws Exception {
        return null;
    }
}
