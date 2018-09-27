package com.hitler.common.dubbo.filter;

import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.RpcException;
import com.hitler.common.dubbo.RpcContextAttachment;

/**
 * RPC(Dubbo服务)调用传递ThreadLocal变量过滤器
 * @author onsoul@qq.com
 * @date 2016-4-12下午4:20:54
 */
public class AuthenticationFilter implements Filter {

	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		String schema_name="T1";
		System.out.println("消费端调用到过滤器!设置Schema.");
		
		//此过滤器在执行服务调用时调用,由Hibernate 拦截器 获取出来注入到SQL前辍.以完成Schema切换
		RpcContext.getContext().setAttachment(RpcContextAttachment.ATTR_T_SCHEMA, schema_name);
		Result result = invoker.invoke(invocation);
		return result;
	}

}
