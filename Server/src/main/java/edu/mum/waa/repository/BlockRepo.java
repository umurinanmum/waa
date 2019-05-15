package edu.mum.waa.repository;

import edu.mum.waa.entity.Block;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface BlockRepo extends CrudRepository<Block,Long> {

    Optional<Block> findBlockByStartDateBeforeAndEndDateAfter(LocalDate date,LocalDate date2);


}
