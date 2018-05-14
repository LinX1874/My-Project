package com.tangly.mapper;

import com.tangly.base.BaseMybatisMapper;
import com.tangly.entity.HelloWorld;
import org.springframework.stereotype.Repository;

/**
 * 用注解形式的查询
 * @author tangly
 */
@Repository
public interface HelloWorldMapper extends BaseMybatisMapper<HelloWorld> {

}