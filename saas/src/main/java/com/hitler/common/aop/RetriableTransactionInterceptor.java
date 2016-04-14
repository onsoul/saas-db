package com.hitler.common.aop;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;



/**
 *  事务重试拦截器
 * @author onsoul@qq.com
 * @date 2016-4-12下午4:18:11
 */
@Aspect
@Component
public class RetriableTransactionInterceptor {
	
	private static final Log LOGGER = LogFactory.getLog(RetriableTransactionInterceptor.class);
	
	@Autowired
	private PlatformTransactionManager transactionManager;

	@Around("@annotation(com.hitler.common.aop.annotation.RetriableTransaction)")
	public Object retry(ProceedingJoinPoint pjp) throws Throwable {
		MethodSignature signature = (MethodSignature) pjp.getSignature();
		Method method = signature.getMethod();
		RetriableTransaction annotation = method.getAnnotation(RetriableTransaction.class);
		int maxAttempts = annotation.maxRetries();
		int attemptCount = 0;
		List<Class<? extends Throwable>> exceptions = Arrays.asList(annotation.retryFor());
		Throwable failure = null;
		TransactionStatus currentTransactionStatus = null;
		String businessName = pjp.getTarget().toString();
		businessName = businessName.substring(0, businessName.lastIndexOf("@")) + "." + method.getName();
		do {
			attemptCount++;
			try {
				DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
				transactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
				currentTransactionStatus = transactionManager.getTransaction(transactionDefinition);
				Object returnValue = pjp.proceed();
				transactionManager.commit(currentTransactionStatus);
				return returnValue;
			} catch (Throwable t) {
				if (!exceptions.contains(t.getClass())) {
					throw t;
				}
				if (currentTransactionStatus != null && !currentTransactionStatus.isCompleted()) {
					transactionManager.rollback(currentTransactionStatus);
					failure = t;
				}
				LOGGER.debug("事务重试：["+businessName+"："+attemptCount+"/"+maxAttempts+"]");
			}
		} while (attemptCount < maxAttempts);
		LOGGER.debug("事务重试：["+businessName+"：已达最大重试次数]");
		throw failure;
	}
}
