package com.nerdware.classroomapis.Repository;


import com.nerdware.classroomapis.Entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Teacher findByTeacherUsername(String username);
}
