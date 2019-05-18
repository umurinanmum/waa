package edu.mum.waa.repository;

import edu.mum.waa.entity.Entry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntryRepo extends CrudRepository<Entry, Long> {


}
