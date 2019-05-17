package edu.mum.waa.repository;

import edu.mum.waa.entity.Credential;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationRepo extends CrudRepository<Credential,Long> {

    Credential findByEmailAndPassword(String email,String password);

}
