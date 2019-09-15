package com.shankulk.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


@SuppressWarnings({"unused", "unchecked", "UnusedReturnValue"})
public class BaseDao<T> {

    private final SessionFactory sessionFactory;

    BaseDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private CriteriaBuilder getCriteriaBuilder(){
        return getCurrentSession().getCriteriaBuilder();
    }

    public Serializable save(T t) {
        return getCurrentSession().save(t);
    }

    public T getById(Serializable id) {
        return (T) getCurrentSession().get(getEntityClassType(), id);

    }

    public List<T> getAll() {
        CriteriaBuilder criteriaBuilder = getCriteriaBuilder();

        CriteriaQuery<T> criteriaQuery = (CriteriaQuery<T>)criteriaBuilder.createQuery(getEntityClassType());
        Root<T> from = (Root<T>)criteriaQuery.from(getEntityClassType());
        CriteriaQuery<T> selectQuery = criteriaQuery.select(from);

        TypedQuery<T> typedQuery = getCurrentSession().createQuery(selectQuery);
        return typedQuery.getResultList();
    }

    public T update(T t) {
        return merge(t);
    }

    public T merge(T t) {
        return (T) getCurrentSession().merge(t);
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    private Class<?> getEntityClassType() {
        return (Class<?>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
