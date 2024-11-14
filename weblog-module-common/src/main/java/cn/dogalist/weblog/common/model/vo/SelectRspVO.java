package cn.dogalist.weblog.common.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 下拉框返回对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SelectRspVO {
    private String label;
    private Object id;
}
