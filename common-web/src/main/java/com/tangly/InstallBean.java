package com.tangly;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author chenjianan
 * @date 2016/12/16-11:25
 * <p>
 * Describe:
 * 如果我们需要在Spring容器完成Bean的实例化，配置和其他的初始化后添加一些自己的逻辑处理，
 * 我们就可以定义一个或者多个BeanPostProcessor接口的实现。
 */
@Configuration
@Slf4j
public class InstallBean implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        log.debug("对象" + beanName + "开始实例化");
        if("serverEndpointExporter".equalsIgnoreCase(beanName)){
            log.info("单元测试时：如果报异常javax.websocket.server.ServerContainer not available，可以手动将serverEndpointExporter返回空");
//            return null;
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.debug("对象" + beanName + "实例化完成");
        return bean;
    }
}
