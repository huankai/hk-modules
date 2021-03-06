package com.hk.core.query.jpa;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.google.common.collect.Lists;
import com.hk.commons.util.ArrayUtils;
import com.hk.commons.util.CollectionUtils;
import com.hk.core.query.Order;

public class Criteria<T> implements Specification<T> {

	private List<Criterion<T>> criterions = Lists.newArrayList();

	private List<Criterion<T>> havings = Lists.newArrayList();

	private List<Order> orders = Lists.newArrayList();

	private List<String> groupByPropertyNames;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List predicates;
		Iterator var5;
		Criterion criterion;
		if (!this.criterions.isEmpty()) {
			predicates = Lists.newArrayList();
			var5 = this.criterions.iterator();
			while (var5.hasNext()) {
				criterion = (Criterion) var5.next();
				if (criterion != null) {
					Predicate predicate = criterion.toPredicate(root, query, cb);
					if (predicate != null) {
						predicates.add(predicate);
					}
				}
			}
			if (predicates.size() > 0) {
				query.where(cb.and((Predicate[]) predicates.toArray(new Predicate[0])));
			}
		}
		if (this.orders != null) {
			predicates = Lists.newArrayList();
			var5 = this.orders.iterator();
			while (var5.hasNext()) {
				Order order = (Order) var5.next();
				predicates.add(order.toJpaOrder(root));
			}
			query.orderBy(predicates);
		}
		if (this.groupByPropertyNames != null) {
			predicates = Lists.newArrayList();
			var5 = this.groupByPropertyNames.iterator();

			while (var5.hasNext()) {
				String propertyName = (String) var5.next();
				predicates.add(PathUtils.getPath(root, propertyName));
			}

			query.groupBy(predicates);
		}
		if (!this.havings.isEmpty()) {
			predicates = Lists.newArrayList();
			var5 = this.havings.iterator();
			while (var5.hasNext()) {
				criterion = (Criterion) var5.next();
				predicates.add(criterion.toPredicate(root, query, cb));
			}
			if (predicates.size() > 0) {
				query.having((Predicate[]) predicates.toArray(new Predicate[0]));
			}
		}
		return null;
	}

	public Criteria<T> add(Criterion<T> criterion) {
		if (criterion != null) {
			this.criterions.add(criterion);
		}
		return this;
	}

	public Criteria<T> having(Criterion<T> criterion) {
		if (criterion != null) {
			this.havings.add(criterion);
		}
		return this;
	}

	public void addOrder(Order... orders) {
		CollectionUtils.addAll(this.orders, orders);
	}

	public void addGroupBy(List<String> propertyNames) {
		this.groupByPropertyNames = propertyNames;
	}

	public void addGroupBy(String... propertyNames) {
		if (ArrayUtils.isNotEmpty(propertyNames)) {
			addGroupBy(Arrays.asList(propertyNames));
		}
	}

	public List<Criterion<T>> getCriterions() {
		return criterions;
	}

	public List<Criterion<T>> getHavings() {
		return havings;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public List<String> getGroupByPropertyNames() {
		return groupByPropertyNames;
	}

}
