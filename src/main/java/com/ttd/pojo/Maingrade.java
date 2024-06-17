/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ttd.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "maingrade")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Maingrade.findAll", query = "SELECT m FROM Maingrade m"),
    @NamedQuery(name = "Maingrade.findById", query = "SELECT m FROM Maingrade m WHERE m.id = :id"),
    @NamedQuery(name = "Maingrade.findByMidtermGrade", query = "SELECT m FROM Maingrade m WHERE m.midtermGrade = :midtermGrade"),
    @NamedQuery(name = "Maingrade.findByFinalGrade", query = "SELECT m FROM Maingrade m WHERE m.finalGrade = :finalGrade")})
public class Maingrade implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "midterm_grade")
    private BigDecimal midtermGrade;
    @Column(name = "final_grade")
    private BigDecimal finalGrade;
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Course courseId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private User userId;

    public Maingrade() {
    }

    public Maingrade(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getMidtermGrade() {
        return midtermGrade;
    }

    public void setMidtermGrade(BigDecimal midtermGrade) {
        this.midtermGrade = midtermGrade;
    }

    public BigDecimal getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(BigDecimal finalGrade) {
        this.finalGrade = finalGrade;
    }

    public Course getCourseId() {
        return courseId;
    }

    public void setCourseId(Course courseId) {
        this.courseId = courseId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Maingrade)) {
            return false;
        }
        Maingrade other = (Maingrade) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ttd.pojo.Maingrade[ id=" + id + " ]";
    }
    
}
