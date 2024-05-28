/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ttd.repositories.impl;

import com.ttd.pojo.Course;
import com.ttd.pojo.Maingrade;
import com.ttd.repositories.GradeRepository;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;
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
public class GradeRepositoryImpl implements GradeRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Object> getGradesByCourseId(int courseId) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);

        Root<Course> rootC = q.from(Course.class);
        Root<Maingrade> rootM = q.from(Maingrade.class);
//        Predicate predicate = b.and(
//                b.equal(rootC.get("id"), rootM.get("courseId").get("id")),
//                b.equal(rootM.get("userId").get("id"), "205406")
//        );
        q.multiselect(rootC.get("id"), rootM.get("userId"));
//        q.where(predicate);
        Query q1 = s.createQuery(q);
        return q1.getResultList();
    }

}
