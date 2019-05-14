package edu.mum.waa.service;

import edu.mum.waa.dto.AttendanceDto;
import edu.mum.waa.entity.Attendance;
import edu.mum.waa.entity.Student;
import edu.mum.waa.repository.AttendanceRepo;
import edu.mum.waa.repository.StudentRepo;
import edu.mum.waa.service.interfaces.AttendanceService;
import lombok.var;
import org.modelmapper.internal.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    private AttendanceRepo attendanceRepo;
    private StudentRepo studentRepo;

    @Autowired
    public AttendanceServiceImpl(AttendanceRepo attendanceRepo, StudentRepo studentRepo) {
        this.attendanceRepo = attendanceRepo;
        this.studentRepo = studentRepo;

    }


    @Override
    public boolean save(AttendanceDto attendanceDto) {
        Attendance attendance = attendanceDto.convertToEntity(attendanceDto);
        attendanceRepo.save(attendance);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        attendanceRepo.deleteById(id);
        return true;
    }

    @Override
    public boolean update(AttendanceDto attendanceDto) {
        Attendance attendance = attendanceDto.convertToEntity(attendanceDto);
        attendanceRepo.save(attendance);
        return true;
    }

    @Override
    public ArrayList<AttendanceDto> findAll() {
        var res = attendanceRepo.findAll();
        ArrayList<AttendanceDto> result = new ArrayList<>();
        AttendanceDto temp = new AttendanceDto();
        for (Attendance attendance : res) {
            result.add(temp.convertToDto(attendance));
        }
        return result;
    }

    @Override
    public AttendanceDto findById(Long id) {
        var res = attendanceRepo.findById(id);
        AttendanceDto temp = new AttendanceDto();
        return temp.convertToDto(res.get());
    }

    public boolean saveFromFile() {

        Attendance attendance = new Attendance();


        String data = "7888,11/17/16,AM,DB\n" +
                "7326,11/17/16,AM,DB\n" +
                "7698,11/17/16,AM,DB\n" +
                "7417,11/17/16,AM,DB\n" +
                "7839,11/17/16,AM,DB\n" +
                "7284,11/17/16,AM,DB\n" +
                "7482,11/17/16,AM,DB\n" +
                "7938,11/17/16,AM,DB";


        String[] rows = data.split("\n");

        try {
            for (String row : rows) {
                String[] column = row.split(",");
                String barcode = column[0];
                String date = column[1];
                String time = column[2];
                String location = column[3];

                //todo change below line with service
                Student student =  studentRepo.findStudentByBarcode(barcode).get();



                //set attendance
                attendance.setStudent(student);
                attendance.setDateTime(LocalDate.parse(date));

            }
        } catch (Exception e) {
            e.printStackTrace();
            //something is wrong with format
        }


        attendanceRepo.save(attendance);
        return true;
    }

}
