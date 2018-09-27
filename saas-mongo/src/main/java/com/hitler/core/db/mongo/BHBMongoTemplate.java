package com.hitler.core.db.mongo;

import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.util.Assert;

import com.hitler.core.db.mongo.sequence.SequenceOption;

/**
 * 重写mongo模板 定制自增序列
 * @author DELL
 *
 */
public class BHBMongoTemplate extends MongoTemplate {

	private SequenceOption sequenceOption;

	public BHBMongoTemplate(MongoDbFactory mongoDbFactory,MongoConverter mongoConverter ,SequenceOption sequenceOption) {
		super(mongoDbFactory,mongoConverter);
		this.sequenceOption = sequenceOption;
	}

	@Override
	public void save(Object entity, String collectionName) {
		sequenceOption.purperSequence(this, collectionName, entity);
		super.save(entity, collectionName);
	}

	@Override
	public void insert(Object entity, String collectionName) {
		Assert.notNull(entity, "ObjectToSave must not be null!");
		Assert.notNull(collectionName, "CollectionName must not be null!");
		ensureNotIterable(entity);
		sequenceOption.purperSequence(this, collectionName, entity);
		super.doInsert(collectionName, entity, getConverter());
	}

	public void setSequenceOption(SequenceOption sequenceOption) {
		this.sequenceOption = sequenceOption;
	}

}
