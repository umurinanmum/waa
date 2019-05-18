package edu.mum.waa.service.interfaces;

import edu.mum.waa.entity.Student;
import edu.mum.waa.entity.TmCheckAndRetreat;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    List<Student> lookupStudentByStudentId(String studentId);


}
