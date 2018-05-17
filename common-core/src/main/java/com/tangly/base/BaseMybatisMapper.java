package com.tangly.base;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * 单表增删改查的丰富mapper扩展类
 * mapper类继承该类实现默认的增删改查批量插入
 * @author tangly
 * @since 2017-06-26 21:53
 */
public interface BaseMybatisMapper<T> extends Mapper<T>, MySqlMapper<T>, InsertListMapper<T> {
    //FIXME 特别注意，该接口不能被扫描到，否则会出错
}