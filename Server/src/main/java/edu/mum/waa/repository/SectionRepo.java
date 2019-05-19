package edu.mum.waa.repository;

import edu.mum.waa.entity.Section;
import edu.mum.waa.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionRepo extends CrudRepository<Section, Long> {

    Section findByBlockIdAndStudentListContains(Long id,Student student);


    Section findByFacultyIdAndBlockName(Long facultyId,String blockName);
}
