package com.infybuzz.entity;

import javax.persistence.*;
import java.util.List;

/**
 * @author dcaric on 11/04/2022
 * @project spring-data-res
 */
@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dept_name")
    public String deptName;


    //@OneToMany(mappedBy = 'department', cascade = CascadeType.ALL)
    //private List<Student> students;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

/*
    public List<Student> getStudents() {
    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
 */
}
