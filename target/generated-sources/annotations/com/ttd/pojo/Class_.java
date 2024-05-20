package com.ttd.pojo;

import com.ttd.pojo.Maingrade;
import com.ttd.pojo.Subcol;
import com.ttd.pojo.Subject;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-05-20T13:22:32")
@StaticMetamodel(Class.class)
public class Class_ { 

    public static volatile CollectionAttribute<Class, Subcol> subcolCollection;
    public static volatile SingularAttribute<Class, String> name;
    public static volatile SingularAttribute<Class, Integer> id;
    public static volatile SingularAttribute<Class, Subject> subjectId;
    public static volatile CollectionAttribute<Class, Maingrade> maingradeCollection;

}