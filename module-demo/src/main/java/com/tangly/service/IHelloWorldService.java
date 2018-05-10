package com.tangly.service;

import com.github.pagehelper.PageInfo;
import com.tangly.entity.HelloWorld;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * date: 2018/1/2 17:37 <br/>
 *
 * @author tangly
 * @since JDK 1.7
 */
public interface IHelloWorldService {

    /**
     * 分页查询HelloWorldd对象
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageInfo<HelloWorld> queryPage(Integer pageNo, Integer pageSize);

    /**
     * 获取列表
     *
     * @return
     */
    List<HelloWorld> getHelloWorldList();

    /**
     * 保存
     *
     * @param helloWorld
     * @return
     */
    HelloWorld save(HelloWorld helloWorld);

    /**
     * 更新所有字段
     *
     * @param helloWorld
     * @return
     */
    @CachePut(value = "helloWorld", key = "'helloWorld_' + #helloWorld.id")
    HelloWorld updateAll(HelloWorld helloWorld);

    /**
     * 获取
     *
     * @param id
     * @return
     */
    @Cacheable( value = "helloWorld", key = "'helloWorld_' + #id")
    HelloWorld getById(Integer id);

    /**
     * 删除
     *
     * @param id
     */
    @CacheEvict(value = "helloWorld", key = "'helloWorld_' + #id")
    int delete(Integer id);


}
