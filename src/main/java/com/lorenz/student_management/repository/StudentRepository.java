package com.lorenz.student_management.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.lorenz.student_management.model.Student;





@Repository 
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByEmail(String email);

    List<Student> findAllByDeletedAtIsNull();

    Optional<Student> findByIdAndDeletedAtIsNull(Long id);

    @Query("""
        SELECT s FROM Student s
        WHERE s.deletedAt IS NULL AND (
            LOWER(s.firstName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR
            LOWER(s.lastName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR
            LOWER(s.email) LIKE LOWER(CONCAT('%', :keyword, '%')) OR
            CAST(s.id AS string) LIKE CONCAT('%', :keyword, '%')
        )
    """)
    List<Student> searchStudent(@Param("keyword") String keyword);
}