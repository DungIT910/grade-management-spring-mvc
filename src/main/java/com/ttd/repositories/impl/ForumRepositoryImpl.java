
package com.ttd.repositories.impl;

import com.ttd.dto.PaginationResult;
import com.ttd.pojo.Forum;
import com.ttd.pojo.Maingrade;
import com.ttd.pojo.Subject;
import com.ttd.pojo.Course;
import com.ttd.repositories.ForumRepository;
import com.ttd.services.CourseService;
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
public class ForumRepositoryImpl implements ForumRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Autowired
    private CourseService courseService;

    @Autowired
    private Environment env;

    @Override
    public Forum getForumById(int forumId) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM Forum WHERE id=:forumid");
        q.setParameter("forumid", forumId);
        return (Forum) q.getSingleResult();
    }

    @Override
    public PaginationResult<Forum> getForums(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Forum> cq = b.createQuery(Forum.class);
        Root<Forum> rootF = cq.from(Forum.class);
        List<Predicate> predicates = new ArrayList<>();
        cq.select(rootF);
        Map<String, String> subparams = new HashMap<>();

        String courseId = params.get("courseId");
        if (courseId != null && !courseId.isEmpty()) {
            subparams.put("courseId", courseId);
            Course u = this.courseService.getCourseById(Integer.parseInt(courseId));
            Predicate predicate = b.equal(rootF.get("courseId").get("id"), courseId);
            predicates.add(predicate);
        }

        cq.where(predicates.toArray(Predicate[]::new));
        Query q = s.createQuery(cq);
        int count = this.countForums(subparams);
        int dfpagesize = Integer.parseInt(env.getProperty("PAGE_SIZE"));
        return PaginationHelper.paginate(q, params, count, dfpagesize);
    }

    @Override
    public int countForums(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        String courseId = params.get("courseId");
        if (courseId != null && !courseId.isEmpty()) {
                return Integer.parseInt(s.createQuery("select Count(*) from Forum f WHERE f.courseId.id =:courseid")
                        .setParameter("courseid",Integer.parseInt(courseId)).getSingleResult().toString());
        } else {
            return Integer.parseInt(s.createQuery("select Count(*) from Forum f")
                    .getSingleResult().toString());
        }
    }

    @Override
    public boolean addOrUpdateForum(Forum forum) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            if (forum.getId() == null) {
                s.save(forum);
            } else {
                s.update(forum);
            }

            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteForum(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        Forum f = this.getForumById(id);
        try {
            session.delete(f);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
