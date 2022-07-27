package com.spring.demo.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;
import java.util.Map;



@JsonIgnoreProperties({ "current", "size", "total","pages","searchCount","records" })
public class BasePage<T>  implements IBasePage,Serializable {
    private List<T> list;
    private PageInfo page;
    public BasePage(){}
    /**
     * @method BasePage
     * @description 功能描述:分页构造函数，主要用于从前台参数中生成一个分页对象
     *
     * @date: 2019/2/26 10:58
     * @author: zhangcs
     * @param params
     * @return
     */
    public BasePage(Map params) {
        //解析分页参数
        Object obj=params.get(IBasePage.PAGE_CURR_NAME);
        long currPage= ( obj== null) ?1:Integer.parseInt(obj.toString());
        obj=params.get(IBasePage.PAGE_SIZE_NAME);
        int size= ( obj== null) ?DEFAULT_PAGE_SIZE:Integer.parseInt(obj.toString());
        this.page = new PageInfo(currPage, size);
    }
    public BasePage(long currPage, long size)  {
        this.page = new PageInfo(currPage, size);
    }
    /**
     * @method BasePage
     * @description 功能描述:
     * 构造函数，用于从mybatisplus返回的数据构造自定义格式的Page类
     * @date: 2019/2/26 11:16
     * @author: zhangcs
     * @param page
     * @return
     */
    public BasePage(IPage page) {
        this.list=page.getRecords();
        this.page=new PageInfo(page);
    }
    /**
     * @method getPage
     * @description 功能描述:
     *  分页的工厂方法
     * @date: 2019/2/26 11:16
     * @author: zhangcs
     * @param params
     * @return com.pct.dotware.commons.page.BasePage
     */
    public static IBasePage instance(Map params){
        return new BasePage(params);
    }

    @Override
    public List<OrderItem> orders() {
        return null;
    }

    @Override
    public List<T> getRecords() {
        return list;
    }

    @Override
    public IPage setRecords(List records) {
        list=records;
        return this;
    }

    @Override
    public long getTotal() {
        return page.getTotal();
    }

    @Override
    public IPage<Object> setTotal(long total) {
        page.setTotal(total);
        if(page.getPages() == 0) {
            page.setPages((total  +  page.getSize()  - 1) / page.getSize());
        }
        return this;
    }

    @Override
    public long getSize() {
        return page.getSize();
    }

    @Override
    public IPage<Object> setSize(long size) {
        page.setSize(size);
        return this;
    }

    @Override
    public long getCurrent() {
        return page.getCurrent();
    }

    @Override
    public IPage<Object> setCurrent(long current) {
        page.setCurrent(current);
        return this;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public PageInfo getPage() {
        return this.page;
    }

    public void setPage(PageInfo page) {
        this.page = page;
    }
}
