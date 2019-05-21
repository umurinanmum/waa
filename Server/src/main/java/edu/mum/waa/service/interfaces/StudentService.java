package edu.mum.waa.service.interfaces;

import edu.mum.waa.entity.Student;
import edu.mum.waa.entity.TmCheckAndRetreat;

import java.util.List;
import java.util.Optional;

import edu.mum.waa.dto.EntryDto;
import edu.mum.waa.dto.StudentDto;

public interface StudentService extends BaseService<StudentDto> {

    StudentDto findStudentByBarcode(String barcode);

    StudentDto findStudentByStudentId(String studentId);


    List<StudentDto> findStudentsByEntry(String entryName);

    List<Student> lookupStudentByStudentId(String studentId);

    List<Student> findAllStudent();
    Student findStudentByStudentId2(String studentId);

}
