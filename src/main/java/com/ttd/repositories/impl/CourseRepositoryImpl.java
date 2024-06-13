/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ttd.repositories.impl;

import com.ttd.dto.PaginationResult;
import com.ttd.pojo.Course;
import com.ttd.pojo.Maingrade;
import com.ttd.pojo.User;
import com.ttd.repositories.CourseRepository;
import com.ttd.services.UserService;
import com.ttd.utils.PaginationHelper;
import java.util.List;
import java.util.Map;
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
public class CourseRepositoryImpl implements CourseRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Autowired
    private UserService userService;

    @Override
    public Course getCourseById(int courseId) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM Course WHERE id=:courseid");
        q.setParameter("userid", courseId);
        return (Course) q.getSingleResult();
    }

    @Override
    public PaginationResult<Course> getCoursesByUserId(String userId, Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        User u = this.userService.getUserById(userId);

        CriteriaQuery<Course> cq = b.createQuery(Course.class);

        Root<Course> rootC = cq.from(Course.class);
        Predicate predicate = b.equal(rootC.get("lecturerId").get("id"), userId);
        if (u.getUserRole().equals(User.ROLE_STUDENT)) {
            Root<Maingrade> rootM = cq.from(Maingrade.class);
            predicate = b.and(
                    b.equal(rootC.get("id"), rootM.get("courseId").get("id")),
                    b.equal(rootM.get("userId").get("id"), userId)
            );
        }
        cq.select(rootC).where(predicate);
        Query q = s.createQuery(cq);
        int count = this.countCoursesByUserId(userId);
        return PaginationHelper.paginate(q, params, count);
    }

    @Override
    public int countCoursesByUserId(String userId) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        User u = this.userService.getUserById(userId);
        if (u.getUserRole().equals(User.ROLE_LECTURER)) {
            return Integer.parseInt(s.createQuery("select Count(*) from Course c WHERE c.lecturerId.id =:userid")
                    .setParameter("userid", userId).getSingleResult().toString());
        }
        return Integer.parseInt(s.createQuery("select Count(*) from Maingrade m WHERE m.userId.id =:userid")
                    .setParameter("userid", userId).getSingleResult().toString());
    }

}
