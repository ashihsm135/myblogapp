package com.javasampleapproach.twitterbootstrap.service.api;

public interface CrudApi<T> {

	T save(T entity);

	T update(String id, T entity);

	T get(String id);

}
