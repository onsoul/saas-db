package com.hitler.core.db.entity;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 自增序列化ID
 * @author onsoul
 * 2018年7月31日 下午2:10:07
 */
@Document(collection = "_SEQUENCE_ID")
public class SequenceId implements Serializable {

	private static final long serialVersionUID = 1L;
	public static String SEQUENCE_ID_COL_NAME = "_SEQUENCE_ID";
	public static String COLLNAME = "collName";
	public static String ROW = "row";
	public static String SEQ = "seq";

	private String collName;
	private String row;
	private Long seq;

	public String getCollName() {
		return collName;
	}

	public void setCollName(String collName) {
		this.collName = collName;
	}

	public String getRow() {
		return row;
	}

	public void setRow(String row) {
		this.row = row;
	}

	public Long getSeq() {
		return seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}
}
