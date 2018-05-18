package com.tangly.base;

import com.tangly.bean.PageResponse;
import com.tangly.bean.SearchParam;
import com.tangly.enums.ESort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 通用ServiceImple
 * Service实现类都继承该方法，以获得常用的怎删改查默认实现
 *
 * @param <T>
 * @author tangly
 */
public abstract class BaseServiceImpl<T> implements IBaseService<T> {

    /**
     * 继承了BaseServiceImpl类后，可以直接使用该通用mapper类实现单表的所有操作。
     */
    @Autowired
    protected BaseMybatisMapper<T> mapper;

    /**
     * 保存一个实体，null的属性也会保存，不会使用数据库默认值
     *
     * @param t
     * @return
     */
    @Override
    public T insert(T t) {
        mapper.insert(t);
        return t;
    }

    /**
     * 保存一个实体，null的属性不会保存，会使用数据库默认值
     *
     * @param t
     * @return
     */
    @Override
    public T insertSelective(T t) {
        mapper.insertSelective(t);
        return t;
    }

    /**
     * 批量插入实体列表，性能高于for循环单独insert数据
     *
     * @param t 实体列表
     * @return
     */
    @Override
    public int insertList(List<T> t) {
        return mapper.insertList(t);
    }

    /**
     * 根据主键字段进行删除，方法参数必须包含完整的主键属性
     *
     * @param key
     * @return
     */
    @Override
    public int deleteByPrimaryKey(Object key) {
        return mapper.deleteByPrimaryKey(key);
    }

    /**
     * 根据实体属性作为条件进行删除，查询条件使用等号
     *
     * @param t
     * @return
     */
    @Override
    public int delete(T t) {
        return mapper.delete(t);
    }

    /**
     * 根据主键更新实体全部字段，null值会被更新
     *
     * @param t
     * @return
     */
    @Override
    public int updateByPrimaryKey(T t) {
        return mapper.updateByPrimaryKey(t);
    }

    /**
     * 根据主键更新属性不为null的值
     *
     * @param t
     * @return
     */
    @Override
    public int updateByPrimaryKeySelective(T t) {
        return mapper.updateByPrimaryKeySelective(t);
    }

    /**
     * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
     *
     * @param key
     * @return
     */
    @Override
    public T selectByPrimaryKey(Object key) {
        return mapper.selectByPrimaryKey(key);
    }

    /**
     * 根据实体中的属性值进行查询，查询条件使用等号
     *
     * @param t
     * @return
     */
    @Override
    public T selectOne(T t) {
        return mapper.selectOne(t);
    }

    /**
     * 根据实体中的属性查询总数，查询条件使用等号
     *
     * @param t
     * @return
     */
    @Override
    public int selectCount(T t) {
        return mapper.selectCount(t);
    }

    /**
     * 查询全部结果
     *
     * @return
     */
    @Override
    public List<T> selectAll() {
        return mapper.selectAll();
    }

    public static Type[] getParameterizedTypes(Object object) {
        Type superclassType = object.getClass().getGenericSuperclass();
        if (!ParameterizedType.class.isAssignableFrom(superclassType.getClass())) {
            return null;
        }
        return ((ParameterizedType) superclassType).getActualTypeArguments();
    }

    /**
     * 根据实体中的属性值进行查询，查询条件使用等号
     *
     * @param searchParam
     * @return
     */
    @Override
    public PageResponse<T> selectByPage(SearchParam searchParam, Class clazz) {

        Example example = new Example(clazz);
        List<T> list;

        //排序过滤条件
        if (!ObjectUtils.isEmpty(searchParam)) {
            if (!ObjectUtils.isEmpty(searchParam)) {
                Map<String, ESort> orderBys = searchParam.getOrderBys();
                Map<String, Object> searchParams = searchParam.getColumnParams();

                if (!ObjectUtils.isEmpty(orderBys)) {
                    Iterator<Map.Entry<String, ESort>> sortIterator = orderBys.entrySet().iterator();
                    Map.Entry<String, ESort> sortEntry;
                    while (sortIterator.hasNext()) {
                        sortEntry = sortIterator.next();
                        sortEntry.getKey();
                        sortEntry.getValue();
                        if (ESort.asc.equals(sortEntry.getValue())) {
                            example.orderBy(sortEntry.getKey()).asc();
                        } else if (ESort.desc.equals(sortEntry.getValue())) {
                            example.orderBy(sortEntry.getKey()).desc();
                        }
                    }
                }

                if (!ObjectUtils.isEmpty(searchParams)) {
                    Iterator<Map.Entry<String, Object>> searchIterator = searchParams.entrySet().iterator();
                    Map.Entry<String, Object> searchEntity;
                    while (searchIterator.hasNext()) {
                        searchEntity = searchIterator.next();
                        example
                                .or()
                                .andLike(searchEntity.getKey(), String.valueOf(searchEntity.getValue()));
                    }
                }
            }
        }


        list = mapper.selectByExample(example);

        return new PageResponse<>(list);
    }

    /**
     * 根据实体中的属性值进行查询，查询条件使用等号
     *
     * @param t
     * @return
     */
    @Override
    public List<T> select(T t) {
        return mapper.select(t);
    }

    /**
     * 根据Example 查找对象
     *
     * @param e
     * @return
     */
    @Override
    public List<T> selectByExample(Example e) {
        return mapper.selectByExample(e);
    }

}