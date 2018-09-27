package com.hitler.core.services;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.hitler.core.beans.Opations;
import com.hitler.core.db.entity.Docs;
import com.mongodb.client.result.DeleteResult;

/**
 * 所有Serivce 的基础类型
 *
 * @author onsoul 2018年8月1日 下午5:16:27
 */
@SuppressWarnings("ALL")
@Service
public abstract class BaseService<T> {

	@Autowired
	protected MongoTemplate mongoTemplate;

	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	public Document save(Document t, String collectionName) {
		mongoTemplate.save(t, collectionName);
		return t;
	}

	public T update(String id, Document updoc, T t) {
		Update update = Update.fromDocument(updoc, "");
		Query query = Query.query(Criteria.where(Opations.ID_FIELD).is(id));
		T result = (T) mongoTemplate.findAndModify(query, update, t.getClass());
		return result;
	}

	public boolean delete(String collectionName, String id) {
		Query query = Query.query(Criteria.where(Opations.ID_FIELD).is(id));
		DeleteResult result = mongoTemplate.remove(query, collectionName);
		return result.getDeletedCount() > 0;
	}

	public Document findById(String collectionName, String id) {
		Document result = mongoTemplate.findById(id, Document.class, collectionName);
		return result;
	}


	public abstract T findById(String id);

	public List<T> findALL(String collectionName, Document queryDoc, T claz, Pageable pageable) {
		Query query = new BasicQuery(queryDoc);
		query.with(pageable);
		List<T> result = mongoTemplate.find(query, (Class<T>) claz, collectionName);
		return result;
	}

	public Page<T> query(Query query) {
		Class<T> tClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		long count = mongoTemplate.count(query, tClass);
		List<T> list = count > 0 ? mongoTemplate.find(query, tClass) : new ArrayList<>();
		return new PageImpl<T>(list,
				PageRequest.of(Math.toIntExact(query.getSkip() / query.getLimit()), query.getLimit()), count);
	}
}
