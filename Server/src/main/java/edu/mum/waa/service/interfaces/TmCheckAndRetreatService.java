package edu.mum.waa.service.interfaces;

import edu.mum.waa.entity.TmCheckAndRetreat;

import java.util.Optional;

public interface TmCheckAndRetreatService {

    Optional<TmCheckAndRetreat> findTmCheckAndRetreatOrderById(Long id);

    TmCheckAndRetreat save(TmCheckAndRetreat tmCheckAndRetreat);
}
