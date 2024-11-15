package cn.dogalist.weblog.admin.model.vo.file;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "上传文件响应 VO")
public class UploadFileRspVO {
    /**
     * 文件访问地址
     */
    private String url;
}
