package br.com.codeshare.data;

import java.util.List;

public interface Repository<T> {

	void insert(T bean);
	void update(T bean);
	void delete(T bean);
	T findById(Long id);
	List<T> findAllOrderedByName();
	List<T> findAllOrderedById();
	
}
