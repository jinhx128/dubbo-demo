package com.jinhaoxun.dubbo.module.file.business;

import com.jinhaoxun.dubbo.module.file.action.request.UploadFileActionReq;
import com.jinhaoxun.dubbo.module.file.action.response.ResolveExcelActionRes;
import com.jinhaoxun.dubbo.module.file.model.request.DownloadFileReq;
import com.jinhaoxun.dubbo.module.file.model.request.UploadFileServiceReq;
import com.jinhaoxun.dubbo.module.file.model.response.ResolveExcelServiceRes;
import com.jinhaoxun.dubbo.module.file.service.FileService;
import com.jinhaoxun.dubbo.response.ResponseResult;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
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
     * @param uploadFileActionReq 上传文件参数
     * @return
     * @throws Exception
     */
    @HystrixCommand(fallbackMethod = "uploadFileFallBack")
    public void uploadFile(UploadFileActionReq uploadFileActionReq) throws Exception {
        UploadFileServiceReq uploadFileServiceReq = new UploadFileServiceReq();
        BeanUtils.copyProperties(uploadFileActionReq, uploadFileServiceReq);
        fileService.uploadFile(uploadFileServiceReq);
    }

    /**
     * @author jinhaoxun
     * @description 上传文件
     * @param uploadFileReq 上传文件参数
     * @param exception Hystrix抛出的异常
     * @return
     * @throws Exception
     */
    public void uploadFileFallBack(UploadFileServiceReq uploadFileReq, Throwable exception) throws Exception {
        throw (Exception) exception;
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
     * @param exception Hystrix抛出的异常
     * @throws Exception
     */
    public ResponseResult downloadFileFallBack(DownloadFileReq downloadFileReq, HttpServletResponse httpServletResponse, Throwable exception) throws Exception {
        throw (Exception) exception;
    }

    /**
     * @author jinhaoxun
     * @description 创建Excel
     * @return
     * @throws Exception
     */
    @HystrixCommand(fallbackMethod = "createExcelFallBack")
    public void createExcel() throws Exception {
        fileService.createExcel();
    }

    /**
     * @author jinhaoxun
     * @description 创建Excel
     * @param exception Hystrix抛出的异常
     * @return ResponseResult 创建结果
     * @throws Exception
     */
    public ResponseResult createExcelFallBack(Throwable exception) throws Exception {
        throw (Exception) exception;
    }


    /**
     * @author jinhaoxun
     * @description 解析Excel
     * @param multipartFile 要解析的文件
     * @return ResponseResult 解析后的数据
     * @throws Exception
     */
    @HystrixCommand(fallbackMethod = "resolveExcelFallBack")
    public ResolveExcelActionRes resolveExcel(MultipartFile multipartFile) throws Exception {
        ResolveExcelActionRes resolveExcelActionRes = new ResolveExcelActionRes();
        ResolveExcelServiceRes resolveExcelServiceRes = fileService.resolveExcel(multipartFile);
        BeanUtils.copyProperties(resolveExcelServiceRes, resolveExcelActionRes);
        return resolveExcelActionRes;
    }

    /**
     * @author jinhaoxun
     * @description 解析Excel
     * @param multipartFile 要解析的文件
     * @param exception Hystrix抛出的异常
     * @return ResponseResult 解析后的数据
     * @throws Exception
     */
    public ResponseResult resolveExcelFallBack(MultipartFile multipartFile, Throwable exception) throws Exception {
        throw (Exception) exception;
    }
}
