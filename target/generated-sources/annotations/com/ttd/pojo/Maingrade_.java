package com.ttd.pojo;

import com.ttd.pojo.Class;
import com.ttd.pojo.User;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-05-26T20:11:00")
@StaticMetamodel(Maingrade.class)
public class Maingrade_ { 

    public static volatile SingularAttribute<Maingrade, Class> classId;
    public static volatile SingularAttribute<Maingrade, BigDecimal> finalGrade;
    public static volatile SingularAttribute<Maingrade, Integer> id;
    public static volatile SingularAttribute<Maingrade, BigDecimal> midtermGrade;
    public static volatile SingularAttribute<Maingrade, User> userId;

}