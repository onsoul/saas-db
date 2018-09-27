package com.hitler.common.model.auditing;

import org.springframework.data.domain.AuditorAware;

import com.alibaba.dubbo.rpc.RpcContext;
import com.hitler.common.dubbo.RpcContextAttachment;

/**
 * 默认操作者字段值填充
 * @author onsoul@qq.com
 * @date 2016-4-12下午4:22:42
 */
public class DefaultAuditorAware implements AuditorAware<String> {

	public String getCurrentAuditor() {
         String username = RpcContext.getContext().getAttachment(RpcContextAttachment.ATTR_NAME_USERNAME);  
        return null == username ? "SYSTEM" : username;
	}
}
