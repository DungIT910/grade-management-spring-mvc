package com.ttd.pojo;

import com.ttd.pojo.Class;
import com.ttd.pojo.Subgrade;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-05-20T13:22:32")
@StaticMetamodel(Subcol.class)
public class Subcol_ { 

    public static volatile SingularAttribute<Subcol, Class> classId;
    public static volatile SingularAttribute<Subcol, String> name;
    public static volatile SingularAttribute<Subcol, Integer> id;
    public static volatile CollectionAttribute<Subcol, Subgrade> subgradeCollection;

}