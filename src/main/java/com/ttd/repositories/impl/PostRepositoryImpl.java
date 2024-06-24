/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ttd.repositories.impl;

import com.ttd.dto.PaginationResult;
import com.ttd.pojo.Post;
import com.ttd.repositories.PostRepository;
import com.ttd.utils.PaginationHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.ejb.TransactionAttribute;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author DELL
 */
@Transactional
@Repository
public class PostRepositoryImpl implements PostRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private Environment env;

    @Override
    public PaginationResult<Post> getPosts(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Post> cq = cb.createQuery(Post.class);
        Root root = cq.from(Post.class);
        cq.select(root);
        List<Predicate> predicates = new ArrayList<>();
        String parentId = params.get("parentId");
        int count;
        int forumId = Integer.parseInt(params.get("forumId"));
        predicates.add(cb.equal(root.get("forumId").get("id"), forumId));
        if (parentId != null) {
            predicates.add(cb.or(cb.equal(root.get("id"), Integer.parseInt(parentId)),
                    cb.equal(root.get("parentId").get("id"), Integer.parseInt(parentId))));
            count = this.countPosts(Integer.valueOf(parentId), forumId);
        } else {
            predicates.add(cb.isNull(root.get("parentId").get("id")));
            count = this.countPosts(null, forumId);
        }
        cq.where(predicates.toArray(Predicate[]::new));

        Query q = session.createQuery(cq);
        int dfpagesize = Integer.parseInt(env.getProperty("PAGE_SIZE"));
        int page = params.containsKey("page") ? Integer.parseInt(params.get("page")) : 1;
        int limit = params.containsKey("limit") ? Integer.parseInt(params.get("limit")) : dfpagesize;
        int totalPage = (int) Math.ceil((double) count / limit);

        List<Post> data = q
                .setFirstResult((page - 1) * limit)
                .setMaxResults(limit)
                .getResultList();

//        Iterator<Post> iterator = data.iterator();
//        while (iterator.hasNext()) {
//            Post post = iterator.next();
//            if (post.getParentId() == null) {
//                iterator.remove(); 
//            }
//        }
        PaginationResult<Post> result = new PaginationResult<>();
        result.setData(data);
        result.setTotalPage(totalPage);
        result.setPage(page);

        return result;
    }

    @Override
    public int countPosts(Integer parentId, int forumId) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q;
        if (parentId != null) {
            q = s.createQuery("SELECT Count(*) FROM Post p WHERE p.parentId.id =:parentId and p.forumId.id =:forumId");
            q.setParameter("parentId", parentId);
        } else {
            q = s.createQuery("SELECT Count(*) FROM Post p WHERE p.parentId.id is null and p.forumId.id =:forumId");
        }
        q.setParameter("forumId", forumId);
        return Integer.parseInt(q.getSingleResult().toString());
    }

    @Override
    public Post addOrUpdatePost(Post post) {
        Post hello = post;
        Session s = this.factory.getObject().getCurrentSession();
        try {
            if (post.getId() == null) {
                s.save(post);
            } else {
                s.update(post);
            }
            return post;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Post getPostById(int postId) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM Post WHERE id=:postId");
        q.setParameter("postId", postId);
        return (Post) q.getSingleResult();
    }

    @Override
    public boolean deletePost(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        Post p = this.getPostById(id);
        try {
            session.delete(p);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
