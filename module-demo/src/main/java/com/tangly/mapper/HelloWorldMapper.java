package com.tangly.mapper;

import com.tangly.base.BaseMapper;
import com.tangly.entity.HelloWorld;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用注解形式的查询
 */
@Repository
public interface HelloWorldMapper extends BaseMapper<HelloWorld> {

    @Select("SELECT * FROM hello_world")
    @Results({
            @Result(property = "id",  column = "id"),
            @Result(property = "name", column = "name")
    })
    List<HelloWorld> getAll();

    /**
     * 使用xml实现
     * @param helloWorld
     */
    void addHelloWorld(HelloWorld helloWorld);
}