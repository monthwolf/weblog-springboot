package cn.dogalist.weblog.common.model;

import lombok.Data;

@Data
public class BasePageQuery {
    /**
     * 当前页码
     */
    private long current = 1L;
    /**
     * 每页显示条数，默认 10
     */
    private long size = 10L;
}
