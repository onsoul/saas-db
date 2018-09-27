package com.hitler.common.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.util.StringUtils;

public class Sorter implements Serializable {

	private static final long serialVersionUID = 8774379137332701467L;

	public static final String DEFAULT_DIRECTION = "ASC";

	private final List<Order> orders;
	
	public Sorter(String _direction, String _propertie) {
		this(_direction, _propertie == null ? new ArrayList<String>() : Arrays.asList(_propertie));
	}
	
	public Sorter(String _direction, String... _properties) {
		this(_direction, _properties == null ? new ArrayList<String>() : Arrays.asList(_properties));
	}
	
	public Sorter(String direction, List<String> properties) {

		if (properties == null || properties.isEmpty()) {
			throw new IllegalArgumentException("You have to provide at least one property to sort by!");
		}

		this.orders = new ArrayList<Order>(properties.size());

		for (String property : properties) {
			this.orders.add(new Order(direction, property));
		}
	}
	
	public Sorter(Order... orders) {
		this(Arrays.asList(orders));
	}
	
	public Sorter(List<Order> orders) {

		if (null == orders || orders.isEmpty()) {
			throw new IllegalArgumentException("You have to provide at least one sort property to sort by!");
		}

		this.orders = orders;
	}
	
	public Sorter(String... properties) {
		this(DEFAULT_DIRECTION, properties);
	}

	public List<Order> getOrders() {
		return orders;
	}

	public static class Order implements Serializable {
		private static final long serialVersionUID = 2286583688034115153L;

		private final String direction;
		private final String property;
		
		public Order(String direction, String property) {
			if (!StringUtils.hasText(property)) {
				throw new IllegalArgumentException("Property must not null or empty!");
			}

			this.direction = direction == null ? DEFAULT_DIRECTION : direction;
			this.property = property;
		}
		
		public Order(String property) {
			this(DEFAULT_DIRECTION, property);
		}

		public String getDirection() {
			return direction;
		}

		public String getProperty() {
			return property;
		}
		
	}
}
