package com.tangly.base;

import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;

/**
 * 通用Service
 * @author tangly
 * @param <T>
 */
public abstract class BaseService<T>  {

    @Autowired
    protected Mapper<T> mapper;
}