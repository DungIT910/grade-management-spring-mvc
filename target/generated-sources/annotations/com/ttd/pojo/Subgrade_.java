package com.ttd.pojo;

import com.ttd.pojo.Subcol;
import com.ttd.pojo.User;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-06-17T12:52:01")
@StaticMetamodel(Subgrade.class)
public class Subgrade_ { 

    public static volatile SingularAttribute<Subgrade, Subcol> subcolId;
    public static volatile SingularAttribute<Subgrade, Integer> id;
    public static volatile SingularAttribute<Subgrade, BigDecimal> value;
    public static volatile SingularAttribute<Subgrade, User> userId;

}