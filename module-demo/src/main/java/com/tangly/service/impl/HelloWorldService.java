package com.tangly.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tangly.base.BaseService;
import com.tangly.entity.HelloWorld;
import com.tangly.service.IHelloWorldService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author tangly
 * @since JDK 1.7
 */
@Service
@Slf4j
public class HelloWorldService extends BaseService<HelloWorld> implements IHelloWorldService {

    @Override
    public PageInfo<HelloWorld> queryPage(Integer pageNo, Integer pageSize) {
        log.info("分页查询HelloWorld {} {}" , pageNo, pageSize);
        PageHelper.startPage(pageNo, pageSize);
        List<HelloWorld> list = mapper.selectAll();
        return new PageInfo(list);
    }

    @Override
    public List<HelloWorld> getHelloWorldList() {
        Example helloWorld = new Example(HelloWorld.class);
        helloWorld.or();
        return mapper.selectByExample(helloWorld);
    }

    @Override
    public HelloWorld save(HelloWorld helloWorld) {
        mapper.insert(helloWorld);
        return helloWorld;
    }

    @Override
    public HelloWorld getById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public int delete(Integer id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public HelloWorld updateAll(HelloWorld helloWorld) {
        mapper.updateByPrimaryKey(helloWorld);
        return helloWorld;
    }
}
