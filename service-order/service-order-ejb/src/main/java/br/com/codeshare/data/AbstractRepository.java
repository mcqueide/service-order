package br.com.codeshare.data;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.logging.Logger;

public class AbstractRepository<T extends Serializable> implements Repository<T> {

	@Inject
	private EntityManager em;
	@Inject
	protected Logger log;

	@SuppressWarnings("unchecked")
	private Class<T> getType() {
		ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
		return (Class<T>) (type).getActualTypeArguments()[0];
	}

	@Override
	public void insert(T bean) {
		log.info("Gravando " + getType());
		getEntityManager().persist(bean);
	}

	@Override
	public void update(T bean) {
		bean = getEntityManager().merge(bean);
	}

	@Override
	public void delete(T bean) {
		getEntityManager().remove(bean);
	}

	@Override
	public T findById(Long id) {
		return getEntityManager().find(getType(), id);
	}

	@Override
	public List<T> findAllOrderedByName() {
		log.info("Recovering data ...");
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<T> criteria = cb.createQuery(getType());
		Root<T> root = criteria.from(getType());
		criteria.select(root).orderBy(cb.asc(root.get("name")));
		return getEntityManager().createQuery(criteria).getResultList();
	}
	
	@Override
	public List<T> findAllOrderedById() {
		log.info("Recovering data ...");
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<T> criteria = cb.createQuery(getType());
		Root<T> root = criteria.from(getType());
		criteria.select(root).orderBy(cb.asc(root.get("id")));
		return getEntityManager().createQuery(criteria).getResultList();
	}

	@Override
	public EntityManager getEntityManager() {
		return em;
	}
}
