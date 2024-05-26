package com.ttd.pojo;

import com.ttd.pojo.Class;
import com.ttd.pojo.Subgrade;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-05-26T20:11:00")
@StaticMetamodel(Subcol.class)
public class Subcol_ { 

    public static volatile SingularAttribute<Subcol, Class> classId;
    public static volatile SingularAttribute<Subcol, String> name;
    public static volatile SingularAttribute<Subcol, Integer> id;
    public static volatile SetAttribute<Subcol, Subgrade> subgradeSet;

}