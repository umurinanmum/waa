package edu.mum.waa.service.interfaces;

import edu.mum.waa.dto.SectionDto;
import edu.mum.waa.dto.StudentDto;

public interface SectionService extends BaseService<SectionDto> {

    SectionDto findByBlockIdAndStudentListContains(Long id, StudentDto studentDto);


}
