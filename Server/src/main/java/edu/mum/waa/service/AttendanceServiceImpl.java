package edu.mum.waa.service;

import edu.mum.waa.dto.*;
import edu.mum.waa.entity.Attendance;
import edu.mum.waa.repository.AttendanceRepo;
import edu.mum.waa.security.SecurityHelper;
import edu.mum.waa.service.interfaces.AttendanceService;
import edu.mum.waa.service.interfaces.BlockService;
import edu.mum.waa.service.interfaces.SectionService;
import edu.mum.waa.service.interfaces.StudentService;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    private AttendanceRepo attendanceRepo;
    private SecurityHelper securityHelper;
    private BlockService blockService;
    private SectionService sectionService;
    private StudentService studentService;


    @Autowired
    public AttendanceServiceImpl(AttendanceRepo attendanceRepo, SecurityHelper securityHelper, BlockService blockService, SectionService sectionService, StudentService studentService) {
        this.attendanceRepo = attendanceRepo;
        this.securityHelper = securityHelper;
        this.blockService = blockService;
        this.sectionService = sectionService;
        this.studentService = studentService;
    }

    @Override
    public ExtraCreditModel getExtraCreditsByBlock(String blockName) {
        ExtraCreditModel extraCreditModel = new ExtraCreditModel();
        List<StudentDataModel> studentDataModels = new ArrayList<>();


        long facultyId = securityHelper.getCurrentUserId();
        SectionDto sectionDto = sectionService.findByFacultyIdAndBlockName(facultyId, blockName);

        List<StudentDto> studentDtoList = sectionDto.getStudentList();

        for(StudentDto studentDto:studentDtoList){
            StudentDataModel studentDataModel = new StudentDataModel();
            studentDataModel.setFirstName(studentDto.getFirstName());
            studentDataModel.setLastName(studentDto.getLastName());
            studentDataModel.setId(studentDto.getStudentId());
            studentDataModel.setExtraPoint(calculateOneBlock(blockName,studentDto.getId()).getExtraCredits());

            studentDataModels.add(studentDataModel);
        }

        extraCreditModel.setSelectedBlock(blockName);
        extraCreditModel.setData(studentDataModels);
        return extraCreditModel;
    }

    @Override
    public AttendanceDatePresentDto getStudentAttendanceByStudentIdAndBlock(String blockName, Long idStudent) {
        AttendanceDatePresentDto main = calculateOneBlock(blockName, idStudent);

        //Long studentId = securityHelper.getCurrentUserId();
        StudentDto studentDto = studentService.findById(idStudent);

        List<String> blocks = getAllBlocks(studentDto, blockName);


        main.setTotalSessionsPossible(main.getSessionsInBlock());
        main.setTotalSessionsAttended(main.getDaysPresent());

        for (String block : blocks) {
            AttendanceDatePresentDto sub = calculateOneBlock(block, idStudent);
            main.setTotalSessionsPossible(main.getTotalSessionsPossible() + sub.getSessionsInBlock());
            main.setTotalSessionsAttended(main.getTotalSessionsAttended() + sub.getDaysPresent());
        }
        main.setPercentageAttendedInTotal((int) ((double) main.getTotalSessionsAttended() / (double) main.getTotalSessionsPossible() * 100));

        return main;
    }

    private AttendanceDatePresentDto calculateOneBlock(String blockName, long idStudent) {
        AttendanceDatePresentDto attendanceDatePresentDto = new AttendanceDatePresentDto();

        List<DatePresentDto> result = new ArrayList<>();
        List<DatePresentDto> resultSorted = new ArrayList<>();


        //Long studentId = securityHelper.getCurrentUserId();
        BlockDto blockDto = blockService.findByName(blockName);
        List<Attendance> attendanceList = attendanceRepo.findByBlockIdAndStudentId(blockDto.getId(), idStudent);

        for (Attendance attendance : attendanceList) {
            DatePresentDto datePresentDto = new DatePresentDto();
            datePresentDto.setDate(attendance.getDateTime());
            datePresentDto.setPresent(true);
            result.add(datePresentDto);
        }

        attendanceDatePresentDto.setDaysPresent(result.size());

        //false
        List<LocalDate> allDays = getDatesBetween(blockDto.getStartDate(), blockDto.getEndDate());

        List<LocalDate> resultDate = result.stream().map(DatePresentDto::getDate).collect(Collectors.toList());

        List<LocalDate> falseDays = allDays.stream()
                .filter(o -> !resultDate.contains(o))
                .collect(Collectors.toList());


        for (LocalDate localDate : falseDays) {
            DatePresentDto datePresentDto = new DatePresentDto();
            datePresentDto.setDate(localDate);
            datePresentDto.setPresent(false);
            result.add(datePresentDto);
        }


        resultSorted = result.stream().sorted(Comparator.comparing(DatePresentDto::getDate)).collect(Collectors.toList());

        attendanceDatePresentDto.setDatePresentDtoList(resultSorted);

        StudentDto studentDto = studentService.findById(idStudent);

        SectionDto sectionDto = sectionService.findByBlockIdAndStudentListContains(blockDto.getId(), studentDto);
        attendanceDatePresentDto.setSessionsInBlock(sectionDto.getAvailableDays() - sectionDto.getCanceledDays());

        attendanceDatePresentDto.setPercentageAttended((int) ((double) attendanceDatePresentDto.getDaysPresent() / (double) attendanceDatePresentDto.getSessionsInBlock() * 100));

        attendanceDatePresentDto.setExtraCredits(calculateExtraCredits(attendanceDatePresentDto.getPercentageAttended()));


        return attendanceDatePresentDto;

    }

    @Override
    //@Secured(RoleEnum.VIEW_ENTRY_REPORT)
    public List<AttendanceByEntryDto> getReportByEntry(String entry) {

        List<AttendanceByEntryDto> result = new ArrayList<>();


        var studentList = studentService.findStudentsByEntry(entry);
        for (StudentDto studentDto : studentList) {
            AttendanceByEntryDto attendanceByEntryDto = new AttendanceByEntryDto();
            attendanceByEntryDto.setFirstName(studentDto.getFirstName());
            attendanceByEntryDto.setLastName(studentDto.getLastName());
            HashMap<String, AttendancePerStudentDto> attendancePerBlock = new HashMap<>();

            for (int i = 0; i < studentDto.getSections().size(); i++) {
                String blockName = studentDto.getSections().get(i).getBlock().getName();
                long studentId = studentDto.getId();

                var byBlock = getStudentAttendanceByStudentIdAndBlock(blockName, studentId);
                AttendancePerStudentDto attendancePerStudentDto = new AttendancePerStudentDto();
                attendancePerStudentDto.setAvailableDays(byBlock.getSessionsInBlock());
                attendancePerStudentDto.setDaysPresent(byBlock.getDaysPresent());
                attendancePerStudentDto.setPercentageAttended(byBlock.getPercentageAttended());

                attendancePerBlock.put(blockName, attendancePerStudentDto);

            }
            attendanceByEntryDto.setAttendancePerBlock(attendancePerBlock);
            result.add(attendanceByEntryDto);
        }

        return result;
    }


    private double calculateExtraCredits(double percentageAttendance) {

        if (percentageAttendance >= 90) {
            return 1.5;
        } else if (percentageAttendance >= 80) {
            return 1.0;
        } else if (percentageAttendance >= 70) {
            return 0.5;
        }
        return 0.0;
    }

    private List<String> getAllBlocks(StudentDto studentDto, String mainBlock) {
        return studentDto.getSections().stream().map(l -> l.getBlock().getName()).filter(l -> !l.equals(mainBlock)).collect(Collectors.toList());

    }

    private List<LocalDate> getDatesBetween(
            LocalDate startDate, LocalDate endDate) {

        long numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate);
        return IntStream.iterate(0, i -> i + 1)
                .limit(numOfDaysBetween)
                .mapToObj(i -> startDate.plusDays(i))
                .filter(l -> !l.getDayOfWeek().equals(DayOfWeek.SUNDAY))
                .collect(Collectors.toList());
    }

    @Override
    public boolean save(AttendanceDto attendanceDto) {

        //already added
        boolean exists = attendanceRepo.existsByDateTimeAndStudentId(attendanceDto.getDateTime(), attendanceDto.getStudent().getId());
        if (!exists) {
            Attendance attendance = attendanceDto.convertToEntity(attendanceDto);
            attendanceRepo.save(attendance);
        }

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


}
