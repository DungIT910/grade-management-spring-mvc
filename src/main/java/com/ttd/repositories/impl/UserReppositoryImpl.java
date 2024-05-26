/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ttd.repositories.impl;

import com.ttd.pojo.User;
import com.ttd.repositories.UserRepository;
import java.util.List;
import java.util.Map;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author admin
 */
@Repository
@Transactional
public class UserReppositoryImpl implements UserRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private BCryptPasswordEncoder passEncoder;

    @Override
    public User getUserByUsername(String username) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM User WHERE username=:un");
        q.setParameter("un", username);

        return (User) q.getSingleResult();
    }

    @Override
    public User getUserById(String userId) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM User WHERE id=:userid");
        q.setParameter("userid", userId);

        return (User) q.getSingleResult();
    }

    @Override
    public boolean authUser(String username, String password) {
        User u = this.getUserByUsername(username);

        return this.passEncoder.matches(password, u.getPassword());
    }

    @Override
    public boolean addUser(User user) {
        Session s = this.factory.getObject().getCurrentSession();

        try {
            if (!user.getDeletedId().equals(user.getId())) {
                if (!user.getDeletedId().equals(null)) {
                    deleteUserById(user.getDeletedId());
                }
                s.save(user);
            } else {
                s.update(user);
            }
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        } catch (NoResultException ex2) {
            return false;
        }
    }

    @Override
    public List<User> getUsersByRole(String role) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<User> q = b.createQuery(User.class);
        Root root = q.from(User.class);
        q.select(root);
        Predicate p = b.equal(root.get("userRole"), role);
        q.where(p);
        q.orderBy(b.asc(root.get("id")));
        Query query = s.createQuery(q);
        return query.getResultList();
    }

    @Override
    public boolean deleteUser(User user) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.delete(user);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean changeStatus(String userId) {
        Session s = this.factory.getObject().getCurrentSession();
        User user = this.getUserById(userId);
        try {
            if (user.getActive()) {
                user.setActive(Boolean.FALSE);
            } else {
                user.setActive(Boolean.TRUE);
            }
            s.update(user);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteUserById(String userId) {
        Session s = this.factory.getObject().getCurrentSession();
        User user = this.getUserById(userId);
        try {
            s.delete(user);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean isExistedUserId(String userId) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("select 1 from User where id=:userid");
        q.setParameter("userid", userId);
        try {
            q.getSingleResult();
        } catch (NoResultException e) {
            return false;
        }
        return true;
    }
}
