package com.hitler.common.aop;

import static java.lang.annotation.ElementType.METHOD;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.persistence.OptimisticLockException;

import org.springframework.orm.ObjectOptimisticLockingFailureException;

/**
 * 
 * 事务重试注解
 * @author onsoul@qq.com
 * @date 2016-4-12下午4:16:04
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = { METHOD })
public @interface RetriableTransaction {

	int maxRetries() default 100;
	
	Class<? extends Throwable>[] retryFor() default {ObjectOptimisticLockingFailureException.class, OptimisticLockException.class};
	
}
