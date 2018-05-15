package com.tangly.aspect;

import com.tangly.exception.RequestLimitException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author tangly
 * @time：
 * @Discription： 限制请求的频率
 */
@Aspect
@Component
@Slf4j
public class RequestLimitContract {

    private Map<String, Integer> redisTemplate = new HashMap<>();

    /**
     * 允许访问的最大次数
     */
    int maxCount = 100;

    /**
     * 时间段，单位为毫秒，默认值一分钟
     */
    long time = 60;

    @Before("execution(* com.tangly.*.*Controller.*(..))")
    public void requestLimit(final JoinPoint joinPoint) throws RequestLimitException {

        HttpServletRequest request;
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            request = attributes.getRequest();
        } else {
            throw new RequestLimitException("方法中缺失HttpServletRequest参数");
        }

        String ip = request.getLocalAddr();
        String url = request.getRequestURL().toString();
        String key = "req_limit_".concat(url).concat(ip);
        if (redisTemplate.get(key) == null || redisTemplate.get(key) == 0) {
            redisTemplate.put(key, 1);
        } else {
            redisTemplate.put(key, redisTemplate.get(key) + 1);
        }
        int count = redisTemplate.get(key);
        if (count > 0) {

            //创建一个定时器
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    redisTemplate.remove(key);
                }
            };

            ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(2);
            newScheduledThreadPool.schedule(timerTask, time, TimeUnit.SECONDS);
        }
        if (count > maxCount) {
            log.info("用户IP[" + ip + "]访问地址[" + url + "]超过了限定的次数[" + maxCount + "]");
            throw new RequestLimitException("请求过于频繁,休息一下吧");
        }

    }
}