package com.huaxia.sso.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 分页查询对象。
 *
 * @author K9999
 */
public class Pagination<T> implements Serializable {

    /**
     * 当前页
     */
    private Integer currPage;
    /**
     * 每页大小
     */
    private Integer pageSize;

    /**
     * 总记录数。
     */
    private final int total;

    /**
     * 记录列表。
     */
    private final List<T> rows;

    /**
     * 构造分页包装对象。
     *
     * @param total 总记录数。
     * @param rows  记录列表，不可为null，无记录时应该是空集。
     */
    public Pagination(int total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public Pagination(int currPage, int pageSize, int total, List<T> rows) {
        this.currPage = currPage;
        this.pageSize = pageSize;
        this.total = total;
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public List<?> getRows() {
        return rows;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setCurrPage(Integer currPage) {
        this.currPage = currPage;
    }

    private static final long serialVersionUID = 5026099605797151180L;

}
