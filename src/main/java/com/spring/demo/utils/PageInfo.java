package com.spring.demo.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.io.Serializable;

public class PageInfo implements Serializable {
   public PageInfo(){}
    public PageInfo(IPage page) {
        this.current = page.getCurrent();
        this.pages = page.getPages();
        this.size = page.getSize();
        this.total = page.getTotal();
        this.searchCount = page.isSearchCount();
    }

    public PageInfo(long current, long size) {
        this.current = current;
        this.size = size;
    }

    /** 当前页 */
    private long current = 1;
    /**  每页显示条数，默认 10 */
    private long size = 10;
    /** 总数记录数*/
    private long total = 0;
    /** 总页数  */
    private long pages = 0;
    /** 是否进行 count 查询*/
    private boolean searchCount = true;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getCurrent() {
        return current;
    }

    public void setCurrent(long current) {
        this.current = current;
    }

    public boolean isSearchCount() {
        return searchCount;
    }

    public void setSearchCount(boolean searchCount) {
        this.searchCount = searchCount;
    }

    public long getPages() {
        return pages;
    }

    public void setPages(long pages) {
        this.pages = pages;
    }

}
