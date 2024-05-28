package com.ttd.pojo;

import com.ttd.pojo.Course;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-05-28T23:27:58")
@StaticMetamodel(Subject.class)
public class Subject_ { 

    public static volatile SingularAttribute<Subject, String> subjectname;
    public static volatile SetAttribute<Subject, Course> courseSet;
    public static volatile SingularAttribute<Subject, Integer> id;

}