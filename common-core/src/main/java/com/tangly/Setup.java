package com.tangly;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author chenjianan
 * @date 2016/12/15-17:24
 * <p>
 * Describe:
 * beanFactoryPostprocessor的作用是在beanFactory初始化之后提供一个修改的机会。
 * 在Spring实例化bean的前后执行一些附加操作
 */
@Configuration
public class Setup implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        System.out.println("在beanFactory初始化之后提供一个修改的机会。此时bean还没实例化（instance)");
    }
}
