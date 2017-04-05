package com.funing.commonfn.service.impl;

import java.io.Serializable;
import java.util.List;

import com.funing.commonfn.dao.BaseDao;
import com.funing.commonfn.model.Entity;
import com.funing.commonfn.model.Entity.Criteria;
import com.funing.commonfn.model.Entity.Pagination;
import com.funing.commonfn.model.Entity.SimpleCriteria;
import com.funing.commonfn.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseServiceImpl<K extends Serializable, E extends Entity> implements BaseService<K, E> {
	
	@Autowired
	private BaseDao<K, E> dao;

	protected Logger logger = LoggerFactory.getLogger(getClass());


	public K save(E entity) {
		return dao.save(entity);
	}


	public int delete(K id) {
		return dao.delete(id);
	}


	public int delete(Criteria criteria) {
		return dao.delete(criteria);
	}

	
	public int update(E entity) {
		return dao.update(entity);
	}

	
	public int update(E entity, Criteria criteria) {
		return dao.update(entity, criteria);
	}

	
	public E selectOne(K id) {
		return dao.selectOne(id);
	}

	
	public E selectOne(Criteria criteria) {
		return dao.selectOne(criteria);
	}

	
	public List<E> selectAll() {
		return dao.selectAll();
	}


	public List<E> selectList(Criteria criteria) {
		return dao.selectList(criteria);
	}


	public long selectCount(Criteria criteria) {
		return dao.selectCount(criteria);
	}


	public Pagination selectPage(Criteria criteria, Pagination pagination) {
		if (pagination == null) {
			pagination = new Pagination();
		}
		if (pagination.getLimit() == 0) {
			pagination.setLimit(10);
		}
		if (pagination.getSort() != null) {
			if (pagination.getOrder() == null) {
				pagination.setOrder("ASC");
			}
			((SimpleCriteria) criteria).orderBy(pagination.getSort(), pagination.getOrder());
		}
		if (pagination.getField() != null) {
			String searchValue = pagination.getSearch();
			if (searchValue != null && !searchValue.contains("%")) {
				searchValue = "%" + searchValue + "%";
			}
			((SimpleCriteria) criteria).like(pagination.getField(), searchValue);
		}
		if (pagination.getPage() != 0) {
			pagination.setOffset((pagination.getPage() - 1) * pagination.getLimit());
		}
		((SimpleCriteria) criteria).limit(pagination.getOffset(), pagination.getLimit());
		pagination.setRows(dao.selectList(criteria));
		pagination.setTotal(dao.selectCount(criteria));
		boolean division = pagination.getTotal() % pagination.getLimit() == 0;
		int pages = (int) (pagination.getTotal() / pagination.getLimit());
		pagination.setPages(division ? pages : pages + 1);
		if (pagination.getPage() != 0) {
			pagination.setPrev(pagination.getPage() == 1 ? 1 : pagination.getPage() - 1);
			pagination.setNext(pagination.getPage() == pagination.getPages() ? pagination.getPage() : pagination.getPage() + 1);
		}
		return pagination;
	}

}