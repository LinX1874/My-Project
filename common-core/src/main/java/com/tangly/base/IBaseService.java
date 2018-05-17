package com.tangly.base;

import com.tangly.bean.PageRequest;
import com.tangly.bean.PageResponse;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 所有的接口继承该类
 * 通用接口类，默认接口继承已获得默认的接口方法
 * @author tangly
 * @since JDK 1.7
 */
public interface IBaseService<T> {

    /**
     * 保存一个实体，null的属性也会保存，不会使用数据库默认值
     * @param t
     * @return 影响的记录数
     */
    T insert(T t);

    /**
     * 保存一个实体，null的属性不会保存，会使用数据库默认值
     * @param t
     * @return 影响的记录数
     */
    T insertSelective(T t);

    /**
     * 批量插入实体列表，性能高于for循环单独insert数据
     * @param t 实体列表
     * @return 影响的记录数
     */
    int insertList(List<T> t);

    /**
     * 根据主键字段进行删除，方法参数必须包含完整的主键属性
     * @param key
     * @return
     */
    int deleteByPrimaryKey(Object key);

    /**
     * 根据实体属性作为条件进行删除，查询条件使用等号
     * @param t
     * @return
     */
    int delete(T t);

    /**
     * 根据主键更新实体全部字段，null值会被更新
     * @param t
     * @return
     */
    int updateByPrimaryKey(T t);

    /**
     * 根据主键更新属性不为null的值
     * @param t
     * @return
     */
    int updateByPrimaryKeySelective(T t);


    /**
     * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
     * @param key
     * @return
     */
    T selectByPrimaryKey(Object key);


    /**
     * 根据实体中的属性值进行查询，查询条件使用等号
     * @param t
     * @return
     */
    T selectOne(T t);

    /**
     * 根据实体中的属性查询总数，查询条件使用等号
     * @param t
     * @return
     */
    int selectCount(T t);

    /**
     * 查询全部结果
     * @return
     */
    List<T> selectAll();

    /**
     * 根据实体中的属性值进行查询，查询条件使用等号
     * @param pageRequest
     * @param clazz 查找的类型
     * @return
     */
    PageResponse<T> selectByPage(PageRequest pageRequest, Class clazz);

    /**
     * 根据实体中的属性值进行查询，查询条件使用等号
     * @param t
     * @return
     */
    List<T> select(T t);


    /**
     * 根据Example 查找对象
     * @param e
     * @return
     */
    List<T> selectByExample(Example e);

}
