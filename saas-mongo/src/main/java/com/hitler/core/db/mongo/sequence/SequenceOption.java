package com.hitler.core.db.mongo.sequence;

import java.lang.reflect.Field;
import java.util.Optional;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.data.util.ReflectionUtils.AnnotationFieldFilter;
import org.springframework.stereotype.Component;

import com.hitler.core.db.entity.SequenceId;

/**
 * 替换掉自增值
 * 
 * @author onsoul
 *
 */
@Component
@Lazy
public class SequenceOption {

	private static final AnnotationFieldFilter IDNO_FILTER = new AnnotationFieldFilter(IDNo.class);

	public void purperSequence(MongoTemplate template, String collectionName, Object entity) {
		Optional<Field> noField = Optional.ofNullable(ReflectionUtils.findField(entity.getClass(), IDNO_FILTER));
		noField.ifPresent(f -> {
			try {
				String fieldName = noField.get().getName();
				Long increment = generate(template, collectionName, fieldName, 1l);
				f.setAccessible(true);
				f.set(entity, increment);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	private long generate(MongoTemplate template, String collectionName, String rowName, Long incrementVal) {
		Criteria criteria = Criteria.where(SequenceId.COLLNAME).is(collectionName);
		if (rowName != null) {
			criteria.and(SequenceId.ROW).is(rowName);
		} else {
			criteria.and(SequenceId.ROW).ne("").ne(null);
		}
		Query query = new Query(criteria);

		Update update = new Update();
		update.inc(SequenceId.SEQ, incrementVal);
		FindAndModifyOptions options = new FindAndModifyOptions();
		options.upsert(false); // 不做插入，所有的自增键由表维护
		options.returnNew(true);
		SequenceId seqId = template.findAndModify(query, update, options, SequenceId.class,
				SequenceId.SEQUENCE_ID_COL_NAME);

		return seqId.getSeq();
	}

}
