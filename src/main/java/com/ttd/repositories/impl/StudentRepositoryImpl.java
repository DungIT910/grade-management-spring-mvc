/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ttd.repositories.impl;

import com.ttd.pojo.Course;
import com.ttd.pojo.Maingrade;
import com.ttd.pojo.User;
import com.ttd.repositories.CourseRepository;
import com.ttd.repositories.StudentRepository;
import com.ttd.repositories.UserRepository;
import com.ttd.services.UserService;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
public class StudentRepositoryImpl implements StudentRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<User> getUsersByCourseId(int courseId) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<User> query = b.createQuery(User.class);
        Root<User> rootU = query.from(User.class);
        Root<Maingrade> rootM = query.from(Maingrade.class);

        Predicate p = b.and(
                b.equal(rootM.get("userId").get("id"), rootU.get("id")),
                b.equal(rootM.get("courseId").get("id"), courseId)
        );
        query.select(rootU).where(p);
        List<User> listUser = s.createQuery(query).getResultList();
        return listUser;
    }

}
