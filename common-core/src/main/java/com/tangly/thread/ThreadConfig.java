package com.tangly.thread;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author tangly
 */
@Configuration
public class ThreadConfig {
    @Bean
    public TaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //基本线程大小，超出最大队列后才会创建更多的线程
        executor.setCorePoolSize(3);
        //最大等待工作队列
        executor.setQueueCapacity(20);
        executor.setMaxPoolSize(10);
        executor.setKeepAliveSeconds(300);
        executor.setThreadNamePrefix("default_task_executor_thread");
        executor.initialize();
        return executor;
    }


}