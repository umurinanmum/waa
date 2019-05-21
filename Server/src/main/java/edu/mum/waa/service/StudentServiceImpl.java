package edu.mum.waa.service;

import edu.mum.waa.dto.StudentDtoForCrud;
import edu.mum.waa.entity.Student;
import edu.mum.waa.repository.StudentRepo;
import edu.mum.waa.service.interfaces.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import edu.mum.waa.dto.StudentDto;
import lombok.var;

import java.util.ArrayList;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepo studentRepo;

    public List<Student> lookupStudentByStudentId(String studentId) {
        List<Student> result = new ArrayList<>();

        List<Object[]> list = studentRepo.lookupStudentByStudentId(studentId);
        //s.id, s.first_name, s.last_name, s.barcode, s.student_id
        for (Object[] obj : list) {
            Student item = new Student();
            item.setId(((BigInteger) obj[0]).longValue());
            item.setFirstName((String) obj[1]);
            item.setLastName((String) obj[2]);
            item.setBarcode((String) obj[3]);
            item.setStudentId((String) obj[4]);
            result.add(item);
        }
        return result; //studentRepo.lookupStudentByStudentId(studentId);
    }

    @Autowired
    public StudentServiceImpl(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }


    @Override
    public List<StudentDto> findStudentsByEntry(String entryName) {
        StudentDto temp = new StudentDto();
        List<Student> students= studentRepo.findStudentsByEntryName(entryName);
        List<StudentDto> result = new ArrayList<>();

        for (Student student : students) {
            result.add(temp.convertToDto(student));
        }

        return result;
    }

    @Override
    public StudentDto findStudentByStudentId(String studentId) {
        StudentDto temp = new StudentDto();
        var student = studentRepo.findStudentByStudentId(studentId).get();
        return temp.convertToDto(student);
    }

    @Override
    public StudentDto findStudentByBarcode(String barcode) {
        StudentDto temp = new StudentDto();
        var student = studentRepo.findStudentByBarcode(barcode).get();
        return temp.convertToDto(student);
    }

    @Override
    public boolean save(StudentDto studentDto) {
        Student student = studentDto.convertToEntity(studentDto);
        studentRepo.save(student);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        studentRepo.deleteById(id);
        return true;
    }

    @Override
    public boolean update(StudentDto studentDto) {
        Student student = studentDto.convertToEntity(studentDto);
        studentRepo.save(student);
        return true;
    }

    @Override
    public ArrayList<StudentDto> findAll() {
        var res = studentRepo.findAll();
        ArrayList<StudentDto> result = new ArrayList<>();
        StudentDto temp = new StudentDto();
        for (Student student : res) {
            result.add(temp.convertToDto(student));
        }
        return result;
    }


    public ArrayList<StudentDtoForCrud> findAllForCrud() {
        var res = studentRepo.findAll();
        ArrayList<StudentDtoForCrud> result = new ArrayList<>();
        StudentDtoForCrud temp = new StudentDtoForCrud();
        for (Student student : res) {
            result.add(temp.convertToDto(student));
        }
        return result;
    }

    @Override
    public StudentDto findById(Long id) {
        var res = studentRepo.findById(id);
        StudentDto temp = new StudentDto();
        return temp.convertToDto(res.get());
    }

}
