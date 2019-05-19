package edu.mum.waa.repository;

import edu.mum.waa.entity.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationRepo extends CrudRepository<Location, Long> {

    Optional<Location> findByLocationCode(String locationName);

}
