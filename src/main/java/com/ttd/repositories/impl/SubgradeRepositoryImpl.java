/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ttd.repositories.impl;

import com.ttd.pojo.Maingrade;
import com.ttd.pojo.Subgrade;
import com.ttd.repositories.SubgradeRepository;
import com.ttd.services.SubcolService;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author DELL
 */
@Repository
@Transactional
public class SubgradeRepositoryImpl implements SubgradeRepository {

    @Autowired
    private SubcolService subcolService;
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public Subgrade getSubgradeById(int subcolId, String userId) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM Subgrade sg WHERE sg.userId.id=:userId and sg.subcolId.id =:subcolId");
        q.setParameter("userId", userId);
        q.setParameter("subcolId", subcolId);
        return (Subgrade) q.getSingleResult();
    }

    @Override
    public boolean isExistSubgrade(int subcolId, String userId) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("select 1 from Subgrade sg where sg.userId.id=:userId and sg.subcolId.id=:subcolId");
        q.setParameter("userId", userId);
        q.setParameter("subcolId", subcolId);
        try {
            q.getSingleResult();
        } catch (NoResultException e) {
            return false;
        }
        return true;
    }

}
