package cn.dogalist.weblog.admin.service.impl;

import cn.dogalist.weblog.admin.model.vo.file.UploadFileRspVO;
import cn.dogalist.weblog.admin.service.AdminFileService;
import cn.dogalist.weblog.admin.utils.MinioUtil;
import cn.dogalist.weblog.common.enums.ResponseCodeEnum;
import cn.dogalist.weblog.common.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class AdminFileServiceImpl implements AdminFileService {
@Autowired
private MinioUtil minioUtil;

    @Override
    public Response uploadFile(MultipartFile file) {
        try {
            String url = minioUtil.uploadFile(file);

            return Response.success(UploadFileRspVO.builder().url(url).build());
        } catch (Exception e) {
            log.error("==> 上传文件异常：", e);
            return Response.fail(ResponseCodeEnum.FILE_UPLOAD_FAILED);
        }
    }
}
