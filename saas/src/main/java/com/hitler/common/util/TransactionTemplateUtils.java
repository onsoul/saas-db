package com.hitler.common.util;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

public class TransactionTemplateUtils {
	
	public static TransactionTemplate getTransactionTemplate(PlatformTransactionManager transactionManager, int propagationBehavior, int isolationLevel) {
		TransactionTemplate template = new TransactionTemplate(transactionManager);
		template.setPropagationBehavior(propagationBehavior);
		return template;
	}
	
	public static TransactionTemplate getDefaultTransactionTemplate(PlatformTransactionManager transactionManager) {
		return getTransactionTemplate(transactionManager, TransactionDefinition.PROPAGATION_REQUIRED, TransactionDefinition.ISOLATION_READ_COMMITTED);
	}
	
	public static TransactionTemplate getRequiresNewTransactionTemplate(PlatformTransactionManager transactionManager) {
		return getTransactionTemplate(transactionManager, TransactionDefinition.PROPAGATION_REQUIRES_NEW, TransactionDefinition.ISOLATION_READ_COMMITTED);
	}

}
