package com.nerdware.classroomapis.Repository;


import com.nerdware.classroomapis.Entity.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Long> {

}