package edu.mum.waa.repository;

import edu.mum.waa.entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepo extends CrudRepository<Student,Long> {

    Optional<Student> findStudentByStudentId(String studentId);

    Optional<Student> findStudentByBarcode(String barcode);

    @Query(value = "select s from Student s where s.studentId like :studentId%")
    List<Student> lookupStudentByStudentId(String studentId);

    List<Student> findStudentsByEntryName(String entryName);

}
