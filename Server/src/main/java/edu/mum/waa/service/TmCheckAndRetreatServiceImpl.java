package edu.mum.waa.service;

import edu.mum.waa.dto.SearchResultDto;
import edu.mum.waa.dto.WaaPageable;
import edu.mum.waa.entity.Student;
import edu.mum.waa.entity.TmCheckAndRetreat;
import edu.mum.waa.exceptions.StudentException;
import edu.mum.waa.repository.TMCheckAndRetreatRepo;
import edu.mum.waa.service.interfaces.TmCheckAndRetreatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TmCheckAndRetreatServiceImpl implements TmCheckAndRetreatService {

    @Autowired
    TMCheckAndRetreatRepo tmCheckAndRetreatRepo;

    public Optional<TmCheckAndRetreat> findTmCheckAndRetreatOrderById(Long id) {
        return tmCheckAndRetreatRepo.findTmCheckAndRetreatOrderById(id);
    }

    public TmCheckAndRetreat save(TmCheckAndRetreat tmCheckAndRetreat) throws StudentException {
        if (tmCheckAndRetreat.getStudent() != null && tmCheckAndRetreat.getStudent().getId() < 1) {
            throw new StudentException("studentError");
        }
        return tmCheckAndRetreatRepo.save(tmCheckAndRetreat);
    }

    public void deleteById(Long id) {
        tmCheckAndRetreatRepo.deleteById(id);
    }

    public SearchResultDto<TmCheckAndRetreat> findTmCheckAndRetreatOrderByStudent(Long stuId, WaaPageable pageable) {

        SearchResultDto result = new SearchResultDto();

        List<TmCheckAndRetreat> listResult = tmCheckAndRetreatRepo.fetchTmCheckAndRetreatOrderByStudent(stuId, pageable);

        int total = listResult.size();
        // pageNumber : current page
        if (total >= pageable.getPageSize()) {
            pageable.setNextPage(pageable.getPageNumber() + 1);
            listResult = listResult.subList(0, total - 1);
        }

        pageable.setTotal(total);
        System.out.println("size: " + total);
        System.out.println("offset: " + pageable.getOffset());

        pageable.setNumberOfPages(total / pageable.getPageSize());
        result.setResult(listResult);
        result.setPageable(pageable);

        System.out.println("pageable.getPageSize(): " + pageable.getPageSize());
        System.out.println("setNumberOfPages: " + pageable.getNumberOfPages());

        return result;
    }
}
