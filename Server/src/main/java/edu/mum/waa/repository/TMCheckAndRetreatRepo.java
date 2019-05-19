package edu.mum.waa.repository;

import edu.mum.waa.entity.TmCheckAndRetreat;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TMCheckAndRetreatRepo extends CrudRepository<TmCheckAndRetreat, Long> {

    Optional<TmCheckAndRetreat> findTmCheckAndRetreatOrderById(Long id);

    @Query(value = "SELECT t FROM TmCheckAndRetreat t WHERE t.student.id = :stuId")
    List<TmCheckAndRetreat> fetchTmCheckAndRetreatOrderByStudent(Long stuId, Pageable pageable);
}
