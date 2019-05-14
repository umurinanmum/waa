package edu.mum.waa.repository;

import edu.mum.waa.entity.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StudentRepo extends CrudRepository<Student,Long> {

    Optional<Student> findStudentByBarcode(String barcode);

}
