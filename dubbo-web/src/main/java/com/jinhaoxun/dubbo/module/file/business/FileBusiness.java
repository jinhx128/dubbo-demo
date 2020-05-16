package com.jinhaoxun.dubbo.module.file.business;

import com.jinhaoxun.dubbo.module.file.vo.response.ResolveExcelActionRes;
import com.jinhaoxun.dubbo.module.file.vo.request.DownloadFileActionReq;
import com.jinhaoxun.dubbo.module.file.vo.request.UploadFileActionReq;
import com.jinhaoxun.dubbo.file.dto.request.DownloadFileServiceReq;
import com.jinhaoxun.dubbo.file.dto.request.UploadFileServiceReq;
import com.jinhaoxun.dubbo.file.dto.response.ResolveExcelServiceRes;
import com.jinhaoxun.dubbo.file.service.FileService;
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
    public void uploadFile(UploadFileActionReq uploadFileActionReq) throws Exception {
        UploadFileServiceReq uploadFileServiceReq = new UploadFileServiceReq();
        BeanUtils.copyProperties(uploadFileActionReq, uploadFileServiceReq);
        fileService.uploadFile(uploadFileServiceReq);
    }

    /**
     * @author jinhaoxun
     * @description 下载文件
     * @param downloadFileActionReq 下载文件参数
     * @param httpServletResponse
     * @return
     * @throws Exception
     */
    public void downloadFile(DownloadFileActionReq downloadFileActionReq, HttpServletResponse httpServletResponse) throws Exception {
        DownloadFileServiceReq downloadFileServiceReq = new DownloadFileServiceReq();
        BeanUtils.copyProperties(downloadFileActionReq, downloadFileServiceReq);
        fileService.downloadFile(downloadFileServiceReq, httpServletResponse);
    }

    /**
     * @author jinhaoxun
     * @description 创建Excel
     * @return
     * @throws Exception
     */
    public void createExcel() throws Exception {
        fileService.createExcel();
    }

    /**
     * @author jinhaoxun
     * @description 解析Excel
     * @param multipartFile 要解析的文件
     * @return ResponseResult 解析后的数据
     * @throws Exception
     */
    public ResolveExcelActionRes resolveExcel(MultipartFile multipartFile) throws Exception {
        ResolveExcelActionRes resolveExcelActionRes = new ResolveExcelActionRes();
        ResolveExcelServiceRes resolveExcelServiceRes = fileService.resolveExcel(multipartFile);
        BeanUtils.copyProperties(resolveExcelServiceRes, resolveExcelActionRes);
        return resolveExcelActionRes;
    }
}
