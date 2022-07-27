package com.spring.demo.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;


public interface IBasePage  <T> extends IPage <T> {
    public static final String PAGE_CURR_NAME = "current";
    public static final String PAGE_SIZE_NAME = "size";
    public static final String PAGE_TOKEN_NAME="access_token";  //微信端传的token参数
    public static final int DEFAULT_PAGE_SIZE=10;//每页缺省的记录数
}
