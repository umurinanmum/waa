package edu.mum.waa.repository;

import edu.mum.waa.entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentRepo extends CrudRepository<Student,Long> {

    Optional<Student> findStudentByStudentId(String studentId);

    Optional<Student> findStudentByBarcode(String barcode);

    //@Query(value = "select s from Student s where s.studentId like :studentId%")  WHERE s.student_id LIKE :studentId
    @Query(value = "SELECT s.id, s.first_name, s.last_name, s.barcode, s.student_id" +
            " FROM student s  WHERE s.student_id LIKE :studentId%"
            , nativeQuery = true)
    List<Object[]> lookupStudentByStudentId(@Param("studentId") String studentId);

    List<Student> findStudentsByEntryName(String entryName);

}
