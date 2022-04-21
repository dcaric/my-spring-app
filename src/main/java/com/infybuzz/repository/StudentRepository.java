package com.infybuzz.repository;

import com.infybuzz.entity.Student;
import com.infybuzz.entity.StudentTypes.StudentSimple;
import com.infybuzz.entity.StudentTypes.StudentSimpleClass;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dcaric on 10/04/2022
 * @project spring-boot-app
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query(value = "SELECT * FROM student", nativeQuery = true)
    public List<Student> findAllNative();

    @Query("SELECT t.firstName FROM Student t WHERE t.firstName LIKE %?1%")
    public List<String> findByNameLike(String firstName);

    @Query("SELECT t FROM Student t WHERE t.firstName LIKE %?1%")
    //@Query(value = 'SELECT first_name FROM student', nativeQuery = true)
    public List<StudentSimple> findByNameLike2(String firstName);

}
