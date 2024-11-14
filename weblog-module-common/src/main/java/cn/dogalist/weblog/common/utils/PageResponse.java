package cn.dogalist.weblog.common.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
public class PageResponse<T> extends Response<List<T>> {
    /**
     * 总记录数
     */
    private long total = 0L;
    /**
     * 每页记录数
     */
    private long size = 10L;
    /**
     * 总页数
     */
    private long pages;
    /**
     * 当前页
     */
    private long current;

    /**
     * 成功响应
     * @param page MybatisPlus提供的分页接口
     * @param data
     * @return
     * @param <T>
     */
    public static <T> PageResponse<T> success(IPage page, List<T> data) {
        PageResponse<T> response = new PageResponse<>();
        response.setSuccess(true);
        response.setCurrent(Objects.isNull(page)? 1L : page.getCurrent());
        response.setSize(Objects.isNull(page)? 10L : page.getSize());
        response.setTotal(Objects.isNull(page)? 0L : page.getTotal());
        response.setPages(Objects.isNull(page)? 0L : page.getPages());
        response.setData(data);
        return response;
    }
}
