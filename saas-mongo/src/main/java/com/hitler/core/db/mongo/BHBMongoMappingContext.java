package com.hitler.core.db.mongo;

import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import com.hitler.core.db.entity.Docs;

/**
 * 
 * 重写MongoContext 以注入自定义的Persistable
 * 
 * @author lixingxing@doupai.cc by DP
 * @version 1.0
 * @date 2017年5月17日 上午11:09:55
 */
public class BHBMongoMappingContext extends MongoMappingContext {

	public BHBMongoMappingContext() {
		super();
		addPersistentEntity(Docs.class);       //ORM  3天
	}

}
