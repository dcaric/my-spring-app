package com.infybuzz.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author dcaric on 10/04/2022
 * @project spring-boot-app
 */
@Entity
@Table(name="student")
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;

    @JoinColumn(name = "dept_id")
    @ManyToOne
    private Department department;


    public Student(Long id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Student() {

    }

    //@OneToMany(mappedBy = 'student', cascade = CascadeType.ALL)
    //private List<SubjectsLearning> subjectsLearning;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

/*    public List<SubjectsLearning> getSubjectsLearning() {
        return subjectsLearning;
    }

    public void setSubjectsLearning(List<SubjectsLearning> subjectsLearning) {
        this.subjectsLearning = subjectsLearning;
    }*/
}

