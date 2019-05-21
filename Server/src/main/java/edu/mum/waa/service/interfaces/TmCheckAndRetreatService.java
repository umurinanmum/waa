package edu.mum.waa.service.interfaces;

import edu.mum.waa.dto.SearchResultDto;
import edu.mum.waa.dto.WaaPageable;
import edu.mum.waa.entity.TmCheckAndRetreat;
import edu.mum.waa.exceptions.StudentException;

import java.util.List;
import java.util.Optional;

public interface TmCheckAndRetreatService {

    Optional<TmCheckAndRetreat> findTmCheckAndRetreatOrderById(Long id);

    TmCheckAndRetreat save(TmCheckAndRetreat tmCheckAndRetreat); // throws StudentException;

    void deleteById(Long id);

    SearchResultDto<TmCheckAndRetreat> findTmCheckAndRetreatOrderByStudent(Long stuId, WaaPageable pageable);
}
