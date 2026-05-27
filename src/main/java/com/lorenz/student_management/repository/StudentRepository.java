package com.lorenz.student_management.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.lorenz.student_management.model.Student;





@Repository 
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByEmail(String email);

    List<Student> findAllByDeletedAtIsNull();

    Optional<Student> findByIdAndDeletedAtIsNull(Long id);
}