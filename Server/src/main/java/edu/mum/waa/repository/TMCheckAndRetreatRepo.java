package edu.mum.waa.repository;

import edu.mum.waa.entity.TmCheckAndRetreat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TMCheckAndRetreatRepo extends CrudRepository<TmCheckAndRetreat, Long> {

    Optional<TmCheckAndRetreat> findTmCheckAndRetreatOrderById(Long id);
}
