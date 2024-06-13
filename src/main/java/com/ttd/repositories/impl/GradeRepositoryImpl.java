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
import com.ttd.services.StudentService;
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
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.transform.AliasToBeanResultTransformer;
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
    @Autowired
    private StudentService studentService;

    @Override
    public PaginationResult<GradeDetail> getGradesByCourseId(int courseId, Map<String, String> params) {
        Session session = factory.getObject().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();

       int count = this.studentService.countStudentsByCourseId(courseId);

        // Truy vấn chính để lấy dữ liệu phân trang
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<Maingrade> mgRoot = cq.from(Maingrade.class);
        Join<Maingrade, User> userJoin = mgRoot.join("userId");

        cq.multiselect(
                userJoin,
                mgRoot.get("midtermGrade"),
                mgRoot.get("finalGrade")
        ).where(cb.equal(mgRoot.get("courseId").get("id"), courseId));

        // Lấy kết quả phân trang
        int page = params.containsKey("page") ? Integer.parseInt(params.get("page")) : 1;
        int limit = params.containsKey("limit") ? Integer.parseInt(params.get("limit")) : count;
 
        List<Tuple> mainResult = session.createQuery(cq)
                .setFirstResult((page - 1) * limit)
                .setMaxResults(limit)
                .getResultList();

        // Chuẩn bị dữ liệu cho trang phân trang
        List<GradeDetail> gradeDetails = new ArrayList<>();
        for (Tuple tuple : mainResult) {
            User user = tuple.get(0, User.class);
            BigDecimal midtermGrade = tuple.get(1, BigDecimal.class);
            BigDecimal finalGrade = tuple.get(2, BigDecimal.class);

            // Truy vấn phụ để lấy điểm phụ cho user hiện tại
            CriteriaQuery<Tuple> subCq = cb.createTupleQuery();
            Root<Subgrade> sgRoot = subCq.from(Subgrade.class);
            Join<Subgrade, Subcol> subcolJoin = sgRoot.join("subcolId");

            subCq.multiselect(
                    subcolJoin.get("name"),
                    sgRoot.get("value")
            ).where(
                    cb.equal(sgRoot.get("userId").get("id"), user.getId()),
                    cb.equal(subcolJoin.get("courseId").get("id"), courseId)
            );

            List<Tuple> subResult = session.createQuery(subCq).getResultList();
            Map<String, BigDecimal> subgrades = new HashMap<>();
            for (Tuple subTuple : subResult) {
                String subcolName = subTuple.get(0, String.class);
                BigDecimal value = subTuple.get(1, BigDecimal.class);
                subgrades.put(subcolName, value);
            }

            GradeDetail gradeDetail = new GradeDetail();
            gradeDetail.setUser(user);
            gradeDetail.setMidtermGrade(midtermGrade);
            gradeDetail.setFinalGrade(finalGrade);
            gradeDetail.setSubgrades(subgrades);

            gradeDetails.add(gradeDetail);
        }
        // Tạo PaginationResult và trả về
        PaginationResult<GradeDetail> paginationResult = new PaginationResult<>();
        paginationResult.setData(gradeDetails);
        paginationResult.setTotalPage((int) Math.ceil(count * 1.0 / limit));
        paginationResult.setPage(page);

        return paginationResult;
    }

}
