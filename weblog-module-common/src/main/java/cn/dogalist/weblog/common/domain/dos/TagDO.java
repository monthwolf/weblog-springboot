package cn.dogalist.weblog.common.domain.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author: dogalist
 * @Description: 标签
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_tag")

public class TagDO {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;
    private String color;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Boolean status;
    private Boolean isDeleted;
}
