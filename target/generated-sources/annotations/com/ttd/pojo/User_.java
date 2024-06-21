package com.ttd.pojo;

import com.ttd.pojo.Course;
import com.ttd.pojo.Maingrade;
import com.ttd.pojo.Post;
import com.ttd.pojo.Subgrade;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-06-22T03:07:56")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> lastName;
    public static volatile SingularAttribute<User, Boolean> active;
    public static volatile SingularAttribute<User, String> avatar;
    public static volatile SetAttribute<User, Post> postSet;
    public static volatile SetAttribute<User, Maingrade> maingradeSet;
    public static volatile SingularAttribute<User, String> firstName;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SetAttribute<User, Course> courseSet;
    public static volatile SingularAttribute<User, String> id;
    public static volatile SingularAttribute<User, String> userRole;
    public static volatile SingularAttribute<User, String> email;
    public static volatile SetAttribute<User, Subgrade> subgradeSet;
    public static volatile SingularAttribute<User, String> username;

}