package com.nerdware.classroomapis.Service;

import com.nerdware.classroomapis.Entity.Subject;
import com.nerdware.classroomapis.Exception.ApiRequestException;
import com.nerdware.classroomapis.Repository.SubjectRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public ResponseEntity<Subject> registerSubject(Subject subject) {
        subjectRepository.save(subject);
        return ResponseEntity.ok(subject);
    }

    public void deleteSubject(Long subjectId) {
        if (!subjectRepository.existsById(subjectId)) {
            throw new ApiRequestException("Subject Not Found");
        }
        subjectRepository.deleteById(subjectId);
    }
}
