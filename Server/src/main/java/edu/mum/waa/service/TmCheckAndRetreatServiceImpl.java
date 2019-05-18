package edu.mum.waa.service;

import edu.mum.waa.entity.TmCheckAndRetreat;
import edu.mum.waa.repository.TMCheckAndRetreatRepo;
import edu.mum.waa.service.interfaces.TmCheckAndRetreatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TmCheckAndRetreatServiceImpl implements TmCheckAndRetreatService {

    @Autowired
    TMCheckAndRetreatRepo tmCheckAndRetreatRepo;

    public Optional<TmCheckAndRetreat> findTmCheckAndRetreatOrderById(Long id) {
        return tmCheckAndRetreatRepo.findTmCheckAndRetreatOrderById(id);
    }

    public TmCheckAndRetreat save(TmCheckAndRetreat tmCheckAndRetreat) {
        return tmCheckAndRetreatRepo.save(tmCheckAndRetreat);
    }
}
