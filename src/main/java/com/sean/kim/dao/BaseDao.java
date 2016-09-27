package com.sean.kim.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by seankim on 2016-09-24.
 */
 public abstract class BaseDao<E, I> {

        @Autowired(required = true)
        private SessionFactory sessionFactory;

        private final Class<E> entityClass;

        /**
         * Constructor
         *
         * @param inEntityClass the entity class this DAO work with
         */
        public BaseDao(Class<E> inEntityClass) {
            this.entityClass = inEntityClass;
        }

        @SuppressWarnings("unchecked")
        @Transactional(propagation = Propagation.REQUIRED)
        public I findById(long id) {
            return (I) this.currentSession().get(this.entityClass, id);
        }

        @SuppressWarnings("unchecked")
        @Transactional(propagation = Propagation.REQUIRED)
        public void saveOrUpdate(I object) {
            this.currentSession().saveOrUpdate(object);
        }



        @Transactional(propagation = Propagation.REQUIRED)
        public long persist(E newEntity) {
            return (Long) this.currentSession().save(newEntity);
        }

        protected Session currentSession() {
            return this.sessionFactory.getCurrentSession();
        }

        protected Criteria getCriteria() {
            return this.currentSession().createCriteria(this.entityClass);
        }

        @Transactional(propagation = Propagation.REQUIRED)
        public void remove(E newEntity) {
            this.currentSession().delete(newEntity);
        }




}
