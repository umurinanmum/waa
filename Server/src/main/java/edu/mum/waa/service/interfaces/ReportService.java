package edu.mum.waa.service.interfaces;

import edu.mum.waa.entity.EntryReport;
import edu.mum.waa.entity.FacultyReport;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReportService {
    /**
     * Faculty  Report By Section Id
     * @param sectionId
     * @return FacultyReport
     */
    List<FacultyReport> exportFacultyReport(Long sectionId);

    List<EntryReport> exportEntryReport(Long entryId, Pageable pageable );

    /**
     * use for pagination of @link{exportEntryReport}
     * @param entryId
     * @return
     */
    Integer retrieveSizeOfEntryReport(@Param("entryId") Long entryId);
}
