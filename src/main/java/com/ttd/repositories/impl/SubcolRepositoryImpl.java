/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ttd.repositories.impl;

import com.ttd.pojo.Subcol;
import com.ttd.pojo.Subcol;
import com.ttd.pojo.Subcol;
import com.ttd.repositories.SubcolRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.AssertionFailure;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author DELL
 */
@Repository
@Transactional
public class SubcolRepositoryImpl implements SubcolRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private Environment env;

    @Override
    public List<Subcol> getSubcols(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Subcol> q = b.createQuery(Subcol.class);
        Root root = q.from(Subcol.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();

            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                predicates.add(b.like(root.get("name"), String.format("%%%s%%", kw)));
            }
            String courseId = params.get("courseId");
            if (courseId != null && !courseId.isEmpty()) {
                predicates.add(b.equal(root.get("courseId").get("id"), Integer.parseInt(courseId)));
            }
            q.where(predicates.toArray(Predicate[]::new));
        }

        q.orderBy(b.desc(root.get("id")));

        Query query = session.createQuery(q);

        if (params != null) {
            String page = params.get("page");
            if (page != null && !page.isEmpty()) {
                int p = Integer.parseInt(page);
                int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
                query.setMaxResults(pageSize);
                query.setFirstResult((p - 1) * pageSize);
            }
        }

        return query.getResultList();
    }

    @Override
    public int countSubcols(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Subcol> scRoot = cq.from(Subcol.class);
        cq.select(cb.count(scRoot));
        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();

            String courseId = params.get("courseId");
            if (courseId != null && !courseId.isEmpty()) {
                predicates.add(cb.equal(scRoot.get("courseId").get("id"), Integer.valueOf(courseId)));
            }
            cq.where(predicates.toArray(Predicate[]::new));
        }
        return Math.toIntExact(session.createQuery(cq).getSingleResult());
    }

    @Override
    public Subcol getSubcol(Map<String, String> params) {
        if (params != null) {
            Session session = this.factory.getObject().getCurrentSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Subcol> cq = cb.createQuery(Subcol.class);
            Root<Subcol> scRoot = cq.from(Subcol.class);
            List<Predicate> predicates = new ArrayList<>();
            String id = params.get("id");
            String courseId = params.get("courseId");
            String name = params.get("name");
            if (id != null && !id.isEmpty()) {
                predicates.add(cb.equal(scRoot.get("id"), Integer.valueOf(id)));
            }
            else if(courseId != null && !courseId.isEmpty() && name != null && !name.isEmpty()) {
                predicates.add(cb.equal(scRoot.get("courseId").get("id"), Integer.valueOf(courseId)));
                predicates.add(cb.equal(scRoot.get("name"), name));
            }
            cq.where(predicates.toArray(Predicate[]::new));
            return session.createQuery(cq).getSingleResult();
        }
        return null;
    }

    @Override
    public boolean addOrUpdateSubcol(Subcol subcol) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            if (subcol.getId() == null) {
                s.save(subcol);
            } else {
                s.update(subcol);
            }
            return true;
        } catch (HibernateException | AssertionFailure ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override 
    public boolean deleteSubcol(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();
        Subcol s = this.getSubcol(params);
        try {
            session.delete(s);
            return true;
        } catch (HibernateException | AssertionFailure ex) {
            return false;
        }
    }

}
