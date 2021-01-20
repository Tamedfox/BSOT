package com.cf.bsot.common.api;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author cf
 * @version 1.0
 * @date 2020/9/19 21:58
 */
public class PageResult<T> {

    private Long total;
    private List<T> rows;

    protected PageResult() {
    }

    protected PageResult(Long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    /**
     * 共用转换分页
     * @param list
     * @param <T>
     * @return
     */
    public static <T> PageResult<T> transPage(List<T> list){
        PageInfo<T> pageInfo = new PageInfo<>(list);
        return new PageResult(pageInfo.getTotal(),pageInfo.getList());
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

}
