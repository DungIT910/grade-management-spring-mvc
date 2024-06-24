/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ttd.repositories.impl;

import com.ttd.pojo.Maingrade;
import com.ttd.repositories.MaingradeRepository;
import javax.persistence.Query;
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
public class MaingradeRepositoryImpl implements MaingradeRepository{
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public Maingrade getMaingradeById(String userId, int courseId) {
         Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM Maingrade mg WHERE mg.userId.id=:userId and mg.courseId.id =:courseId");
        q.setParameter("userId", userId);
        q.setParameter("courseId", courseId);
        return (Maingrade) q.getSingleResult();
    }
    
    
}
