package edu.mum.waa.service.interfaces;

import edu.mum.waa.dto.StudentDto;

public interface StudentService extends BaseService<StudentDto> {

    StudentDto findStudentByBarcode(String barcode);

    StudentDto findStudentByStudentId(String studentId);


}
