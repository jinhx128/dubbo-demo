package com.jinhaoxun.dubbo.file.service;

import com.jinhaoxun.dubbo.file.dto.request.DownloadFileServiceReq;
import com.jinhaoxun.dubbo.file.dto.request.UploadFileServiceReq;
import com.jinhaoxun.dubbo.file.dto.response.ResolveExcelServiceRes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2019-08-09
 * @description 上传下载文件服务接口
 */
public interface FileService {

    /**
     * @author jinhaoxun
     * @description 上传文件
     * @param uploadFileServiceReq 上传文件参数
     * @return
     * @throws Exception
     */
    void uploadFile(UploadFileServiceReq uploadFileServiceReq) throws Exception;

    /**
     * @author jinhaoxun
     * @description 下载文件
     * @param downloadFileServiceReq 下载文件参数
     * @param httpServletResponse
     * @return
     * @throws Exception
     */
    void downloadFile(DownloadFileServiceReq downloadFileServiceReq, HttpServletResponse httpServletResponse) throws Exception;

    /**
     * @author jinhaoxun
     * @description 解析Excel
     * @param multipartFile 要解析的文件
     * @return ResolveExcelServiceRes 解析后的数据
     * @throws Exception
     */
    ResolveExcelServiceRes resolveExcel(MultipartFile multipartFile) throws Exception;

    /**
     * @author jinhaoxun
     * @description 导出Excel
     * @return
     * @throws Exception
     */
    void createExcel() throws Exception;

}
