package com.hitler.server;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hitler.service.business.IRecordService;

/**
 * 初始化Spring Data JPA，并为DAO接口提供Dubbo Provider服务
 * @author onsoul
 * @version 1.0
 * 
 */

public class DAOProvider {

	public static void main(String[] args) throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"conf/provider/ha-db-context.xml");
		context.start();
		IRecordService recordService = context.getBean(IRecordService.class);
		int result = recordService.ping(" local.");  //本地方法调用
		
		System.out.println("main method result is:" + result);
		System.out.println("Service successfully started");
		// 按任意键退出
		System.in.read();
	}
}