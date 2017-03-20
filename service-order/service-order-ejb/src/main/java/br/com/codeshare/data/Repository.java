package br.com.codeshare.data;

import javax.persistence.EntityManager;
import java.util.List;

public interface Repository<T> {

	void insert(T bean);
	void update(T bean);
	void delete(T bean);
	void delete(Long id);
	T findById(Long id);
	List<T> findAllOrderedByName();
	List<T> findAllOrderedById();
	EntityManager getEntityManager();
	
}
