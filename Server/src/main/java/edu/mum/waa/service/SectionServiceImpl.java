package edu.mum.waa.service;

import edu.mum.waa.dto.SectionDto;
import edu.mum.waa.dto.StudentDto;
import edu.mum.waa.entity.Section;
import edu.mum.waa.repository.SectionRepo;
import edu.mum.waa.service.interfaces.SectionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SectionServiceImpl implements SectionService {

    private SectionRepo sectionRepo;

    public SectionServiceImpl(SectionRepo sectionRepo) {
        this.sectionRepo = sectionRepo;
    }


    @Override
    public SectionDto findByFacultyIdAndBlockName(Long facultyId, String blockName) {
        SectionDto temp = new SectionDto();
        Section section = sectionRepo.findByFacultyIdAndBlockName(facultyId,blockName);
        return temp.convertToDto(section);
    }

    @Override
    public SectionDto findByBlockIdAndStudentListContains(Long id, StudentDto studentDto) {
        SectionDto temp = new SectionDto();
        Section section = sectionRepo.findByBlockIdAndStudentListContains(id,studentDto.convertToEntity(studentDto));
        return temp.convertToDto(section);
    }

    @Override
    public boolean save(SectionDto sectionDto) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public boolean update(SectionDto sectionDto) {
        return false;
    }

    @Override
    public ArrayList<SectionDto> findAll() {
        return null;
    }

    @Override
    public SectionDto findById(Long id) {
        return null;
    }
}
