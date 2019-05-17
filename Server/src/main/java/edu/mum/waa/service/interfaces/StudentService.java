package edu.mum.waa.service.interfaces;

import edu.mum.waa.dto.EntryDto;
import edu.mum.waa.dto.StudentDto;

import java.util.List;

public interface StudentService extends BaseService<StudentDto> {

    StudentDto findStudentByBarcode(String barcode);

    StudentDto findStudentByStudentId(String studentId);


    List<StudentDto> findStudentsByEntry(String entryName);


}
