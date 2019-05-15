package edu.mum.waa.service;

import edu.mum.waa.dto.StudentDto;
import edu.mum.waa.entity.Student;
import edu.mum.waa.repository.StudentRepo;
import edu.mum.waa.service.interfaces.StudentService;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StudentServiceImpl implements StudentService {


    private StudentRepo studentRepo;

    @Autowired
    public StudentServiceImpl(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }


    @Override
    public StudentDto findStudentByBarcode(String barcode) {
        StudentDto temp = new StudentDto();
        return temp.convertToDto(studentRepo.findStudentByBarcode(barcode).get());
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
