package com.hitler.common.aop;

import org.apache.commons.lang.StringUtils;
import org.hibernate.EmptyInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.rpc.RpcContext;
import com.hitler.common.dubbo.RpcContextAttachment;

/**
 * Mycat结合dubbo消费端域名动态改变schema
 * @author JT
 * @date 2016/04/07 18:33:00
 */
public class CheageSchemaInterceptor extends EmptyInterceptor {

	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory
			.getLogger(CheageSchemaInterceptor.class);

	@Override
	public String onPrepareStatement(String sql) {
		String schema_name = followRpcSchemaName();  
		String full_sql = sql;
		if (StringUtils.isNotEmpty(schema_name)) {
			String mycat_annotation = "/*#mycat:schema=" + schema_name + "*/ ";
			full_sql = mycat_annotation + sql;
		}
		logger.info("prepare " + sql);
		return super.onPrepareStatement(full_sql);
	}

	private String followRpcSchemaName() {
		String tenant_region = RpcContext.getContext().getAttachment(
				RpcContextAttachment.ATTR_T_SCHEMA);
		return tenant_region;
	}

}
