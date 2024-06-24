/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ttd.repositories.impl;

import com.ttd.dto.GradeDetail;
import com.ttd.dto.PaginationResult;
import com.ttd.pojo.Course;
import com.ttd.pojo.Maingrade;
import com.ttd.pojo.Subcol;
import com.ttd.pojo.Subgrade;
import com.ttd.pojo.User;
import com.ttd.repositories.GradeRepository;
import com.ttd.services.CourseService;
import com.ttd.services.MaingradeService;
import com.ttd.services.StudentService;
import com.ttd.services.SubcolService;
import com.ttd.services.SubgradeService;
import com.ttd.services.UserService;
import com.ttd.utils.PaginationHelper;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.transform.AliasToBeanResultTransformer;
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
public class GradeRepositoryImpl implements GradeRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private StudentService studentService;
    @Autowired
    private UserService userService;
    @Autowired
    private MaingradeService maingradeService;
    @Autowired
    private SubcolService subcolService;
    @Autowired
    private SubgradeService subgradeService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private Environment env;

    @Override
    public PaginationResult<GradeDetail> getGrades(Map<String, String> params) {
        int courseId = Integer.parseInt(params.get("courseId"));
        Session session = factory.getObject().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();

        int count = this.studentService.countStudentsByCourseId(courseId);

        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<Maingrade> mgRoot = cq.from(Maingrade.class);
        Join<Maingrade, User> userJoin = mgRoot.join("userId");
        cq.multiselect(
                userJoin,
                mgRoot.get("midtermGrade"),
                mgRoot.get("finalGrade")
        ).where(cb.equal(mgRoot.get("courseId").get("id"), courseId));

        int page = params.containsKey("page") ? Integer.parseInt(params.get("page")) : 1;
        int limit = params.containsKey("limit") ? Integer.parseInt(params.get("limit")) : count;

        List<Tuple> mainResult = session.createQuery(cq)
                .setFirstResult((page - 1) * limit)
                .setMaxResults(limit)
                .getResultList();

        List<GradeDetail> gradeDetails = new ArrayList<>();
        for (Tuple tuple : mainResult) {
            User user = tuple.get(0, User.class);
            BigDecimal midtermGrade = tuple.get(1, BigDecimal.class);
            BigDecimal finalGrade = tuple.get(2, BigDecimal.class);

            Query q = session.createQuery(
                    "select mg.userId as mssv, sc.name as name, sg.value as value\n"
                    + "from Maingrade mg\n"
                    + "left join Subcol sc on sc.courseId = mg.courseId\n"
                    + "left join Subgrade sg on (sg.subcolId = sc.id and sg.userId = mg.userId) "
                    + "where mg.userId.id =:userId and mg.courseId.id =:courseId ", Tuple.class);
            q.setParameter("userId", user.getId());
            q.setParameter("courseId", courseId);
            List<Tuple> subResult = q.getResultList();
            Map<String, BigDecimal> subgrades = new HashMap<>();
            for (Tuple subTuple : subResult) {
                String subcolName = subTuple.get("name", String.class);
                BigDecimal value = subTuple.get("value", BigDecimal.class);
                subgrades.put(subcolName, value);
            }

            GradeDetail gradeDetail = new GradeDetail();
            gradeDetail.setUser(user);
            gradeDetail.setMidtermGrade(midtermGrade);
            gradeDetail.setFinalGrade(finalGrade);
            gradeDetail.setSubgrades(subgrades);

            gradeDetails.add(gradeDetail);
        }

        PaginationResult<GradeDetail> paginationResult = new PaginationResult<>();
        paginationResult.setData(gradeDetails);
        paginationResult.setTotalPage((int) Math.ceil(count * 1.0 / limit));
        paginationResult.setPage(page);

        return paginationResult;
    }

    @Override
    public GradeDetail updateGrades(GradeDetail gd, int courseId) {
        Session session = factory.getObject().getCurrentSession();
        String userId = gd.getUser().getId();
        Map<String, BigDecimal> subgrades = gd.getSubgrades();
        Maingrade mg = this.maingradeService.getMaingradeById(userId, courseId);
        mg.setMidtermGrade(gd.getMidtermGrade());
        mg.setFinalGrade(gd.getFinalGrade());
        for (Map.Entry<String, BigDecimal> subgrade : subgrades.entrySet()) {
            Map<String, String> params = new HashMap<>();
            params.put("name", subgrade.getKey());
            params.put("courseId", String.valueOf(courseId));
            Subcol sc = subcolService.getSubcol(params);
            if (this.subgradeService.isExistSubgrade(sc.getId(), userId)) {
                Subgrade sg = subgradeService.getSubgradeById(sc.getId(), userId);
                sg.setValue(subgrade.getValue());
            } else {
                Subgrade sg = new Subgrade();
                sg.setSubcolId(sc);
                sg.setUserId(this.userService.getUserById(userId));
                sg.setValue(subgrade.getValue());
                session.save(sg);
            }
        }
        return gd;
    }

    @Override
    public GradeDetail getStudentgrade(String userId, int courseId) {
        Session session = factory.getObject().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        Query q = session.createQuery(
                "select mg.userId as mssv,mg.midtermGrade as midtermGrade , mg.finalGrade as finalGrade, sc.name as name, sg.value as value\n"
                + "from Maingrade mg\n"
                + "left join Subcol sc on sc.courseId = mg.courseId\n"
                + "left join Subgrade sg on (sg.subcolId = sc.id and sg.userId = mg.userId) "
                + "where mg.userId.id =:userId and mg.courseId.id =:courseId ", Tuple.class);
        q.setParameter("userId", userId);
        q.setParameter("courseId", courseId);
        List<Tuple> result = q.getResultList();
        GradeDetail gradeDetail = new GradeDetail();
        Map<String, BigDecimal> subgrades = new HashMap<>();
        for (Tuple subTuple : result) {
            String subcolName = subTuple.get("name", String.class);
            BigDecimal value = subTuple.get("value", BigDecimal.class);
            subgrades.put(subcolName, value);
            gradeDetail.setMidtermGrade(subTuple.get("midtermGrade", BigDecimal.class));
            gradeDetail.setFinalGrade(subTuple.get("finalGrade", BigDecimal.class));
        }
        gradeDetail.setCourse(this.courseService.getCourseById(courseId));
        gradeDetail.setUser(this.userService.getUserById(userId));
        gradeDetail.setSubgrades(subgrades);
        return gradeDetail;
    }

}
