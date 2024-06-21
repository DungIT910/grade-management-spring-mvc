/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ttd.repositories.impl;

import com.ttd.dto.GradeDetail;
import com.ttd.dto.PaginationResult;
import com.ttd.pojo.Course;
import com.ttd.pojo.Maingrade;
import com.ttd.pojo.User;
import com.ttd.repositories.CourseRepository;
import com.ttd.repositories.StudentRepository;
import com.ttd.repositories.UserRepository;
import com.ttd.services.UserService;
import com.ttd.utils.PaginationHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
public class StudentRepositoryImpl implements StudentRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private Environment env;

    @Override
    public PaginationResult<User> getStudentsByCourseId(int courseId, Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<User> cq = b.createQuery(User.class);
        Root<User> rootU = cq.from(User.class);
        Root<Maingrade> rootM = cq.from(Maingrade.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.and(
                b.equal(rootM.get("userId").get("id"), rootU.get("id")),
                b.equal(rootM.get("courseId").get("id"), courseId))
        );
        cq.select(rootU);
        cq.where(predicates.toArray(Predicate[]::new));
        int count = this.countStudentsByCourseId(courseId);
        Query q = s.createQuery(cq);
        int dfpagesize = Integer.parseInt(env.getProperty("PAGE_SIZE"));
        return PaginationHelper.paginate(q, params, count, dfpagesize);
    }

    @Override
    public int countStudentsByCourseId(int courseId) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT Count(*) FROM Maingrade m WHERE m.courseId.id=:courseid");
        q.setParameter("courseid", courseId);
        return Integer.parseInt(q.getSingleResult().toString());
    }

    @Override
    public boolean removeStudentFromCourse(int courseId, String studentID) {
        Session session = this.factory.getObject().getCurrentSession();
        Query q = session.createQuery("delete from Maingrade m where m.userId.id =:userId and m.courseId.id=:courseId");
        q.setParameter("userId", studentID);
        q.setParameter("courseId", courseId);
        try {
            q.executeUpdate();
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean addStudentToCourse(int courseId, String studentId) {
        Session session = this.factory.getObject().getCurrentSession();
        Query q = session.createQuery("insert into Maingrade(courseId, userId) " +
                                   "select c, u from Course c, User u " +
                                   "where c.id = :courseId and u.id = :userId");
        q.setParameter("userId", studentId);
        q.setParameter("courseId", courseId);
        try {
            q.executeUpdate();
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
