package com.miniBank.repos;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.miniBank.model.Users;

public interface UserRepository extends CrudRepository<Users, Integer>{
	Users findByUserNameAndPassword(String userName, String password);
}
