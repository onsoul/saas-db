package com.hitler.core.db.mongo;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import com.hitler.core.db.mongo.sequence.SequenceOption;
import com.mongodb.MongoClientURI;

/**
 * 注入mongo基础环境
 * 
 * @author onsoul 2018年4月23日
 */
@Configuration
public class MongoConfiguation {

	@Value("${spring.data.mongodb.uri}")
	private String mongoURI = "mongodb://saas:pwd@127.0.0.1:27017";

	@Bean
	public MongoDbFactory mongoDbFactory(MongoClientURI uri) {
		return new RoutingDBFactory(uri);
	}
	
	@Bean 
	public MongoClientURI mongoClientURI() {
		return new MongoClientURI(mongoURI); 
	}

	@Bean
	public MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory, SequenceOption sequenceOption) {
		DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory);

		MongoCustomConversions conversions = new MongoCustomConversions(resolverConverter());
		MongoMappingContext mappingContext = new BHBMongoMappingContext();
		mappingContext.setSimpleTypeHolder(conversions.getSimpleTypeHolder());
		mappingContext.afterPropertiesSet();
		MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, mappingContext);
		// _class 剔除
		converter.setTypeMapper(new DefaultMongoTypeMapper(null));
		converter.setCustomConversions(conversions);
		converter.afterPropertiesSet();

		// 需要自增时
		// MongoTemplate template=new DTXDMongoTemplate(mongoDbFactory,converter,
		// sequenceOption);
		MongoTemplate template = new MongoTemplate(mongoDbFactory, converter);
		return template;
	}

	private List<?> resolverConverter() {
		return Collections.singletonList(new StringToDateConverter());
	}

}
