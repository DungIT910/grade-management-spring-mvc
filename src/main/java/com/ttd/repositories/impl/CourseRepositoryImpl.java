
package com.ttd.repositories.impl;

import com.ttd.dto.PaginationResult;
import com.ttd.pojo.Course;
import com.ttd.pojo.Maingrade;
import com.ttd.pojo.Subject;
import com.ttd.pojo.User;
import com.ttd.repositories.CourseRepository;
import com.ttd.services.UserService;
import com.ttd.utils.PaginationHelper;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
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
public class CourseRepositoryImpl implements CourseRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Autowired
    private UserService userService;

    @Autowired
    private Environment env;

    @Override
    public Course getCourseById(int courseId) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM Course WHERE id=:courseid");
        q.setParameter("courseid", courseId);
        return (Course) q.getSingleResult();
    }

    @Override
    public PaginationResult<Course> getCourses(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Course> cq = b.createQuery(Course.class);
        Root<Course> rootC = cq.from(Course.class);
        List<Predicate> predicates = new ArrayList<>();
        cq.select(rootC);
        Map<String, String> subparams = new HashMap<>();

        String userId = params.get("userId");
        if (userId != null && !userId.isEmpty()) {
            subparams.put("userId", userId);
            User u = this.userService.getUserById(userId);
            Predicate predicate = b.equal(rootC.get("lecturerId").get("id"), userId);
            if (u.getUserRole().equals(User.ROLE_STUDENT)) {
                Root<Maingrade> rootM = cq.from(Maingrade.class);
                predicate = b.and(
                        b.equal(rootC.get("id"), rootM.get("courseId").get("id")),
                        b.equal(rootM.get("userId").get("id"), userId)
                );
            }
            predicates.add(predicate);
        }

        cq.where(predicates.toArray(Predicate[]::new));
        Query q = s.createQuery(cq);
        int count = this.countCourses(subparams);
        int dfpagesize = Integer.parseInt(env.getProperty("PAGE_SIZE"));
        return PaginationHelper.paginate(q, params, count, dfpagesize);
    }

    @Override
    public int countCourses(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        String userId = params.get("userId");
        if (userId != null && !userId.isEmpty()) {
            User u = this.userService.getUserById(userId);
            if (u.getUserRole().equals(User.ROLE_LECTURER)) {
                return Integer.parseInt(s.createQuery("select Count(*) from Course c WHERE c.lecturerId.id =:userid")
                        .setParameter("userid", userId).getSingleResult().toString());
            }
            return Integer.parseInt(s.createQuery("select Count(*) from Maingrade m WHERE m.userId.id =:userid")
                    .setParameter("userid", userId).getSingleResult().toString());
        } else {
            return Integer.parseInt(s.createQuery("select Count(*) from Course c")
                    .getSingleResult().toString());
        }
    }

    @Override
    public boolean addOrUpdateCourse(Course course) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            if (course.getId() == null) {
                s.save(course);
            } else {
                s.update(course);
            }

            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteCourse(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        Course c = this.getCourseById(id);
        try {
            session.delete(c);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
