package edu.mum.waa.service;

import edu.mum.waa.entity.EntryReport;
import edu.mum.waa.entity.FacultyReport;
import edu.mum.waa.repository.BlockRepo;
import edu.mum.waa.repository.ReportRepo;
import edu.mum.waa.service.interfaces.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Pageable;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    ReportRepo reportRepo;

    @Autowired
    private Environment environment;

    @Autowired
    private BlockRepo blockRepo;

    public List<FacultyReport> exportFacultyReport(Long sectionId) {
        System.out.println("sectionId: " + sectionId);
        List<FacultyReport> result = new ArrayList<>();
        List<Object[]> list = reportRepo.exportFacultyReport(sectionId);

        String[] percents = environment.getProperty("percent", "90,80,70").split(",");
        String[] extraCredits =  environment.getProperty("extraCredit", "90,80,70").split(",");

        for (Object[] obj : list) {
            Long id = 0L;
            String student_id = (String) obj[0];
            String name = "";
            Double attendant = ((BigInteger) obj[1]).doubleValue();
            Double percent = Double.valueOf(attendant / 100 * 100); //TODO block.availbel _days - cancelDays

            Double extraCredit = 0.0;

            for (int i = 0; i < percents.length; i++) {
                if (percent >= Double.valueOf( percents[i])) { // 90
                    extraCredit = Double.valueOf( extraCredits[i]);
                    break;
                }
            }
            FacultyReport item = new FacultyReport(id, student_id, name, attendant.intValue(), percent, extraCredit);
            result.add(item);
        }
        return result;
    }

    public List<EntryReport> exportEntryReport(Long entryId, Pageable pageable) {
        System.out.println("entryId: " + entryId);
        List<EntryReport> result = new ArrayList<>();

        List<Object[]> list = reportRepo.exportEntryReport(entryId, pageable);

        String[] percents = environment.getProperty("percent", "90,80,70").split(",");
        String[] extraCredits =  environment.getProperty("extraCredit", "90,80,70").split(",");

        for (Object[] obj : list) {
            Long id = 0L;
            String student_id = (String) obj[0];
            String name = "";
            Double attendant = ((BigInteger) obj[1]).doubleValue();
            Double percent = Double.valueOf(attendant / 100 * 100); //TODO block.availbel _days - cancelDays

            Double extraCredit = 0.0;

            for (int i = 0; i < percents.length; i++) {
                if (percent >= Double.valueOf( percents[i])) { // 90
                    extraCredit = Double.valueOf( extraCredits[i]);
                    break;
                }
            }
            EntryReport item = new EntryReport(id, student_id, name, attendant.intValue(), percent, extraCredit);
            result.add(item);
        }
        return result;
    }

    public Integer retrieveSizeOfEntryReport(@Param("entryId") Long entryId) {
        return  reportRepo.retrieveSizeOfEntryReport(entryId);
    }
}
