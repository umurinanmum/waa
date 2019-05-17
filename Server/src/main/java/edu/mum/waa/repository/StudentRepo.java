package edu.mum.waa.repository;

import edu.mum.waa.entity.Block;
import edu.mum.waa.entity.Entry;
import edu.mum.waa.entity.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepo extends CrudRepository<Student,Long> {

    Optional<Student> findStudentByStudentId(String studentId);


    Optional<Student> findStudentByBarcode(String barcode);

    List<Student> findStudentsByEntryName(String entryName);


}
