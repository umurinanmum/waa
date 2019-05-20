package edu.mum.waa.repository;

import edu.mum.waa.entity.FacultyReport;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepo extends JpaRepository<FacultyReport, Long> {

    @Query(value = " SELECT s.student_id, COUNT(s.student_id) AS attendant"
            + " FROM Student s, attendance  a,  student_sections ss , section sec "
            + " WHERE s.id = a.student_id"
            + " AND  s.id = ss.STUDENT_LIST_ID AND sec.block_id = a.block_id"
            + " AND sec.id = ss.sections_id"
            + " AND ss.sections_id = :sectionId"
            + " GROUP BY s.student_id"
            , nativeQuery = true)
    List<Object[]> exportFacultyReport(@Param("sectionId") Long sectionId);


    @Query(value = " SELECT s.student_id, COUNT(s.student_id) FROM entry e, student s, attendance a " +
            " WHERE e.id=s.entry_id AND s.id = a.student_id " +
            " AND e.id = :entryId" +
            " GROUP BY s.id"
            , nativeQuery = true)
    List<Object[]> exportEntryReport(@Param("entryId") Long entryId, Pageable pageable);

    @Query(value = " SELECT count(*) FROM entry e, student s, attendance a " +
            " WHERE e.id=s.entry_id AND s.id = a.student_id " +
            " AND e.id = :entryId"
            , nativeQuery = true)
    Integer retrieveSizeOfEntryReport(@Param("entryId") Long entryId);
}
