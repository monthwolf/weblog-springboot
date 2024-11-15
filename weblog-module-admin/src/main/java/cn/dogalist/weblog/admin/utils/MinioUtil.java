package cn.dogalist.weblog.admin.utils;

import cn.dogalist.weblog.admin.config.MinioProperties;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Component
@Slf4j
public class MinioUtil {
    @Autowired
    private MinioProperties minioProperties;
    @Autowired
    private MinioClient minioClient;

    public String uploadFile(MultipartFile file) throws Exception {
        if (file == null || file.getSize() == 0) {
            log.error("==> 上传文件异常：文件为空");
            throw new RuntimeException("文件大小不能为空");
        }

        // 文件原始名称
        String originalFilename = file.getOriginalFilename();
        // 文件ContentType
        String contentType = file.getContentType();
        // 生成存储对象名称
        String key = UUID.randomUUID().toString().replace("-", "");
        // 获取文件后缀
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        // 拼接对象名称
        String objectName = String.format("%s%s", key, suffix);
        log.info("==> 开始上传文件至Minio：{}", objectName);

        minioClient.putObject(PutObjectArgs.builder()
                .bucket(minioProperties.getBucketName())
                .object(objectName)
                .stream(file.getInputStream(), file.getSize(), -1)
                .contentType(contentType)
                .build());

        String url = String.format("%s/%s/%s", minioProperties.getEndpoint(), minioProperties.getBucketName(), objectName);
        log.info("==> 上传文件至Minio完成，访问地址为：{}", url);
        return url;
    }
}
