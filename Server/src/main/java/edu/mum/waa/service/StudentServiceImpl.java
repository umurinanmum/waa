package edu.mum.waa.service;

import edu.mum.waa.dto.StudentDto;
import edu.mum.waa.entity.Student;
import edu.mum.waa.repository.StudentRepo;
import edu.mum.waa.service.interfaces.StudentService;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {


    private StudentRepo studentRepo;

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

    @Override
    public StudentDto findById(Long id) {
        var res = studentRepo.findById(id);
        StudentDto temp = new StudentDto();
        return temp.convertToDto(res.get());
    }
}
