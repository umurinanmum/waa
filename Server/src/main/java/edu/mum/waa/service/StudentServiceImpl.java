package edu.mum.waa.service;

import edu.mum.waa.entity.Student;
import edu.mum.waa.entity.TmCheckAndRetreat;
import edu.mum.waa.repository.StudentRepo;
import edu.mum.waa.repository.TMCheckAndRetreatRepo;
import edu.mum.waa.service.interfaces.StudentService;
import edu.mum.waa.service.interfaces.TmCheckAndRetreatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepo studentRepo;

    public List<Student> lookupStudentByStudentId(String studentId) {
        return studentRepo.lookupStudentByStudentId(studentId);
    }


}
