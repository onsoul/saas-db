package com.hitler.common.aop;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.lang.time.StopWatch;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;

/**
 * 服务调用次数拦截器
 * @author onsoul@qq.com
 * @date 2016-4-12下午4:18:28
 */
@Component
@Aspect
public class ServiceInterceptor {
	
	private static final Log LOGGER = LogFactory.getLog("服务拦截器");
	
	private static final Map<String, AtomicLong> serviceTimes = Maps.newConcurrentMap();
	
	@Around("execution(* com.hitler.service..*.*(..))")
	public Object logServiceTime(ProceedingJoinPoint pjp) throws Throwable {
		StopWatch watch = new StopWatch();
		watch.start();
		Object retVal = pjp.proceed();
		watch.stop();
		String str = pjp.getTarget().toString();
		String service = str.substring(str.lastIndexOf(".")+1, str.lastIndexOf("@")) + "." + pjp.getSignature().getName();
		AtomicLong times = serviceTimes.get(service);
		if (times == null) {
			times = new AtomicLong(0L);
			serviceTimes.put(service, times);
		}
		times.incrementAndGet();
		LOGGER.info(String.format("%s [耗时: %dms，调用次数: %d]", service, watch.getTime(), times.longValue()));
        return retVal;
	}

}
